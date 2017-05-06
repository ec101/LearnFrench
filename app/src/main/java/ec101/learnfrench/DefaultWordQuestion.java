package ec101.learnfrench;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Emmet on 05/04/2017.
 */

public class DefaultWordQuestion implements Question {

    private final Word word;
    private final Answer answer;

    public DefaultWordQuestion(Word word){
        super();
        //TODO - check validity of word
        this.word = word;
        this.answer = new DefaultAnswer(this.word.getFrench());
    }

    private DefaultWordQuestion(Parcel in){
        this.word = in.readParcelable(DefaultWord.class.getClassLoader());
        this.answer = in.readParcelable(DefaultAnswer.class.getClassLoader());
    }

    @Override
    public String getQuestionText() {
        return "What is the french word for "+word.getEnglish()+"?";
    }

    @Override
    public Answer getExpectedAnswer() {
        return this.answer;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.word, flags);
        dest.writeParcelable(this.answer, flags);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public DefaultWordQuestion createFromParcel(Parcel in) {
            return new DefaultWordQuestion(in);
        }

        public DefaultWordQuestion[] newArray(int size) {
            return new DefaultWordQuestion[size];
        }
    };
}
