package dao;

import model.Scooter;

import java.util.Set;

public interface ScooterDAO {

    Set<Scooter> getAllScooters();

    Set<Scooter> findScooters(ScooterSearchCriteria scooterSearchCriteria);

    void reserveScooter(Scooter scooter);

    void addScooter(Scooter scooter);
}
