package CarRentalSystem.Entities;

import CarRentalSystem.GlobalEnums.PAYMENT_METHOD;
import CarRentalSystem.GlobalEnums.PAYMENT_STATE;
import CarRentalSystem.GlobalEnums.PAYMENT_TYPE;

public class Payment {
    public int id;
    public PAYMENT_METHOD paymentMethod;
    public PAYMENT_STATE paymentState;
    public PAYMENT_TYPE paymentType;
    public double amount;
    public int userId;
    public int resId;

    public Payment(int id, PAYMENT_METHOD paymentMethod, PAYMENT_TYPE type, double amout, int userId, int resId) {
        this.id = id;
        this.paymentMethod = paymentMethod;
        this.paymentType = type;
        this.amount = amout;
        this.userId = userId;
        this.resId = resId;
        this.paymentState = PAYMENT_STATE.PENDING;
    }

    public void setPaymentState(PAYMENT_STATE state) {
        this.paymentState = state;
    }
}
