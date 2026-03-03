package ATM.atmstate;

import ATM.ATM;

public class AuthenticatedState implements ATMState {
    @Override
    public void nextState(ATM atm) {
        atm.nextState(new IdleState());
    }

}
