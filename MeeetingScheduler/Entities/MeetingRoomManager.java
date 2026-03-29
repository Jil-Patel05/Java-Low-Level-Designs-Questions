package MeeetingScheduler.Entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class MeetingRoomManager {
    public List<MeetingRoom> rooms = new CopyOnWriteArrayList<>();
    public Map<MeetingRoom, List<BookingInfo>> roomBooking = new ConcurrentHashMap<>();

    public void createMeetingRoom(int id, String roomName, Location location, int cap) {
        MeetingRoom room = new MeetingRoom(id, roomName, location, cap);
        rooms.add(room);
    }

    private boolean checkRoomAvailability(List<BookingInfo> bookings, LocalDateTime start, LocalDateTime end) {
        return bookings.stream().anyMatch((booking) -> {
            return !booking.startTime.isAfter(end) && !booking.endTime.isBefore(start);
        });
    }

    public List<MeetingRoom> getMeetingRooms(int cap, LocalDateTime start, LocalDateTime end) {
        List<MeetingRoom> availableRooms = this.rooms.stream().filter((room) -> {
            if (room.capacity >= cap) {
                List<BookingInfo> bookings = roomBooking.getOrDefault(room, List.of());
                boolean isRoomAvailable = checkRoomAvailability(bookings, start, end);
                return isRoomAvailable;
            }
            return false;
        }).collect(Collectors.toList());
        return availableRooms;
    }

    public void bookRoom(User user, int id, int roomId, LocalDateTime start, LocalDateTime end) {
        MeetingRoom room = this.rooms.stream().filter((r) -> r.id == roomId).findFirst().get();
        BookingInfo booking = new BookingInfo(id, user, start, end);
        roomBooking.putIfAbsent(room, new CopyOnWriteArrayList<>());
        roomBooking.get(room).add(booking);
    }

    public void addUsersToMeetingRoom(MeetingRoom room, BookingInfo bookingInfo, List<User> users) {
        bookingInfo.assignUsers(users);
        // Notify this all users for Meeting room time and all thing
    }

}
