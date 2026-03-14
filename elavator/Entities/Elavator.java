package elavator.Entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import elavator.GlobalEnums.ELEVATOR_STATE;
import elavator.States.ElevatorState;
import elavator.States.IdleState;
import elavator.observers.ElevatorObserver;

public class Elavator implements Runnable {
    private int id;
    private int currentFloor;
    private int weightCapacity;
    private TreeSet<Integer> upFloors = new TreeSet<>();
    private TreeSet<Integer> downFloors = new TreeSet<>();
    private ElevatorState currentState;
    private boolean isRunning = true;
    private final List<ElevatorObserver> observers = new ArrayList<>();

    public Elavator(int id, int weightCapacity) {
        this.id = id;
        this.weightCapacity = weightCapacity;
        this.currentState = new IdleState();
    }

    public void stopElevator() {
        this.isRunning = false;
    }

    public void addObserver(ElevatorObserver observer) {
        this.observers.add(observer);
    }

    public ELEVATOR_STATE getState() {
        return this.currentState.getState();
    }

    public int getCurrentFloor() {
        return this.currentFloor;
    }

    public void setCurrentState(ElevatorState state) {
        this.currentState = state;
    }

    public void setCurrentFloor(int floor) {
        this.currentFloor = floor;
        for (ElevatorObserver obv : observers) {
            obv.displayFloor(floor);
        }
    }

    public void move() {
        this.currentState.move(this);
    }

    public void addRequest(Request rq) {
        this.currentState.addRequest(this, rq);
    }

    public TreeSet<Integer> getUpFloors() {
        return this.upFloors;
    }

    public TreeSet<Integer> getDownFloors() {
        return this.downFloors;
    }

    @Override
    public void run() {
        while (isRunning) {
            this.move();
            try {
                Thread.sleep(1000); // Simulate movement time
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                isRunning = false;
            }
        }
    }
}
