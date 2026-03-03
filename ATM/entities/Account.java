package ATM.entities;

public class Account {
    private User user;
    private String id;
    private double money;

    public Account(User user, String id, double money) {
        this.user = user;
        this.money = money;
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public double checkBalance() {
        return this.money;
    }

    public boolean isSufficientBalance(double moneyToBeDeducted) {
        return this.money >= moneyToBeDeducted;
    }

    public void deductMoney(double moneyToBeDeducted) {
        this.money -= moneyToBeDeducted;
    }
}
