package ATM;

public class GlobalEnums {
    public enum CASH_TYPE {
        CASH_500(500),
        CASH_200(200),
        CASH_100(100),
        CASH_50(50),
        CASH_20(20),
        CASH_10(10),
        CASH_5(5),
        CASH_2(2),
        CASH_1(1);

        private final int value;

        private CASH_TYPE(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }

    public enum OPERATION_SUPPORTED {
        WITHDRAW,
        CHECK_BALANCE
    }
}
