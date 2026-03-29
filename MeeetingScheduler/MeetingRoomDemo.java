package MeeetingScheduler;

import java.time.LocalDateTime;
import java.util.List;

import MeeetingScheduler.Entities.Location;
import MeeetingScheduler.Entities.MeetingRoom;
import MeeetingScheduler.Entities.User;

public class MeetingRoomDemo {
    public void startMeetingRoomInitializer() {
        Location l1 = new Location(2, "Near cafe-1");
        Location l2 = new Location(4, "Near cafe-1");

        MeetingRoom mr1 = new MeetingRoom(0, "room-1", l1, 5);
        MeetingRoom mr2 = new MeetingRoom(1, "room-2", l2, 10);

        User u1 = new User("Jil Patel", "jil.patel@abc.com");
        User u2 = new User("Kathan Patel", "kathan.patel@abc.com");

        MeetingRoomScheduler scheduler = MeetingRoomScheduler.getInstance();
        scheduler.addMeetingRoom(0, "room-1", l1, 5);
        scheduler.addMeetingRoom(1, "room-2", l2, 10);

        List<MeetingRoom> rooms = scheduler.getMeetingRooms(6, LocalDateTime.now().minusHours(2),
                LocalDateTime.now().minusHours(1));
        for (MeetingRoom room : rooms) {
            System.out.println(room.getRoomName());
        }
        scheduler.bookRoom(u2, 1, 1, LocalDateTime.now().minusHours(2),
                LocalDateTime.now().minusHours(1));
        // scheduler.addUsersToMeetingRoom(mr2, null, null);

        rooms = scheduler.getMeetingRooms(6, LocalDateTime.now().minusHours(2),
                LocalDateTime.now().minusHours(1));
        for (MeetingRoom room : rooms) {
            System.out.println(room.getRoomName());
        }
    }
}
