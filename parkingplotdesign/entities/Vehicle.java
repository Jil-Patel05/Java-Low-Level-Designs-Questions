package parkingplotdesign.entities;

import parkingplotdesign.enums.Enums.VEHICLE_TYPE;

public abstract class Vehicle {
    public VEHICLE_TYPE vehicleType;
    public String number;

    public Vehicle(VEHICLE_TYPE vh, String number) {
        this.vehicleType = vh;
        this.number = number;
    }
}
