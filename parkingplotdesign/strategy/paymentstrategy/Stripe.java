package parkingplotdesign.strategy.paymentstrategy;

public class Stripe implements Payment {

    @Override
    public void makePayment(long charge) {
        System.out.println("Payment is done through the Stripe");
    }

}
