package ATM.entities;

import java.util.HashMap;
import java.util.Map;

public class BankService {
    private Map<Card, Account> cardToAccount = new HashMap<>();

    public BankService() {

    }

    public void linkCardToAccount(Card card, Account acc) {
        cardToAccount.put(card, acc);
    }

    public boolean validateUser(Card card, String pin) {
        return card.authenticateUser(pin);
    }

    public double checkBalance(Card card) {
        Account acc = cardToAccount.get(card);
        return acc.checkBalance();
    }

    public boolean isSufficientFund(Card card, int money) {
        Account acc = cardToAccount.get(card);
        return acc.isSufficientBalance(money);
    }

    public void withDrawMoney(Card card, double money) {
        Account acc = cardToAccount.get(card);
        acc.deductMoney(money);
    }
}
