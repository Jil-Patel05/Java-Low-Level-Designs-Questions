package stackoverflowdesign.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import stackoverflowdesign.Enums.GlobalEnums.VOTE_TYPE;
import stackoverflowdesign.strategies.emailstrategies.Notification;

public abstract class Post {
    public String id;
    public User user;
    public List<Vote> votes = new ArrayList<>();

    public Post(User user) {
        this.id = UUID.randomUUID().toString();
        this.user = user;
    }

    public void addVote(VOTE_TYPE voteType, User actioner) {
        boolean isUserHasAlreadyVoted = false;
        for (Vote vote : votes) {
            if (vote.user.userName == user.userName) {
                isUserHasAlreadyVoted = true;
                if (voteType != vote.voteType) {
                    vote.voteType = voteType;
                    Event event = new Event(this.user, actioner, voteType);
                    Notification notification = new Notification();
                    notification.notifyUserBasedOnPreference(event);
                }
            }
        }
        if (!isUserHasAlreadyVoted) {
            Vote vote = new Vote(user, voteType);
            this.votes.add(vote);
        }
    }

    public void removeVote(VOTE_TYPE voteType, User user) {
        Vote removedVote = null;
        for (Vote vt : votes) {
            if (vt.user.userName == user.userName) {
                removedVote = vt;
            }
        }
        if (removedVote != null) {
            this.votes.remove(removedVote);
        }
    }
}
