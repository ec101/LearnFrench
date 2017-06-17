package ec101.learnfrench.Test;

import android.os.Parcelable;

/**
 * Created by Emmet on 05/04/2017.
 */

public interface AnsweredQuestion extends Parcelable {

    Question getQuestion();

    Answer getAnswer();

    boolean isAnswerCorrect();
}
