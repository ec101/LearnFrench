package ec101.learnfrench;

/**
 * Created by Emmet on 18/04/2017.
 */

interface Quiz {

    Question getNextQuestion();

    void setAnswer(Answer answer);

    QuestionsResults getQuizResults();

    boolean isFinished();

}
