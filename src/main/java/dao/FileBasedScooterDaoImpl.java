package dao;

import model.Scooter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toSet;

public class FileBasedScooterDaoImpl implements ScooterDAO {

    private static final String HEADERS = "model,price,colour,reservation_date,quantity";
    private final String storageFileName;

    public FileBasedScooterDaoImpl(String storageFileName) {
        this.storageFileName = storageFileName;
    }

    @Override
    public Set<Scooter> getAllScooters() {
        try {
            List<String> data = new ArrayList<>();
            FileReader reader = new FileReader(storageFileName);
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNextLine()) {
                data.add(scanner.nextLine());
            }
            return IntStream.range(1, data.size())
                    .mapToObj(index -> Scooter.fromString(data.get(index)))
                    .collect(toSet());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Set<Scooter> findScooters(ScooterSearchCriteria scooterSearchCriteria) {
        return getAllScooters().stream().filter(scooterSearchCriteria.toPredicate()).collect(toSet());
    }

    @Override
    public void reserveScooter(Scooter scooter) {
        Set<Scooter> allScooters = getAllScooters();
        if (!allScooters.contains(scooter))
            throw new IllegalArgumentException("Scooter not found, unable to reserve");

        Scooter inStock = allScooters.stream().filter(s -> s.equals(scooter)).findAny().orElseThrow();
        if (inStock.quantity <= 0)
            throw new IllegalStateException("Cannot reserve when no scooter is in stock");

        allScooters.remove(inStock);
        allScooters.add(Scooter.of(inStock.model, inStock.price, inStock.colour, inStock.reservationDate, inStock.quantity - 1));

        try {
            persistData(allScooters);
        } catch (Exception e) {
            throw new RuntimeException("Failed to reserve scooter");
        }
    }

    @Override
    public void addScooter(Scooter scooter) {
        try {
            Set<Scooter> allScooters = getAllScooters();
            if (!allScooters.contains(scooter)) {
                allScooters.add(scooter);
                persistData(allScooters);
                return;
            }

            Scooter inStock = allScooters.stream().filter(s -> s.equals(scooter)).findAny().orElseThrow();
            allScooters.remove(inStock);
            allScooters.add(Scooter.of(inStock.model, inStock.price, inStock.colour, inStock.reservationDate, inStock.quantity + scooter.quantity));
            persistData(allScooters);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add scooter");
        }
    }

    private void persistData(Collection<Scooter> scooters) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add(HEADERS);
        scooters.forEach(scooter -> lines.add(scooter.toString()));
        FileWriter writer = new FileWriter(storageFileName);
        for (String str : lines) {
            writer.write(str + System.lineSeparator());
        }
        writer.close();
    }
}
