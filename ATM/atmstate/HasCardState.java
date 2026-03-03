package ATM.atmstate;

import ATM.ATM;

public class HasCardState implements ATMState {

    @Override
    public void nextState(ATM atm) {
        atm.nextState(new AuthenticatedState());
    }

}
