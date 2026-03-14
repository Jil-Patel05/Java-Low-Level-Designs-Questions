package elavator.States;

import elavator.GlobalEnums.ELEVATOR_STATE;
import elavator.Entities.Elavator;
import elavator.Entities.Request;

public class IdleState implements ElevatorState {

    @Override
    public void addRequest(Elavator el, Request rq) {
        if (rq.getElevatorState() == ELEVATOR_STATE.UP && rq.getFloor() >= el.getCurrentFloor()) {
            el.getUpFloors().add(rq.getFloor());
        }
        if (rq.getElevatorState() == ELEVATOR_STATE.DOWN && rq.getFloor() >= el.getCurrentFloor()) {
            el.getUpFloors().add(rq.getFloor());
        }
    }

    @Override
    public void move(Elavator el) {
        if (!el.getUpFloors().isEmpty()) {
            el.setCurrentState(new UpState());
        }
        if (!el.getDownFloors().isEmpty()) {
            el.setCurrentState(new DownState());
        }
    }

    @Override
    public ELEVATOR_STATE getState() {
        return ELEVATOR_STATE.IDLE;
    }

}
