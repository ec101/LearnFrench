package ec101.learnfrench;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Emmet on 05/04/2017.
 */

public class DefaultWord implements Word {

    final String english;
    final String french;

    public DefaultWord(String english, String french){
        super();
        //TODO - check that english and french input is valid
        this.english = english;
        this.french = french;
    }

    private DefaultWord(Parcel in){
        this.english = in.readString();
        this.french = in.readString();
    }

    @Override
    public String getFrench() {
        return french;
    }

    @Override
    public String getEnglish() {
        return english;
    }

    @Override
    public LearnableType getLearnableType() {
        return LearnableType.WORD;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.english);
        dest.writeString(this.french);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public DefaultWord createFromParcel(Parcel in) {
            return new DefaultWord(in);
        }

        public DefaultWord[] newArray(int size) {
            return new DefaultWord[size];
        }
    };
}
