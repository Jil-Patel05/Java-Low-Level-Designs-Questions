package jiradesign.entities;

import java.util.UUID;

public class History {
    public String id;
    public String changes;
    public User user;

    public History(User user, String changes) {
        this.user = user;
        this.changes = changes;
        this.id = UUID.randomUUID().toString();
    }
}
