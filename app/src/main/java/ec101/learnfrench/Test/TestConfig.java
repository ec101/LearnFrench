package ec101.learnfrench.Test;

import android.os.Parcelable;

/**
 * Created by Emmet on 17/06/2017.
 */

public interface TestConfig extends Parcelable {

    int getTestSize();

    String[] getSubjects();

}
