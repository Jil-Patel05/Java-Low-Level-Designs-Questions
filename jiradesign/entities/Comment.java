package jiradesign.entities;

import java.time.LocalDateTime;
import java.util.UUID;

public class Comment {
    public String id;
    public String comment;
    public User user;
    public LocalDateTime lastEditTime;
    public LocalDateTime createdTime;

    public Comment(User user, String comment) {
        this.user = user;
        this.comment = comment;
        this.id = UUID.randomUUID().toString();
        this.createdTime = LocalDateTime.now();
        this.lastEditTime = LocalDateTime.now();
    }

    public void editComment(User user, String comment) {
        if (this.user != user) {
            // Raise exception of not match user
        }
        this.comment = comment;
        this.lastEditTime = LocalDateTime.now();
    }
}
