package jiradesign;

import java.time.LocalDateTime;
import java.util.List;

import jiradesign.GlobalEnums.TASK_PRIORITY;
import jiradesign.entities.Task;
import jiradesign.strategies.sorting.PrioritySorting;
import jiradesign.strategies.sorting.TaskSortStrategy;

public class JiraDemo {
    public void initalizeJira() {
        Jira jira = Jira.getInstance();
        jira.createUser("jil.patel", "1");
        jira.createUser("jil.patel2", "2");
        jira.createUser("jil.patel3", "3");
        jira.createUser("jil.patel4", "4");
        jira.createTask("Title", LocalDateTime.now(), "1", "2", TASK_PRIORITY.MEDIUM);
        jira.createTask("Title1", LocalDateTime.now(), "3", "", TASK_PRIORITY.HIGH);
        jira.createTask("Title2", LocalDateTime.now(), "4", "1", TASK_PRIORITY.MEDIUM);

        List<Task> tasks = jira.listTasksByUser("1");
        System.out.println(tasks.size());

        List<Task> sortedTask = jira.searchTasks("Title", new TaskSortStrategy(new PrioritySorting()));
        for (Task task : sortedTask) {
            System.out.println(task.title);
        }
    }
}
