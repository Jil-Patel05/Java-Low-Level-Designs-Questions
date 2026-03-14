package elavator.States;

import elavator.GlobalEnums.ELEVATOR_STATE;
import elavator.GlobalEnums.REQUEST_TYPE;
import elavator.Entities.Elavator;
import elavator.Entities.Request;

public class UpState implements ElevatorState {

    @Override
    public void addRequest(Elavator el, Request rq) {
        if (rq.getRequestType() == REQUEST_TYPE.INTERNAL) {
            if (rq.getFloor() > el.getCurrentFloor()) {
                el.getUpFloors().add(rq.getFloor());
            } else {
                el.getDownFloors().add(rq.getFloor());
            }
            return;
        }

        // External requests
        if (rq.getElevatorState() == ELEVATOR_STATE.UP && rq.getFloor() >= el.getCurrentFloor()) {
            el.getUpFloors().add(rq.getFloor());
        } else if (rq.getElevatorState() == ELEVATOR_STATE.DOWN) {
            el.getDownFloors().add(rq.getFloor());
        }
    }

    @Override
    public void move(Elavator el) {
        if (el.getUpFloors().isEmpty()) {
            el.setCurrentState(new IdleState());
            return;
        }

        Integer nextFloor = el.getUpFloors().first();
        el.setCurrentFloor(el.getCurrentFloor() + 1);

        if (el.getCurrentFloor() == nextFloor) {
            el.getUpFloors().pollFirst();
        }

        if (el.getUpFloors().isEmpty()) {
            el.setCurrentState(new IdleState());
        }
    }

    @Override
    public ELEVATOR_STATE getState() {
        return ELEVATOR_STATE.UP;
    }

}
