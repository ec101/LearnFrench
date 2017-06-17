package ec101.learnfrench.Test;

import android.os.Parcel;
import android.os.Parcelable;

import ec101.learnfrench.Learn.LearnableType;
import ec101.learnfrench.Learn.Word;

/**
 * Created by Emmet on 05/04/2017.
 */

public class DefaultWord implements Word {

    private final String english;
    private final String french;
    private final String subject;

    public DefaultWord(String subject, String english, String french){
        super();
        if(subject == null || subject.isEmpty()){
            throw new LearnFrenchException("The word's subject should never be null or empty");
        }
        if(english == null || english.isEmpty()){
            throw new LearnFrenchException("English word should never be null or empty");
        }
        if(french == null || french.isEmpty()){
            throw new LearnFrenchException("French word should never be null or empty");
        }
        this.subject = subject;
        this.english = english;
        this.french = french;
    }

    private DefaultWord(Parcel in){
        this.subject = in.readString();
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
    public String getSubject() {
        return subject;
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
        dest.writeString(this.subject);
        dest.writeString(this.english);
        dest.writeString(this.french);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        @Override
        public DefaultWord createFromParcel(Parcel in) {
            return new DefaultWord(in);
        }

        @Override
        public DefaultWord[] newArray(int size) {
            return new DefaultWord[size];
        }
    };
}
