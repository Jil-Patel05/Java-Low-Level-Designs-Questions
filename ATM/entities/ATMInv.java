package ATM.entities;

import java.util.HashMap;
import java.util.Map;

import ATM.GlobalEnums.CASH_TYPE;

public class ATMInv {
    private Map<CASH_TYPE, Integer> totalCashMap = new HashMap<>();

    public ATMInv() {

    }

    public void addCash(CASH_TYPE type, int number) {
        totalCashMap.putIfAbsent(type, 0);
        totalCashMap.put(type, totalCashMap.get(type) + number);
    }

    public boolean isSufficientFund(int money) {
        int cashNeedToDeduct = money;
        for (CASH_TYPE type : totalCashMap.keySet()) {
            int count = totalCashMap.get(type);
            if (cashNeedToDeduct >= type.getValue()) {
                int denomation = cashNeedToDeduct / type.getValue();
                int mnCnt = Math.min(denomation, count);
                cashNeedToDeduct -= (type.getValue() * mnCnt);
            }
        }
        return cashNeedToDeduct == 0;
    }

    public void withdrawCash(int money) {
        for (CASH_TYPE type : totalCashMap.keySet()) {
            int count = totalCashMap.get(type);
            if (money >= type.getValue()) {
                int denomation = money / type.getValue();
                int mnCnt = Math.min(denomation, count);
                count -= mnCnt;
                money -= (type.getValue() * mnCnt);
            }
        }
    }
}
