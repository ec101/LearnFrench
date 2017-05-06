package ec101.learnfrench;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Emmet on 05/04/2017.
 */

public interface QuestionsResults extends Parcelable {

    List<AnsweredQuestion> getCorrectlyAnsweredQuestions();

    List<AnsweredQuestion> getIncorrectlyAnsweredQuestions();

    void addAnsweredQuestion(AnsweredQuestion answeredQuestion);
}
