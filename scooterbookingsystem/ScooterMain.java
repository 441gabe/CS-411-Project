package scooterbookingsystem;

import java.time.LocalDate;

public class ScooterMain {
    public static void main(String[] args) {

        ScooterReservation.locateSpecificScooters("Unagi", "Red", LocalDate.now(), 2);  
    }
}
