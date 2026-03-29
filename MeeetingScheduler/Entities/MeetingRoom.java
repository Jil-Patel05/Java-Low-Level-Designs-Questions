package MeeetingScheduler.Entities;

public class MeetingRoom {
    public int id;
    public int capacity;
    private String roomName;
    private Location roomLocation;

    public MeetingRoom(int id, String roomName, Location roomLocation, int cap) {
        this.id = id;
        this.roomName = roomName;
        this.roomLocation = roomLocation;
        this.capacity = cap;
    }

    public String getRoomName() {
        return this.roomName;
    }

    public Location getLocation() {
        return this.roomLocation;
    }
}
