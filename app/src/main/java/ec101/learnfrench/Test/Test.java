package ec101.learnfrench.Test;

/**
 * Created by Emmet on 18/04/2017.
 */

public interface Test {

    Question getNextQuestion();

    void setAnswer(Answer answer);

    QuestionsResults getQuizResults();

    boolean isFinished();

}
