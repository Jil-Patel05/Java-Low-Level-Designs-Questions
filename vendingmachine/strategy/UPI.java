package vendingmachine.strategy;

public class UPI implements Strategy {

    @Override
    public void makePayment(double price) {
        System.out.println("Making payment using UPI");
    }

}
