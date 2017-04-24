package ec101.learnfrench;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Emmet on 05/04/2017.
 */

public interface QuestionsResults {

    List<AnsweredQuestion> getCorrectlyAnsweredQuestions();

    List<AnsweredQuestion> getIncorrectlyAnsweredQuestions();

    void addAnsweredQuestion(AnsweredQuestion answeredQuestion);
}
