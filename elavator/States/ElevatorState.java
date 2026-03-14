package elavator.States;

import elavator.Entities.Elavator;
import elavator.Entities.Request;
import elavator.GlobalEnums.ELEVATOR_STATE;

public interface ElevatorState {
    public void addRequest(Elavator el, Request rq);

    public void move(Elavator el);

    public ELEVATOR_STATE getState();
}
