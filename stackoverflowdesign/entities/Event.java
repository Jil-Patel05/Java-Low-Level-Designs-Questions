package stackoverflowdesign.entities;

import stackoverflowdesign.Enums.GlobalEnums.VOTE_TYPE;

public class Event {
    public User actioner;
    public User listner;
    public VOTE_TYPE voteType;

    public Event(User actioner, User listner, VOTE_TYPE voteType) {
        this.voteType = voteType;
        this.actioner = actioner;
        this.listner = listner;
    }
}
