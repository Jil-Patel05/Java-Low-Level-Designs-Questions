package ATM;

import ATM.GlobalEnums.CASH_TYPE;
import ATM.GlobalEnums.OPERATION_SUPPORTED;
import ATM.entities.Account;
import ATM.entities.BankService;
import ATM.entities.Card;
import ATM.entities.User;

public class ATMDemo {
    public void initializeATM() {
        User user = new User("jil.patel", "1");
        Account acc = new Account(user, "1", 5000);
        Card card = new Card(acc, "11", "1234");
        BankService service = new BankService();
        service.linkCardToAccount(card, acc);
        ATM atm = ATM.getATMinstance(service);
        atm.printMoney();
        atm.addCashToATM(CASH_TYPE.CASH_500, 10);
        atm.addCashToATM(CASH_TYPE.CASH_100, 10);
        atm.addCashToATM(CASH_TYPE.CASH_200, 10);
        atm.addCashToATM(CASH_TYPE.CASH_50, 10);
        atm.addCashToATM(CASH_TYPE.CASH_20, 10);
        atm.addCashToATM(CASH_TYPE.CASH_10, 10);
        atm.addCashToATM(CASH_TYPE.CASH_5, 10);
        atm.addCashToATM(CASH_TYPE.CASH_2, 100);
        atm.addCashToATM(CASH_TYPE.CASH_1, 100);
        atm.insertCard(card);
        atm.enterPIN("1234");
        System.out.println("Opeation started");
        atm.selectOperation(OPERATION_SUPPORTED.WITHDRAW, 851);
        atm.selectOperation(OPERATION_SUPPORTED.CHECK_BALANCE, 0);
        atm.printMoney();
    }
}
