package ATM.entities;

public class Card {
    private Account account;
    private String id;
    private String pin;

    public Card(Account account, String id, String pin) {
        this.account = account;
        this.id = id;
        this.pin = pin;
    }

    public Account getAccountOfCard() {
        return this.account;
    }

    public boolean authenticateUser(String pin) {
        return this.pin == pin;
    }
}
