package vendingmachine.state;

import vendingmachine.VendingMachine;

public class DispenseProduct extends State {

    @Override
    public void nextState(VendingMachine vm) {
        vm.setCurrentState(new IdleState());
    }

}
