package stackoverflowdesign.entities;

import java.util.ArrayList;
import java.util.List;
import stackoverflowdesign.Enums.GlobalEnums.NOTIFICATION_SERVICES;

public class User {
    public String userName;
    public String email;
    public List<NOTIFICATION_SERVICES> notificationAllowed = new ArrayList<>();

    public User(String userName, String email) {
        this.userName = userName;
        this.email = email;
        notificationAllowed.add(NOTIFICATION_SERVICES.EMAIL);
        notificationAllowed.add(NOTIFICATION_SERVICES.MOBILE);
    }

    public void removeNotificationType(NOTIFICATION_SERVICES nt) {
        this.notificationAllowed.remove(nt);
    }
}
