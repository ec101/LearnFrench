package ec101.learnfrench;

/**
 * Created by Emmet on 05/04/2017.
 */

public interface AnsweredQuestion {

    Question getQuestion();

    Answer getAnswer();

    boolean isAnswerCorrect();
}
