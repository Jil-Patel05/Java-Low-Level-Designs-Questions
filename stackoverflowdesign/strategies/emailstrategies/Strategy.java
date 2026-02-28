package stackoverflowdesign.strategies.emailstrategies;

import stackoverflowdesign.entities.Event;

public interface Strategy {
    public void notifyUser(Event event);
}
