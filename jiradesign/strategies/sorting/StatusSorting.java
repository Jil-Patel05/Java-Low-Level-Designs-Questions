package jiradesign.strategies.sorting;

import java.util.Collections;
import java.util.List;

import jiradesign.entities.Task;

public class StatusSorting implements TaskSort {

    @Override
    public void sortTask(List<Task> tasks) {
        Collections.sort(tasks, (a, b) -> {
            return a.status.compareTo(b.status);
        });
    }

}
