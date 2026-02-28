package stackoverflowdesign.strategies.emailstrategies;

import stackoverflowdesign.Enums.GlobalEnums.NOTIFICATION_SERVICES;
import stackoverflowdesign.entities.Event;

public class Notification {
    private Strategy strategy;

    public Notification() {

    }

    public void notifyUserBasedOnPreference(Event event) {
        for (NOTIFICATION_SERVICES ntAllowd : event.listner.notificationAllowed) {
            switch (ntAllowd) {
                case NOTIFICATION_SERVICES.EMAIL:
                    strategy = new EmailStrategy();
                    break;
                default:
                    strategy = new MobileStrategy();
                    break;
            }
            strategy.notifyUser(event);
        }
    }
}
