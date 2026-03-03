package ATM.entities;

public class User {
    private String userName;
    private String userId;
    // User has subscribed for some notification here

    public User(String userName, String userId) {
        this.userId = userId;
        this.userName = userName;
    }
}
