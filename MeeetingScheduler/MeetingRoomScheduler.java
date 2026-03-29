package MeeetingScheduler;

import java.time.LocalDateTime;
import java.util.List;
import MeeetingScheduler.Entities.BookingInfo;
import MeeetingScheduler.Entities.Location;
import MeeetingScheduler.Entities.MeetingRoom;
import MeeetingScheduler.Entities.MeetingRoomManager;
import MeeetingScheduler.Entities.User;

public class MeetingRoomScheduler {
    private static volatile MeetingRoomScheduler instance;
    private MeetingRoomManager manager;

    private MeetingRoomScheduler() {
        this.manager = new MeetingRoomManager();
    }

    public static MeetingRoomScheduler getInstance() {
        if (instance == null) {
            synchronized (MeetingRoomScheduler.class) {
                if (instance == null) {
                    instance = new MeetingRoomScheduler();
                }
            }
        }
        return instance;
    }

    public void addMeetingRoom(int id, String roomName, Location location, int cap) {
        this.manager.createMeetingRoom(id, roomName, location, cap);
    }

    public List<MeetingRoom> getMeetingRooms(int cap, LocalDateTime start, LocalDateTime end) {
        return this.manager.getMeetingRooms(cap, start, end);
    }

    public void bookRoom(User user, int id, int roomId, LocalDateTime start, LocalDateTime end) {
        this.manager.bookRoom(user, id, roomId, start, end);
    }

    public void addUsersToMeetingRoom(MeetingRoom room, BookingInfo bookingInfo, List<User> users) {
        this.manager.addUsersToMeetingRoom(room, bookingInfo, users);
    }
}
