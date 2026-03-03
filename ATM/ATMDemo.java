package ATM;

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
        atm.insertCard(card);
        atm.enterPIN("1234");
        atm.selectOperation(OPERATION_SUPPORTED.CHECK_BALANCE, 0);
    }
}
