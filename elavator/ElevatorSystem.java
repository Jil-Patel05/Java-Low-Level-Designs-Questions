package elavator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import elavator.Entities.Elavator;
import elavator.Entities.Request;
import elavator.GlobalEnums.ELEVATOR_STATE;
import elavator.GlobalEnums.REQUEST_TYPE;
import elavator.Strategy.ElevatorChoosingStrategy;
import elavator.Strategy.NearestElevatorChoosingStartegy;
import elavator.observers.Display;
import elavator.observers.ElevatorObserver;

public class ElevatorSystem {
    private static volatile ElevatorSystem instance;
    private Map<Integer, Elavator> elevators = new HashMap<>();

    private final ElevatorChoosingStrategy selectionStrategy;
    private final ExecutorService executorService;

    private ElevatorSystem(int numElevators) {
        this.selectionStrategy = new NearestElevatorChoosingStartegy();
        this.executorService = Executors.newFixedThreadPool(numElevators);

        List<Elavator> elevatorList = new ArrayList<>();
        ElevatorObserver elevatorDisplay = new Display(); // Create the observer

        for (int i = 1; i <= numElevators; i++) {
            Elavator elevator = new Elavator(i, 5);
            elevator.addObserver(elevatorDisplay); // Attach the observer
            elevatorList.add(elevator);
            this.elevators.put(i, elevator);
        }
    }

    public static ElevatorSystem getInstance(int numElevators) {
        if (instance == null) {
            synchronized (ElevatorSystem.class) {
                if (instance == null) {
                    instance = new ElevatorSystem(numElevators);
                }
            }
        }
        return instance;
    }

    public void start() {
        for (Elavator elevator : elevators.values()) {
            executorService.submit(elevator);
        }
    }

    // --- Facade Methods ---

    // EXTERNAL Request (Hall Call)
    public void requestElevator(int floor, ELEVATOR_STATE direction) {
        System.out.println("\n>> EXTERNAL Request: User at floor " + floor + " wants to go " + direction);
        Request request = new Request(REQUEST_TYPE.EXTERNAL, direction, floor);

        // Use strategy to find the best elevator
        Optional<Elavator> selectedElevator = selectionStrategy.getElevator(new ArrayList<>(elevators.values()),
                request);

        if (selectedElevator.isPresent()) {
            selectedElevator.get().addRequest(request);
        } else {
            System.out.println("System busy, please wait.");
        }
    }

    // INTERNAL Request (Cabin Call)
    public void selectFloor(int elevatorId, int destinationFloor) {
        System.out.println(
                "\n>> INTERNAL Request: User in Elevator " + elevatorId + " selected floor " + destinationFloor);
        Request request = new Request(REQUEST_TYPE.INTERNAL, ELEVATOR_STATE.IDLE, destinationFloor);

        Elavator elevator = elevators.get(elevatorId);
        if (elevator != null) {
            elevator.addRequest(request);
        } else {
            System.err.println("Invalid elevator ID.");
        }
    }

    public void shutdown() {
        System.out.println("Shutting down elevator system...");
        for (Elavator elevator : elevators.values()) {
            elevator.stopElevator();
        }
        executorService.shutdown();
    }
}
