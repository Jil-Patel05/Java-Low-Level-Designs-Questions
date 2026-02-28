package stackoverflowdesign.strategies.emailstrategies;

import stackoverflowdesign.entities.Event;

public class EmailStrategy implements Strategy {

    @Override
    public void notifyUser(Event event) {
        System.out.println(
                "Email notifying to user with User.name " + event.listner.userName + " For Voting type "
                        + event.voteType);
    }

}
