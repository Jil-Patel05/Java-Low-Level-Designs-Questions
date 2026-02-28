package vendingmachine.state;

import vendingmachine.VendingMachine;

public class ProductSelect extends State {

    @Override
    public void nextState(VendingMachine vm) {
        vm.setCurrentState(new Payment());
    }

}
