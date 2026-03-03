package ATM.atmstate;

import ATM.ATM;

public class IdleState implements ATMState {

    @Override
    public void nextState(ATM atm) {
        atm.nextState(new HasCardState());
    }

}
