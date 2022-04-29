package model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.StringJoiner;

public class Scooter {

    public final String model;
    public final Double price;
    public final String colour;
    public final LocalDate reservationDate;
    public final Integer quantity;

    private Scooter(String model, Double price, String colour, LocalDate reservationDate, Integer quantity) {
        this.model = model;
        this.price = price;
        this.colour = colour;
        this.reservationDate = reservationDate;
        this.quantity = quantity;
    }

    public static Scooter of(String model, Double price, String colour, LocalDate reservationDate, Integer quantity) {
        return new Scooter(model, price, colour, reservationDate, quantity);
    }

    public static Scooter fromString(String input) {
        String[] fields = input.split(",");
        return Scooter.of(
                fields[0],                        // model
                Double.parseDouble(fields[1]),    // price
                fields[2],                        // colour
                LocalDate.parse(fields[3]),       // reservationDate
                Integer.parseInt(fields[4])       // quantity
        );
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(",");
        joiner.add(model).add(price.toString()).add(colour).add(reservationDate.toString()).add(quantity.toString());
        return joiner.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scooter scooter = (Scooter) o;
        return model.equals(scooter.model)
                && price.equals(scooter.price)
                && colour.equals(scooter.colour)
                && reservationDate.equals(scooter.reservationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, price, colour, reservationDate);
    }
}
