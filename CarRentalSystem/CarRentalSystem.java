package CarRentalSystem;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import CarRentalSystem.Entities.Car;
import CarRentalSystem.Entities.Payment;
import CarRentalSystem.Entities.PaymentRefund;
import CarRentalSystem.Entities.Reservations;
import CarRentalSystem.Entities.User;
import CarRentalSystem.GlobalEnums.CAR_TYPE;
import CarRentalSystem.GlobalEnums.PAYMENT_METHOD;
import CarRentalSystem.GlobalEnums.PAYMENT_STATE;
import CarRentalSystem.GlobalEnums.PAYMENT_TYPE;
import CarRentalSystem.GlobalEnums.RESERVATION_STATE;
import CarRentalSystem.Strategies.PaymentStrategy;
import CarRentalSystem.Strategies.UPIMethodStrategy;

public class CarRentalSystem {
    private static volatile CarRentalSystem instance;
    private Map<Integer, Car> cars = new HashMap<>();
    private final PaymentStrategy strategy;
    private List<PaymentRefund> refunds = new ArrayList<>();

    private CarRentalSystem() {
        strategy = new PaymentStrategy(new UPIMethodStrategy());
    }

    public void addCars(Car car) {
        cars.put(car.id, car);
    }

    public static CarRentalSystem getInstance() {
        if (instance == null) {
            synchronized (CarRentalSystem.class) {
                if (instance == null) {
                    instance = new CarRentalSystem();
                }
            }
        }
        return instance;
    }

    public List<Car> getCarList(LocalDate startDate, LocalDate endDate, CAR_TYPE type, double priceRangeStart,
            double priceRangeEnd) {
        List<Car> carsToReturn = this.cars.values().stream().filter((car) -> {
            if (checkCarAvaibility(car, startDate, endDate, null) && (type != null && car.carType == type)
                    && car.pricePerDay >= priceRangeStart
                    && car.pricePerDay <= priceRangeEnd) {
                return true;
            }
            return false;
        }).collect(Collectors.toList());
        return carsToReturn;
    }

    private boolean checkCarAvaibility(Car car, LocalDate sDate, LocalDate eDate, Reservations rv) {
        boolean hasOverlap = car.carReservrations.values().stream()
                .anyMatch(res -> {
                    if (rv == null) {
                        return res.getBookingFromDate().isBefore(eDate) &&
                                res.getBookingToDate().isAfter(sDate);
                    }
                    return res.id != rv.id && res.getBookingFromDate().isBefore(eDate) &&
                            res.getBookingToDate().isAfter(sDate);
                });
        return !hasOverlap;
    }

    public void makeReservation(Car car, LocalDate startDate, LocalDate endDate, User user) {
        Reservations rv = new Reservations(1, user.id, car.id, startDate, endDate);
        this.reserveTheCar(rv, car, startDate, endDate, user);
    }

    public List<Car> SearchInmodifiedReservation(Reservations rv, LocalDate startDate, LocalDate endDate, CAR_TYPE type,
            double priceRangeStart,
            double priceRangeEnd) {
        List<Car> carsToReturn = this.cars.values().stream().filter((car) -> {
            if (checkCarAvaibility(car, startDate, endDate, rv) && (type != null && car.carType == type)
                    && car.pricePerDay >= priceRangeStart
                    && car.pricePerDay <= priceRangeEnd) {
                return true;
            }
            return false;
        }).collect(Collectors.toList());
        return carsToReturn;
    }

    private void reserveTheCar(Reservations rv, Car car, LocalDate startDate, LocalDate endDate, User user) {
        long totalDays = ChronoUnit.DAYS.between(startDate, endDate);
        double price = totalDays * car.pricePerDay;
        Payment py = new Payment(1, PAYMENT_METHOD.UPI, PAYMENT_TYPE.FULL_PAYMENT, price, car.id, rv.id);
        try {
            this.strategy.makePayment(price);
            // Payment successfull we are marking as done
            py.setPaymentState(PAYMENT_STATE.FULLFILLED);
        } catch (Exception ex) {
            rv.setReservationState(RESERVATION_STATE.CANCELED);
            py.setPaymentState(PAYMENT_STATE.CANCELED);
        }
        try {
            // Try to go for reservation if failure occur
            rv.setReservationState(RESERVATION_STATE.ACTIVE);
            if (rv.state != RESERVATION_STATE.MODIFIED) {
                car.removeReservations(rv.prevResId);
                car.addCarReservations(rv);
            }
            user.addReservation(rv);
        } catch (Exception ex) {
            rv.setReservationState(RESERVATION_STATE.CANCELED);
            PaymentRefund rf = new PaymentRefund(0, price, user.id, rv.id, "Reservation failed");
            refunds.add(rf);
        }
    }

    public void modifyReservation(Reservations prevRv, Car car, LocalDate startDate, LocalDate endDate, User user) {
        Reservations rv = new Reservations(1, user.id, car.id, startDate, endDate);
        rv.linkReservation(prevRv);
        rv.setReservationState(RESERVATION_STATE.MODIFIED);
        this.reserveTheCar(rv, car, startDate, endDate, user);
    }

}
