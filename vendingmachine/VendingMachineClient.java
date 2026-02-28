package vendingmachine;

import vendingmachine.entities.Product;
import vendingmachine.entities.Shelf;
import vendingmachine.strategy.UPI;

public class VendingMachineClient {
    public void runVendingMachine() {

        Product product = new Product("1", 20.0, "Pepsi");
        Shelf shelf = new Shelf("1", 0);
        shelf.setproduct(product);
        shelf.setQuantity(6);

        VendingMachine vm = VendingMachine.getInstance();
        vm.addShelf(shelf);

        vm.selectProduct("1");
        vm.doPayment(4);
        vm.makePayment(new UPI(), 4);
        System.out.println(shelf.quantity);
    }
}
