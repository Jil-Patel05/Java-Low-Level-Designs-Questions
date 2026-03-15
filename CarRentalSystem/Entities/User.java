package CarRentalSystem.Entities;

import java.util.HashMap;
import java.util.Map;

public class User {
    public int id;
    public String userName;
    public String licenseNumber;
    public Map<Integer, Reservations> reservations = new HashMap<>();

    public User(int id, String userName, String licenceNumber) {
        this.id = id;
        this.userName = userName;
        this.licenseNumber = licenceNumber;
    }

    public void addReservation(Reservations reservation) {
        reservations.put(reservation.id, reservation);
    }
}
