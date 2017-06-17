package ec101.learnfrench.Test;

import android.os.Parcelable;

import java.util.List;

/**
 * Created by Emmet on 05/04/2017.
 */

public interface QuestionsResults extends Parcelable {

    List<AnsweredQuestion> getCorrectlyAnsweredQuestions();

    List<AnsweredQuestion> getIncorrectlyAnsweredQuestions();

    void addAnsweredQuestion(AnsweredQuestion answeredQuestion);
}
