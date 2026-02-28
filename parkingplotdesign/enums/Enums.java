package parkingplotdesign.enums;

public class Enums {
    public enum VEHICLE_TYPE {
        CAR,
        BIKE,
        TRUCK
    }

    public enum PER_HOUR_VEHICLE_CHARGE {
        CAR(10),
        TRUCK(20),
        BIKE(5);

        private final int price;

        private PER_HOUR_VEHICLE_CHARGE(int price) {
            this.price = price;
        }

        public int getPrice() {
            return this.price;
        }
    }
}
