package vendingmachine;

import java.util.ArrayList;
import java.util.List;

import vendingmachine.entities.Product;
import vendingmachine.entities.Shelf;
import vendingmachine.state.Cancel;
import vendingmachine.state.DispenseProduct;
import vendingmachine.state.IdleState;
import vendingmachine.state.Payment;
import vendingmachine.state.ProductSelect;
import vendingmachine.state.State;
import vendingmachine.strategy.PaymentStrategy;
import vendingmachine.strategy.Strategy;

public class VendingMachine {
    public List<Shelf> shelfs = new ArrayList<>();
    public static volatile VendingMachine instance;
    private State currenState;
    private Shelf SelectedShelf;
    private double finalPrice;
    private Strategy paymenStrategy;

    private VendingMachine() {
        this.currenState = new IdleState();
    }

    public static VendingMachine getInstance() {
        if (instance == null) {
            synchronized (VendingMachine.class) {
                if (instance == null) {
                    instance = new VendingMachine();
                }
            }
        }
        return instance;
    }

    public void setCurrentState(State state) {
        this.currenState = state;
    }

    public void doCleanUp() {
        if (this.currenState instanceof Cancel) {
            this.finalPrice = 0;
            this.SelectedShelf = null;
            this.paymenStrategy = null;
            this.currenState.nextState(instance);
        }
    }

    public void selectProduct(String id) {
        try {
            if (this.currenState instanceof IdleState) {
                for (Shelf shelf : shelfs) {
                    if (shelf.id == id) {
                        this.SelectedShelf = shelf;
                        break;
                    }
                }
                if (this.SelectedShelf == null) {
                    throw new Exception("ID is not present in List");
                }
                this.currenState.nextState(instance);
            } else {
                this.currenState.cancelOperations(instance);
                this.doCleanUp();
                throw new Exception("Current state mismatch");
            }

        } catch (Exception ex) {
            System.out.println("Exception occured while selecting product : " + ex);
        }
    }

    private void dispenseProduct(int quantity) {
        if (this.currenState instanceof DispenseProduct) {
            try {
                this.SelectedShelf.quantity -= quantity;
                // Dispense Logic comes
            } catch (Exception ex) {
                this.SelectedShelf.quantity += quantity;
                // Perform Operations on refund state after setting it
                // 1. Set refund state
                // 2. Make function which performs refunds and all
                // 3. Idle state is set
                // 4. Now Machine is on same above state
                // We have to initiate the refund here
                // Raise Exception as well.
            }
        }
    }

    public void makePayment(Strategy strategy, int quantity) {
        if (this.currenState instanceof Payment) {
            try {
                this.paymenStrategy = new PaymentStrategy(strategy);
                this.paymenStrategy.makePayment(finalPrice);
                this.currenState.nextState(instance);
                this.dispenseProduct(quantity);
            } catch (Exception ex) {
                // Payment Failed exception we have to raise redirect it to again payment page
            }
        }
    }

    public void doPayment(int quantity) {
        if (this.currenState instanceof ProductSelect) {
            if (quantity > this.SelectedShelf.quantity) {
                // Raise Exception
            }
            this.finalPrice = this.SelectedShelf.product.price * quantity;
            this.currenState.nextState(instance);
        }
    }

    public void addShelf(Shelf shelf) {
        this.shelfs.add(shelf);
    }

    public void setProductOnShelf(Shelf shelf, Product product) {
        shelf.setproduct(product);
    }

    public void setProductQuantityOnShelf(Shelf shelf, int quantity) {
        shelf.setQuantity(quantity);
    }

    public void removeShelf(Shelf shelf) {
        this.shelfs.remove(shelf);
    }
}
