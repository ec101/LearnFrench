package ec101.learnfrench.Learn;

import android.os.Parcelable;

/**
 * Created by Emmet on 05/04/2017.
 */

public interface Learnable extends Parcelable{

    LearnableType getLearnableType();

}
