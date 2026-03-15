package CarRentalSystem.Entities;

import java.util.HashMap;
import java.util.Map;

import CarRentalSystem.GlobalEnums.CAR_TYPE;

public class Car {
    public int id;
    public CAR_TYPE carType;
    public String model;
    public String numberPlate;
    public Map<Integer, Reservations> carReservrations = new HashMap<>();
    public double pricePerDay;

    public Car(int id, CAR_TYPE type, String model, String numberPlate, double pricePerDay) {
        this.id = id;
        this.carType = type;
        this.model = model;
        this.numberPlate = numberPlate;
        this.pricePerDay = pricePerDay;
    }

    public void addCarReservations(Reservations reservation) {
        this.carReservrations.put(reservation.id, reservation);
    }

    public void removeReservations(int prevResId) {
        Reservations reservation = carReservrations.get(prevResId);
        this.carReservrations.remove(reservation.id, reservation);
    }
}
