package CarRentalSystem;

import java.time.LocalDate;
import java.util.List;

import CarRentalSystem.Entities.Car;
import CarRentalSystem.Entities.User;
import CarRentalSystem.GlobalEnums.CAR_TYPE;

public class CarRentalSystemDemo {
    public void initializeCarRental() {
        Car car1 = new Car(1, CAR_TYPE.ECONOMY, "2025", "1234", 8.0);
        Car car2 = new Car(2, CAR_TYPE.PREMIUM, "2023", "1234", 25.0);
        Car car3 = new Car(3, CAR_TYPE.LUX_SEDAN, "2021", "1234", 14.0);
        Car car4 = new Car(4, CAR_TYPE.LUX_SUV, "2026", "1234", 20.0);
        Car car5 = new Car(5, CAR_TYPE.SUV, "2019", "1234", 15.0);
        Car car6 = new Car(6, CAR_TYPE.SEDAN, "2022", "1234", 10.0);
        User user = new User(1, "jil.patel", "ABCD1234");

        CarRentalSystem system = CarRentalSystem.getInstance();
        system.addCars(car1);
        system.addCars(car2);
        system.addCars(car3);
        system.addCars(car4);
        system.addCars(car5);
        system.addCars(car6);

        List<Car> carToShow = system.getCarList(LocalDate.now().minusDays(2), LocalDate.now(), CAR_TYPE.SUV, 0, 100);
        for (Car car : carToShow) {
            System.out.println(car.id);
        }
        system.makeReservation(car5, LocalDate.now().minusDays(2), LocalDate.now(), user);
        carToShow = system.getCarList(LocalDate.now().minusDays(2), LocalDate.now(), CAR_TYPE.SUV, 0, 100);
        for (Car car : carToShow) {
            System.out.println(car.id);
        }
        System.out.println("Search in modified");
        carToShow = system.SearchInmodifiedReservation(1, LocalDate.now().minusDays(4), LocalDate.now(), CAR_TYPE.SUV,
                0, 100, user);
        for (Car car : carToShow) {
            System.out.println(car.id);
        }
        System.out.println(user.reservations.size());
        system.modifyReservation(1, car5, LocalDate.now().minusDays(4), LocalDate.now(), user);
        System.out.println(user.reservations.size());
    }
}
