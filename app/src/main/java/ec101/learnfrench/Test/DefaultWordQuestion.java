package ec101.learnfrench.Test;

import android.os.Parcel;
import android.os.Parcelable;

import ec101.learnfrench.Learn.Word;

/**
 * Created by Emmet on 05/04/2017.
 */

public class DefaultWordQuestion implements Question {

    private final Word word;
    private final Answer answer;

    public DefaultWordQuestion(Word word){
        super();
        if(word == null){
            throw new LearnFrenchException("Word should never be null");
        }
        this.word = word;
        this.answer = new DefaultAnswer(this.word.getFrench());
    }

    private DefaultWordQuestion(Parcel in){
        this.word = in.readParcelable(DefaultWord.class.getClassLoader());
        this.answer = in.readParcelable(DefaultAnswer.class.getClassLoader());
    }

    @Override
    public String getQuestionText() {
        StringBuilder builder = new StringBuilder();
        builder.append("What is the french word for ")
                .append(word.getEnglish()).append("? (")
                .append(word.getSubject())
                .append(")");
        return builder.toString();
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

        @Override
        public DefaultWordQuestion createFromParcel(Parcel in) {
            return new DefaultWordQuestion(in);
        }

        @Override
        public DefaultWordQuestion[] newArray(int size) {
            return new DefaultWordQuestion[size];
        }
    };
}
