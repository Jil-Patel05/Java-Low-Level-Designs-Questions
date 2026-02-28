package stackoverflowdesign.entities;

public class Answer extends Post {
    public String answer;
    public Question question;

    public Answer(String answer, User user, Question question) {
        super(user);
        this.answer = answer;
        this.question = question;
    }
}
