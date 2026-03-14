package elavator.Entities;

import elavator.GlobalEnums.ELEVATOR_STATE;
import elavator.GlobalEnums.REQUEST_TYPE;

public class Request {
    private REQUEST_TYPE type;
    private ELEVATOR_STATE elType;
    private int floor;

    public Request(REQUEST_TYPE rType, ELEVATOR_STATE elType, int floor) {
        this.floor = floor;
        this.elType = this.elType;
        this.type = rType;
    }

    public ELEVATOR_STATE getElevatorState() {
        return this.elType;
    }

    public REQUEST_TYPE getRequestType() {
        return this.type;
    }

    public int getFloor() {
        return this.floor;
    }
}
