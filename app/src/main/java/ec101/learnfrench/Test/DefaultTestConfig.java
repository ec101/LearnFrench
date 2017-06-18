package ec101.learnfrench.Test;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Emmet on 17/06/2017.
 */

public class DefaultTestConfig implements TestConfig {

    private static final int NUMBER_OF_QUESTIONS = 10;

    private final int testSize;
    private final String[] subjects;

    public DefaultTestConfig(){
        this.testSize = 10;
        this.subjects = new String[0];
    }

    public DefaultTestConfig(int testSize, String[] subjects){
        this.testSize = testSize;
        this.subjects = subjects;
    }

    private DefaultTestConfig(Parcel in){
        this.testSize = in.readInt();
        this.subjects = in.createStringArray();
    }

    @Override
    public int getTestSize() {
        return testSize;
    }

    @Override
    public String[] getSubjects() {
        return subjects;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(testSize);
        dest.writeStringArray(subjects);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        @Override
        public DefaultTestConfig createFromParcel(Parcel in) {
            return new DefaultTestConfig(in);
        }

        @Override
        public DefaultTestConfig[] newArray(int size) {
            return new DefaultTestConfig[size];
        }
    };
}
