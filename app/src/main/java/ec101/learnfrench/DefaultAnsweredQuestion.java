package ec101.learnfrench;

/**
 * Created by Emmet on 05/04/2017.
 */

public class DefaultAnsweredQuestion implements AnsweredQuestion {

    private final Question question;
    private final Answer answer;

    public DefaultAnsweredQuestion(Question question, Answer answer){
        super();
        //TODO - check inputs
        this.question = question;
        this.answer = answer;
    }

    @Override
    public Question getQuestion() {
        return this.question;
    }

    @Override
    public Answer getAnswer() {
        return this.answer;
    }

    @Override
    public boolean isAnswerCorrect() {
        return this.question.getExpectedAnswer().equals(this.answer.getAnswer());
    }
}
