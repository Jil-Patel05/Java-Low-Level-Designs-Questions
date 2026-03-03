package jiradesign;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jiradesign.GlobalEnums.TASK_PRIORITY;
import jiradesign.GlobalEnums.TASK_STATUS;
import jiradesign.entities.Task;
import jiradesign.entities.User;
import jiradesign.strategies.sorting.TaskSortStrategy;

public class Jira {
    private List<Task> tasks = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private static volatile Jira instance;

    private Jira() {

    }

    public static Jira getInstance() {
        if (instance == null) {
            synchronized (Jira.class) {
                if (instance == null) {
                    instance = new Jira();
                }
            }
        }
        return instance;
    }

    public void createUser(String userName, String id) {
        User user = new User(userName, id);
        this.users.add(user);
    }

    public void createTask(String title, LocalDateTime duedate, String userId1, String userId2,
            TASK_PRIORITY priority) {
        // We can Builder Pattern here to Build larger object
        User cr = this.users.stream()
                .filter(u -> u.userId == userId1)
                .findFirst()
                .orElse(null); // Or .orElseThrow(() -> new UserNotFoundException(userId1))

        User as = this.users.stream()
                .filter(u -> u.userId == userId2)
                .findFirst()
                .orElse(null);
        Task task = new Task(title, duedate, cr, as, priority);
        this.tasks.add(task);
    }

    public List<Task> listTasksByUser(String userId) {
        List<Task> taksByUser = new ArrayList<>();
        for (Task task : tasks) {
            if (task.createdBy.userId == userId) {
                taksByUser.add(task);
            }
        }
        return taksByUser;
    }

    public List<Task> listTasksByStatus(TASK_STATUS status) {
        return tasks.stream()
                .filter(task -> task.status == status)
                .collect(Collectors.toList());
    }

    public List<Task> searchTasks(String keyword, TaskSortStrategy sortingStrategy) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.title.contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        sortingStrategy.sortTask(matchingTasks);
        return matchingTasks;
    }
}
