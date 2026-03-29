package MeeetingScheduler.Entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BookingInfo {
    public int id;
    public User user;
    public LocalDateTime startTime;
    public LocalDateTime endTime;
    public List<User> users = new ArrayList<>();

    public BookingInfo(int id, User user, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void assignUsers(List<User> users) {
        this.users = users;
    }
}
