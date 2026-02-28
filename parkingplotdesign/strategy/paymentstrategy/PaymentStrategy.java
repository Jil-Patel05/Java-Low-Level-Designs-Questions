package parkingplotdesign.strategy.paymentstrategy;

public class PaymentStrategy implements Payment {
    private Payment paymentMethod;

    public PaymentStrategy(Payment pay) {
        this.paymentMethod = pay;
    }

    @Override
    public void makePayment(long charge) {
        this.paymentMethod.makePayment(charge);
    }
}
