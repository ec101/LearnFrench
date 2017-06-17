package ec101.learnfrench.Test;

import android.os.Parcelable;

/**
 * Created by Emmet on 05/04/2017.
 */

public interface Question extends Parcelable{

    String getQuestionText();

    Answer getExpectedAnswer();
}
