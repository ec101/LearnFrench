package ec101.learnfrench;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Emmet on 05/04/2017.
 */

public class DefaultAnswer implements Answer {

    private final String answer;

    public DefaultAnswer(String answer){
        super();
        //TODO - check input
        this.answer = answer;
    }

    private DefaultAnswer(Parcel in){
        this.answer = in.readString();
    }

    @Override
    public String getAnswer() {
        return this.answer;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.answer);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public DefaultAnswer createFromParcel(Parcel in) {
            return new DefaultAnswer(in);
        }

        public DefaultAnswer[] newArray(int size) {
            return new DefaultAnswer[size];
        }
    };
}
