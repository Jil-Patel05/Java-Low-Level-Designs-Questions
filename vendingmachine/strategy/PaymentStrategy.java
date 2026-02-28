package vendingmachine.strategy;

public class PaymentStrategy implements Strategy {

    private Strategy strategy;

    public PaymentStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void makePayment(double price) {
        this.strategy.makePayment(price);
    }

}
