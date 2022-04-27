package scooterbookingsystem;

import java.util.List;
import java.time.LocalDate;
import java.util.LinkedList;

public class ScooterReservation {
    private static List<Registeration> allScooters = new LinkedList<>();

    static{
        allScooters.add(new Registeration(190, "Gotrax", "Blue", 51, LocalDate.now()));
        allScooters.add(new Registeration(191, "Unagi", "Red", 34, LocalDate.now()));
        allScooters.add(new Registeration(192, "Turboant", "Black", 78, LocalDate.now()));
        allScooters.add(new Registeration(193, "Hiboy", "Orange", 25, LocalDate.now()));
    }

    public ScooterReservation() {
        super();
    }

    public static Registeration locateSerial(int serialNumber) {
        Registeration temp = null;
        for (Registeration a : allScooters){
            if(a.getSerialNumber() == serialNumber){
                temp = a;
                break;
            }
        }
        return temp;
    }
  
}
