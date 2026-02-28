package parkingplotdesign.entities;

import java.time.LocalDateTime;
import java.util.UUID;

public class ActiveTickets {
    public Plot plotOccupied;
    public LocalDateTime occupiedTime;
    public Vehicle vechile;
    public String ticketId;

    public ActiveTickets(Vehicle vehicle, Plot plot) {
        this.plotOccupied = plot;
        this.vechile = vehicle;
        this.ticketId = UUID.randomUUID().toString();
        this.occupiedTime = LocalDateTime.now();
    }

    public String getTicketId() {
        return this.ticketId;
    }
}
