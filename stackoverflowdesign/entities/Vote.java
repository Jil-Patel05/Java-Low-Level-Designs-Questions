package stackoverflowdesign.entities;

import stackoverflowdesign.Enums.GlobalEnums.VOTE_TYPE;

public class Vote {
    public User user;
    public VOTE_TYPE voteType;

    public Vote(User user, VOTE_TYPE voType) {
        this.user = user;
        this.voteType = voteType;
    }
}
