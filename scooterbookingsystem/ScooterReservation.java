package scooterbookingsystem;

import java.util.List;
import java.time.LocalDate;
import java.util.LinkedList;

public class ScooterReservation {
    private static List<Registeration> allScooters = new LinkedList<>();

    static{
        allScooters.add(new Registeration(190, "Gotrax", "Blue", 51, LocalDate.now(), 10));
        allScooters.add(new Registeration(191, "Unagi", "Red", 34, LocalDate.now(), 23));
        allScooters.add(new Registeration(192, "Turboant", "Black", 78, LocalDate.now(), 5));
        allScooters.add(new Registeration(193, "Hiboy", "Orange", 25, LocalDate.now(), 64));
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
  
    public static void locateSpecificScooters(String scooterBrand, String scooterColor, LocalDate reserveDate, int numOfScooters){
        List<Registeration> findSpecificScooter = new LinkedList<>();
        for(Registeration a : allScooters){
            
            if(a.getScooterBrand().equals(scooterBrand) && a.getScooterColor().equals(scooterColor)
            && a.getReserveDate().equals(reserveDate) && a.getNumOfScooters() > (numOfScooters))
            {
                findSpecificScooter.add(a);
            }
        }
        if (findSpecificScooter.size() == 0){

            System.out.println("There are no scooters matching your search entry.");

        }
        System.out.println("Scooter Brand\tScooter Color\tReservation Date\tAvailable Scooters\t");
        System.out.println();

        for (Registeration a : findSpecificScooter){
            System.out.printf("%10s%10s%4d%5d\n", a.getScooterBrand(), a.getScooterColor(),
            a.getReserveDate(), a.getNumOfScooters());

        }

        

    }
}
