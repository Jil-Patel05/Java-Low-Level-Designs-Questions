package CarRentalSystem.Strategies;

public class UPIMethodStrategy implements PaymentMethodStrategy {

    @Override
    public void makePayment(double amount) {
        System.out.println("Makign Payment Using UPI");
    }

}
