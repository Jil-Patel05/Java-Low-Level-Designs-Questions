package CarRentalSystem.Entities;

import java.time.LocalDate;

import CarRentalSystem.GlobalEnums.RESERVATION_STATE;

public class Reservations {
    public int id;
    public int userId;
    public int cardId;
    private LocalDate bookingFrom;
    private LocalDate bookingTo;
    public int prevResId;
    public RESERVATION_STATE state;

    public Reservations(int id, int userId, int carId, LocalDate bookFrom, LocalDate bookTo) {
        this.id = id;
        this.userId = userId;
        this.cardId = carId;
        this.bookingFrom = bookFrom;
        this.bookingTo = bookTo;
        this.state = RESERVATION_STATE.PENDING;
    }

    public void setReservationState(RESERVATION_STATE state) {
        this.state = state;
    }

    public LocalDate getBookingFromDate() {
        return this.bookingFrom;
    }

    public LocalDate getBookingToDate() {
        return this.bookingTo;
    }

    public void linkReservation(Reservations prev) {
        this.prevResId = prev.id;
    }
}
