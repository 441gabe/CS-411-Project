package scooterbookingsystem;

import java.time.LocalDate;

public class Registeration {
    private int serialNumber;
    private String scooterBrand;
    private String scooterColor;
    private int scooterPrice;
    private LocalDate reserveDate;

    public Registeration() {
        super();
    }

    public Registeration (int serialNumber, String scooterBrand, String scooterColor, int scooterPrice, LocalDate reserveDate){
        super();
        this.serialNumber = serialNumber;
        this.scooterBrand = scooterBrand;
        this.scooterColor = scooterColor;
        this.scooterPrice = scooterPrice;
        this.reserveDate = reserveDate;
    }
    
    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getScooterBrand() {
        return scooterBrand;
    }

    public void setScooterBrand(String scooterBrand) {
        this.scooterBrand = scooterBrand;
    }

    public String getScooterColor() {
        return scooterColor;
    }

    public void setScooterColor(String scooterColor) {
        this.scooterColor = scooterColor;
    }

    public int getScooterPrice() {
        return scooterPrice;
    }

    public void setScooterPrice(int scooterPrice) {
        this.scooterPrice = scooterPrice;
    }

    public LocalDate getReserveDate() {
        return reserveDate;
    }

    public void setReserveDate(LocalDate reserveDate) {
        this.reserveDate = reserveDate;
    }

    

}
