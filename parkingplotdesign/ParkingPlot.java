package parkingplotdesign;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import parkingplotdesign.entities.ActiveTickets;
import parkingplotdesign.entities.ParkingFloor;
import parkingplotdesign.entities.Plot;
import parkingplotdesign.entities.Vehicle;
import parkingplotdesign.enums.Enums;
import parkingplotdesign.enums.Enums.VEHICLE_TYPE;
import parkingplotdesign.strategy.paymentstrategy.Payment;
import parkingplotdesign.strategy.paymentstrategy.PaymentStrategy;

public class ParkingPlot {
    private List<ParkingFloor> floors = new ArrayList<>();
    private PaymentStrategy pr;
    private Map<String, ActiveTickets> activeTickets = new HashMap<>();
    private volatile static ParkingPlot instace;

    private ParkingPlot() {

    }

    public ParkingPlot getInstance() {
        if (instace == null) {
            synchronized (ParkingPlot.class) {
                if (instace == null) {
                    instace = new ParkingPlot();
                }
            }
        }
        return instace;
    }

    public void setParkingPlotPaymentStrategy(Payment pr) {
        this.pr = new PaymentStrategy(pr);
    }

    public void addParkingFloors(ParkingFloor floor) {
        this.floors.add(floor);
    }

    public String findParkingPlot(VEHICLE_TYPE ch, Vehicle vehicle) {
        Optional<Plot> plot = Optional.empty();
        for (ParkingFloor floor : floors) {
            if (plot.isEmpty()) {
                plot = floor.getFreeParkigPlotId(ch);
            } else {
                break;
            }
        }
        if (plot.isPresent()) {
            Plot foundPLot = plot.get();
            foundPLot.parkVehicle(vehicle);
            ActiveTickets ticket = new ActiveTickets(vehicle, foundPLot);
            String ticketId = ticket.getTicketId();
            activeTickets.put(ticketId, ticket);
            return "PARKING TICKET IS GENERATE! Please DOWNLOAD";
        }
        return "OOPS! Not found parking plot";
    }

    private long getPrice(VEHICLE_TYPE vt, long hours) {
        long charge = 0;
        switch (vt) {
            case Enums.VEHICLE_TYPE.CAR:
                charge = Enums.PER_HOUR_VEHICLE_CHARGE.CAR.getPrice();
                break;

            case Enums.VEHICLE_TYPE.BIKE:
                charge = Enums.PER_HOUR_VEHICLE_CHARGE.BIKE.getPrice();
                break;

            case Enums.VEHICLE_TYPE.TRUCK:
                charge = Enums.PER_HOUR_VEHICLE_CHARGE.TRUCK.getPrice();
                break;

            default:
                break;
        }
        return charge * hours;
    }

    public String freeParkingPlot(String ticketId) {
        // We have to Remove Fee calculation from ParkingPlot
        ActiveTickets ticket = activeTickets.get(ticketId);
        Plot plot = ticket.plotOccupied;
        LocalDateTime time = ticket.occupiedTime;
        Vehicle vehicle = ticket.vechile;
        LocalDateTime currentTime = LocalDateTime.now();

        Duration duration = Duration.between(time, currentTime);
        long hours = duration.toHours();
        long charge = this.getPrice(vehicle.vehicleType, hours);

        this.pr.makePayment(charge);
        plot.freeParkingSlot();

        return "Money Paid! Please come again";
    }
}
