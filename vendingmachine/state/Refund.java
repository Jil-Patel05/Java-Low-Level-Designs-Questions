package vendingmachine.state;

import vendingmachine.VendingMachine;

public class Refund extends State {
    @Override
    public void nextState(VendingMachine vm) {
        vm.setCurrentState(new IdleState());
    }
}
