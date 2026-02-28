package logginframework.Observers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logginframework.GlobalEnums.LOG_LEVEL;

public class LogSubject {
    private Map<LOG_LEVEL, List<Observer>> observerMapping = new HashMap<>();

    public void addObserver(LOG_LEVEL level, Observer observer) {
        List<Observer> observers = observerMapping.getOrDefault(level, new ArrayList<>());
        observers.add(observer);
        observerMapping.put(level, observers);
    }

    public void notifyObservers(LOG_LEVEL level, String message) {
        List<Observer> observers = observerMapping.getOrDefault(level, new ArrayList<>());
        for (Observer obs : observers) {
            obs.logMessage(message);
        }
    }
}
