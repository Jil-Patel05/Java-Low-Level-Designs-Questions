package elavator.Strategy;

import java.util.List;
import java.util.Optional;

import elavator.Entities.Elavator;
import elavator.Entities.Request;
import elavator.GlobalEnums.ELEVATOR_STATE;

public class NearestElevatorChoosingStartegy implements ElevatorChoosingStrategy {

    @Override
    public Optional<Elavator> getElevator(List<Elavator> elevators, Request request) {
        Elavator bestElavator = null;
        int minDistance = Integer.MAX_VALUE;

        for (Elavator el : elevators) {
            if (el.getState() == ELEVATOR_STATE.IDLE) {
                int dis = el.getCurrentFloor() - request.getFloor();
                if (dis < minDistance) {
                    minDistance = dis;
                    bestElavator = el;
                }
            } else if (el.getState() == ELEVATOR_STATE.UP && request.getFloor() >= el.getCurrentFloor()) {
                int dis = el.getCurrentFloor() - request.getFloor();
                if (dis < minDistance) {
                    minDistance = dis;
                    bestElavator = el;
                }
            } else if (el.getState() == ELEVATOR_STATE.DOWN && request.getFloor() <= el.getCurrentFloor()) {
                int dis = el.getCurrentFloor() - request.getFloor();
                if (dis < minDistance) {
                    minDistance = dis;
                    bestElavator = el;
                }
            }
        }

        return Optional.ofNullable(bestElavator);
    }

}
