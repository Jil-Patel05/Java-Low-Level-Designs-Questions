package vendingmachine.state;

import vendingmachine.VendingMachine;

public class IdleState extends State {

    @Override
    public void nextState(VendingMachine vm) {
        vm.setCurrentState(new ProductSelect());
    }

}
