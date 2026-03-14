package elavator.Strategy;

import java.util.List;
import java.util.Optional;

import elavator.Entities.Elavator;
import elavator.Entities.Request;

public class Strategy implements ElevatorChoosingStrategy {
    private ElevatorChoosingStrategy strategy;

    public Strategy(ElevatorChoosingStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public Optional<Elavator> getElevator(List<Elavator> elevators, Request request) {
        return this.strategy.getElevator(elevators, request);
    }

}
