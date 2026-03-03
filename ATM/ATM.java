package ATM;

import ATM.GlobalEnums.CASH_TYPE;
import ATM.GlobalEnums.OPERATION_SUPPORTED;
import ATM.atmstate.ATMState;
import ATM.atmstate.AuthenticatedState;
import ATM.atmstate.HasCardState;
import ATM.atmstate.IdleState;
import ATM.entities.ATMInv;
import ATM.entities.BankService;
import ATM.entities.Card;

public class ATM {
    private static volatile ATM instance;
    private BankService service;
    private ATMInv inv;
    private ATMState currentState;
    private Card insertedCard;

    private ATM(BankService service) {
        inv = new ATMInv();
        this.service = service;
        currentState = new IdleState();
    }

    public static ATM getATMinstance(BankService service) {
        if (instance == null) {
            synchronized (ATM.class) {
                if (instance == null) {
                    instance = new ATM(service);
                }
            }
        }
        return instance;
    }

    public void nextState(ATMState state) {
        this.currentState = state;
    }

    public void addCashToATM(CASH_TYPE type, int number) {
        inv.addCash(type, number);
    }

    public void printMoney() {
        this.inv.printMoney();
    }

    public void insertCard(Card card) {
        // We can use try catch block here to catch exception here
        // if this operation is not for currentState
        if (this.currentState instanceof IdleState) {
            this.insertedCard = card;
            this.currentState.nextState(instance);
        }
    }

    public boolean enterPIN(String pin) {
        if (this.currentState instanceof HasCardState) {
            boolean isPinCorrect = service.validateUser(insertedCard, pin);
            if (!isPinCorrect) {
                return false;
            }
            this.currentState.nextState(instance);
        }
        return true;
    }

    public void selectOperation(OPERATION_SUPPORTED operation, int money) {
        if (this.currentState instanceof AuthenticatedState) {
            if (operation == OPERATION_SUPPORTED.CHECK_BALANCE) {
                double moneyPresent = service.checkBalance(insertedCard);
                System.out.println("Money: " + moneyPresent);
            } else {
                if (service.isSufficientFund(insertedCard, money)) {
                    if (inv.isSufficientFund(money)) {
                        inv.withdrawCash(money);
                        service.withDrawMoney(insertedCard, money);
                        // System.out.println("Here new");
                        // WE CAN NOTIFY THE USER AS WELL BASED ON IT'S PREFRENCE
                        // DISPENCE THE CASH FROM HERE
                    }
                } else {
                    // Machine has not funds
                }
            }
        }
    }
}
