package CarRentalSystem;

public class GlobalEnums {
    public enum RESERVATION_STATE {
        PENDING,
        ACTIVE,
        CANCELED,
        MODIFIED
    }

    public enum CAR_TYPE {
        SEDAN,
        LUX_SEDAN,
        SUV,
        LUX_SUV,
        ECONOMY,
        PREMIUM
    }

    public enum PAYMENT_METHOD {
        UPI,
        CREDIT_CARD
    }

    public enum PAYMENT_STATE {
        FULLFILLED,
        CANCELED,
        PENDING
    }

    public enum PAYMENT_TYPE {
        FULL_PAYMENT,
        TOP_UP
    }
}
