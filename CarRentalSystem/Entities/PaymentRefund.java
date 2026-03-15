package CarRentalSystem.Entities;

import CarRentalSystem.GlobalEnums.PAYMENT_STATE;

public class PaymentRefund {
    public int id;
    public int userId;
    public int resId;
    public double amount;
    public String reason;
    public PAYMENT_STATE paymentState;

    public PaymentRefund(int id, double amount, int userId, int resId, String reason) {
        this.id = id;
        this.amount = amount;
        this.userId = userId;
        this.resId = resId;
        this.reason = reason;
        this.paymentState = PAYMENT_STATE.PENDING;
    }

    public void setPaymentState(PAYMENT_STATE state) {
        this.paymentState = state;
    }
}
