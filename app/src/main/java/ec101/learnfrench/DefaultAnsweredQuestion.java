package ec101.learnfrench;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Emmet on 05/04/2017.
 */

public class DefaultAnsweredQuestion implements AnsweredQuestion {

    private final Question question;
    private final Answer answer;

    public DefaultAnsweredQuestion(Question question, Answer answer){
        super();
        if(answer == null){
            throw new LearnFrenchException("Answer should never be null");
        }
        if(question == null){
            throw new LearnFrenchException("Question should never be null");
        }
        this.question = question;
        this.answer = answer;
    }

    private DefaultAnsweredQuestion(Parcel in){
        this.question = in.readParcelable(DefaultWordQuestion.class.getClassLoader());
        if(this.question == null){
            throw new RuntimeException("Question should never be null");
        }
        this.answer = in.readParcelable(DefaultAnswer.class.getClassLoader());
    }

    @Override
    public Question getQuestion() {
        return this.question;
    }

    @Override
    public Answer getAnswer() {
        return this.answer;
    }

    @Override
    public boolean isAnswerCorrect() {
        String expectedAnswer = this.question.getExpectedAnswer().getAnswer().toLowerCase().trim();
        String providedAnswer = this.answer.getAnswer().toLowerCase().trim();
        return expectedAnswer.equals(providedAnswer);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.question, flags);
        dest.writeParcelable(this.answer, flags);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        @Override
        public DefaultAnsweredQuestion createFromParcel(Parcel in) {
            return new DefaultAnsweredQuestion(in);
        }

        @Override
        public DefaultAnsweredQuestion[] newArray(int size) {
            return new DefaultAnsweredQuestion[size];
        }
    };
}
