package vendingmachine.state;

import vendingmachine.VendingMachine;

public class Cancel extends State {
    @Override
    public void nextState(VendingMachine vm) {
        vm.setCurrentState(new IdleState());
    }
}
