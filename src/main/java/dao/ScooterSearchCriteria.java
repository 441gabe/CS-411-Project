package dao;

import model.Scooter;

import java.time.LocalDate;
import java.util.Optional;
import java.util.function.Predicate;

import static java.util.Optional.empty;

public class ScooterSearchCriteria {

    private final Optional<String> model;
    private final Optional<String> colour;
    private final Optional<Double> maxPrice;
    private final Optional<LocalDate> reservationDate;

    private ScooterSearchCriteria(Builder builder) {
        this.model = builder.model;
        this.colour = builder.colour;
        this.maxPrice = builder.maxPrice;
        this.reservationDate = builder.reservationDate;
    }

    public Predicate<Scooter> toPredicate() {
        return scooter -> {
            boolean correctColour = colour.map(colour -> colour.equals(scooter.colour)).orElse(true);
            boolean correctModel = model.map(model -> model.equals(scooter.model)).orElse(true);
            boolean correctReservationDate = reservationDate.map(date -> date.equals(scooter.reservationDate)).orElse(true);
            boolean correctMaxPrice = maxPrice.map(maxPrice -> maxPrice >= scooter.price).orElse(true);
            return correctColour && correctModel && correctReservationDate && correctMaxPrice;
        };
    }

    public static class Builder {
        private Optional<String> model = empty();
        private Optional<String> colour = empty();
        private Optional<Double> maxPrice = empty();
        private Optional<LocalDate> reservationDate = empty();

        public static Builder builder() {
            return new Builder();
        }

        public Builder model(Optional<String> model) {
            this.model = model;
            return this;
        }

        public Builder colour(Optional<String> colour) {
            this.colour = colour;
            return this;
        }

        public Builder maxPrice(Optional<Double> maxPrice) {
            this.maxPrice = maxPrice;
            return this;
        }

        public Builder reservationDate(Optional<LocalDate> reservationDate) {
            this.reservationDate = reservationDate;
            return this;
        }

        public ScooterSearchCriteria build() {
            return new ScooterSearchCriteria(this);
        }
    }
}
