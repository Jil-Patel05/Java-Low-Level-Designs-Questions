package jiradesign.strategies.searching;

import java.util.List;

import jiradesign.entities.Task;

public interface TaskSearch {
    public List<Task> search(List<Task> tasks);
}
