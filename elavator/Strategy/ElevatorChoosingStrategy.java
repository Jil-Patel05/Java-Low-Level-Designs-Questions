package elavator.Strategy;

import java.util.List;
import java.util.Optional;

import elavator.Entities.Elavator;
import elavator.Entities.Request;

public interface ElevatorChoosingStrategy {
    public Optional<Elavator> getElevator(List<Elavator> elevators, Request request);
}
