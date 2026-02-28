package parkingplotdesign.entities;

import java.util.List;
import java.util.Optional;

import parkingplotdesign.enums.Enums.VEHICLE_TYPE;

import java.util.ArrayList;

public class ParkingFloor {
    public final List<Plot> plots = new ArrayList<>();
    public final int parkingLevel;

    public ParkingFloor(int parkingLevel) {
        this.parkingLevel = parkingLevel;
    }

    public void addParkingPlot(Plot plot) {
        this.plots.add(plot);
    }

    public void removeParkingPlot(Plot plot) {
        this.plots.remove(plot);
    }

    public Optional<Plot> getFreeParkigPlotId(VEHICLE_TYPE vh) {
        for (Plot plt : plots) {
            if (plt.vehicleTypeSupported == vh && plt.isOccupied == false) {
                return Optional.of(plt);
            }
        }
        return Optional.empty();
    }

}
