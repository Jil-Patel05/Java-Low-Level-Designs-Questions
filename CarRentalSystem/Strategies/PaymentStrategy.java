package CarRentalSystem.Strategies;

public class PaymentStrategy implements PaymentMethodStrategy {

    public PaymentMethodStrategy strategy;

    public PaymentStrategy(PaymentMethodStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void makePayment(double amount) {
        this.strategy.makePayment(amount);
    }

}
