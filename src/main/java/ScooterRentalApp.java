import dao.FileBasedScooterDaoImpl;
import dao.ScooterDAO;
import dao.ScooterSearchCriteria;
import model.Scooter;

import java.time.LocalDate;
import java.util.*;

import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

public class ScooterRentalApp {
    public static void main(String[] args) {
        ScooterDAO dao = new FileBasedScooterDaoImpl("scooters.txt");
        Scanner consoleInputScanner = new Scanner(System.in);

        String action = "";
        while (!"exit".equals(action)) {
            do {
                System.out.println("What would you like to do? (search/reserve/return/exit)");
                action = consoleInputScanner.nextLine();
            } while (!List.of("search", "reserve", "return", "exit").contains(action));

            switch (action) {
                case "search":
                    searchScooterFlow(dao, consoleInputScanner);
                    continue;
                case "reserve":
                    reservationFlow(dao, consoleInputScanner);
                    continue;
                case "return":
                    returnFlow(dao, consoleInputScanner);
                    continue;
                default:
            }
        }
    }

    private static void reservationFlow(ScooterDAO dao, Scanner consoleInputScanner) {
        System.out.println("Please copy and paste the scooter from search that you want to reserve");
        dao.reserveScooter(Scooter.fromString(consoleInputScanner.nextLine()));
    }

    private static void returnFlow(ScooterDAO dao, Scanner consoleInputScanner) {
        System.out.println("Please copy and paste the scooter you want to return. For example \"honda,45.5,green,2022-04-28,1\"");
        dao.addScooter(Scooter.fromString(consoleInputScanner.nextLine()));
    }

    private static void searchScooterFlow(ScooterDAO dao, Scanner consoleInputScanner) {
        boolean searchFinished = false;

        while (!searchFinished) {
            System.out.println("Please enter search criteria in following format (omit any criteria you do not want to search by):\n" +
                    "model:{model}|colour:{colour}|maxprice:{price}|date:{yyyy-mm-dd}");

            String searchCriteriaString = consoleInputScanner.nextLine().toLowerCase();
            Map<String, String> searchCriteria = searchCriteriaString.isEmpty() ? Map.of() :
                    Arrays.stream(searchCriteriaString.split("\\|"))
                            .map(option -> option.split(":"))
                            .collect(toMap(option -> option[0], option -> option[1]));

            ScooterSearchCriteria search = ScooterSearchCriteria.Builder.builder()
                    .model(Optional.ofNullable(searchCriteria.get("model")))
                    .colour(Optional.ofNullable(searchCriteria.get("color")))
                    .maxPrice(Optional.ofNullable(searchCriteria.get("maxprice")).map(Double::parseDouble))
                    .reservationDate(Optional.ofNullable(searchCriteria.get("date")).map(LocalDate::parse))
                    .build();

            Set<Scooter> availableScooters = dao.findScooters(search).stream()
                    .filter(scooter -> scooter.quantity > 0)
                    .collect(toSet());

            if (availableScooters.isEmpty())
                System.out.println("Sorry we cannot find any available scooter for your search options");
            else {
                System.out.println("Here are the scooters that match your search:\nmodel,price,colour,reservation_date,quantity");
                availableScooters.forEach(System.out::println);
            }

            System.out.println("would you like to search again? (y/n)");
            if (consoleInputScanner.nextLine().equals("n")) {
                searchFinished = true;
            }
        }
    }
}
