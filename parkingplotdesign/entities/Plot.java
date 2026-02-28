package parkingplotdesign.entities;

import parkingplotdesign.enums.Enums.VEHICLE_TYPE;

public class Plot {
    public int id;
    public VEHICLE_TYPE vehicleTypeSupported;
    public boolean isOccupied;
    public Vehicle Vehicle;

    public Plot(VEHICLE_TYPE vt, int id) {
        this.vehicleTypeSupported = vt;
        this.id = id;
    }

    public void parkVehicle(Vehicle vh) {
        this.isOccupied = true;
        this.Vehicle = vh;
    }

    public void freeParkingSlot() {
        this.isOccupied = false;
        this.Vehicle = null;
    }

}
