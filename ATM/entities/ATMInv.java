package ATM.entities;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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

    public void printMoney() {
        for (CASH_TYPE type : totalCashMap.keySet()) {
            int count = totalCashMap.get(type);
            System.out.println(type.getValue() + " " + count);
        }
    }

    public void withdrawCash(int money) {
        Set<CASH_TYPE> orderedSet = new TreeSet<>((a, b) -> {
            return a.compareTo(b);
        });
        orderedSet.addAll(totalCashMap.keySet());
        for (CASH_TYPE type : orderedSet) {
            int count = totalCashMap.get(type);
            if (money >= type.getValue()) {
                int denomation = money / type.getValue();
                int mnCnt = Math.min(denomation, count);
                count -= mnCnt;
                money -= (type.getValue() * mnCnt);
                totalCashMap.put(type, count);
            }
        }
    }
}
