package jiradesign.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jiradesign.GlobalEnums.TASK_PRIORITY;
import jiradesign.GlobalEnums.TASK_STATUS;

public class Task {
    public List<Comment> commets = new ArrayList<>();
    public List<History> history = new ArrayList<>();
    public String title;
    public TASK_PRIORITY taskPriority;
    public TASK_STATUS status;
    public LocalDateTime dueDate;
    public User createdBy;
    public User assignedTo;
    public List<User> taskObservers = new ArrayList<>();

    public Task(String title, LocalDateTime duedate, User created, User assigned, TASK_PRIORITY priority) {
        this.title = title;
        this.dueDate = duedate;
        this.createdBy = created;
        this.assignedTo = assigned;
        this.taskPriority = priority;
        this.taskObservers.add(createdBy);
        if (this.assignedTo != null) {
            this.taskObservers.add(this.assignedTo);
        }
    }

    public void changeAssignee(User assignee, User previousUser) {
        if (assignee != null) {
            this.taskObservers.add(assignee);
        }
        // Notify to it's all Assignee
        // Make Event and send Task Observers there, and based on User preference all
        // got notified
        this.assignedTo = assignee;
    }

    public void changeTaskStatus(TASK_STATUS taskStatus) {
        this.status = taskStatus;
        // Notify to it's all Assignee
    }

    public void addComment(Comment comment) {
        this.commets.add(comment);
        // Notify to it's all Assignee
    }

    public void addHistory(History history) {
        // Notify to it's all Assignee
        this.history.add(history);
    }
}
