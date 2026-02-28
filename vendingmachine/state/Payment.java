package vendingmachine.state;

import vendingmachine.VendingMachine;

public class Payment extends State {

    @Override
    public void nextState(VendingMachine vm) {
        vm.setCurrentState(new DispenseProduct());
    }

}
