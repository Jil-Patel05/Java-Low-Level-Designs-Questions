package stackoverflowdesign.entities;

import java.util.ArrayList;
import java.util.List;

public class Question extends Post {
    public String question;
    public List<Tag> tags = new ArrayList<>();
    public List<Answer> asnwers = new ArrayList<>();
    private Answer acceptedAnwer = null;
    private boolean isQuestionClosed = false;

    public Question(String question, User user, List<Tag> tags) {
        super(user);
        this.tags = tags;
        this.question = question;
    }

    public void addAnswer(Answer ans) {
        this.asnwers.remove(ans);
    }

    public void removeAnswer(Answer ans) {
        this.asnwers.remove(ans);
    }

    public void CloseQuestionViaAcceptedAnwer(Answer answer) {
        this.acceptedAnwer = answer;
        this.isQuestionClosed = true;
    }
}
