package vendingmachine.state;

import vendingmachine.VendingMachine;

public abstract class State {
    public abstract void nextState(VendingMachine vm);

    public void cancelOperations(VendingMachine vm) {
        // Perform some operation do data cleanup
        vm.setCurrentState(new Cancel());
    }
}
