package jiradesign.strategies.sorting;

import java.util.List;

import jiradesign.entities.Task;

public class TaskSortStrategy implements TaskSort {

    private TaskSort taskSort;

    public TaskSortStrategy(TaskSort sort) {
        this.taskSort = sort;
    }

    @Override
    public void sortTask(List<Task> tasks) {
        this.taskSort.sortTask(tasks);
    }

}
