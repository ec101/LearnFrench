package ec101.learnfrench;

import java.util.List;

/**
 * Created by Emmet on 18/04/2017.
 */

public interface Quiz {

    Question getNextQuestion();

    Answer getExpectedAnswer();

    void setAnswer(Answer answer);

    QuestionsResults getQuizResults();

    boolean isFinalQuestion();

}
