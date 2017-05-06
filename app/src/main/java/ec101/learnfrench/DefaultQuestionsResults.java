package ec101.learnfrench;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Emmet on 05/04/2017.
 */

public class DefaultQuestionsResults implements QuestionsResults {

    private List<AnsweredQuestion> correctlyAnsweredQuestions = new ArrayList<>();
    private List<AnsweredQuestion> inCorrectlyAnsweredQuestions = new ArrayList<>();

    public DefaultQuestionsResults(){
        super();
    }

    private DefaultQuestionsResults(Parcel in){
        correctlyAnsweredQuestions = toList(in.readParcelableArray(AnsweredQuestion.class.getClassLoader()));
        inCorrectlyAnsweredQuestions = toList(in.readParcelableArray(AnsweredQuestion.class.getClassLoader()));
    }

    private List<AnsweredQuestion> toList(Parcelable[] parcelableArray){
        ArrayList<AnsweredQuestion> list = new ArrayList<AnsweredQuestion>();
        for(Parcelable parcel : parcelableArray){
            AnsweredQuestion answeredQuestion = (AnsweredQuestion) parcel;
            list.add(answeredQuestion);
        }
        return list;
    }

    @Override
    public List<AnsweredQuestion> getCorrectlyAnsweredQuestions() {
        return correctlyAnsweredQuestions;
    }

    @Override
    public List<AnsweredQuestion> getIncorrectlyAnsweredQuestions() {
        return inCorrectlyAnsweredQuestions;
    }

    @Override
    public void addAnsweredQuestion(AnsweredQuestion answeredQuestion) {
        if(answeredQuestion.isAnswerCorrect()){
            correctlyAnsweredQuestions.add(answeredQuestion);
        }else{
            inCorrectlyAnsweredQuestions.add(answeredQuestion);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        AnsweredQuestion[] correctAnswersArray = this.correctlyAnsweredQuestions.toArray(new AnsweredQuestion[this.correctlyAnsweredQuestions.size()]);
        dest.writeParcelableArray(correctAnswersArray, flags);
        AnsweredQuestion[] incorrectAnswersArray = this.inCorrectlyAnsweredQuestions.toArray(new AnsweredQuestion[this.correctlyAnsweredQuestions.size()]);
        dest.writeParcelableArray(incorrectAnswersArray, flags);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public DefaultQuestionsResults createFromParcel(Parcel in) {
            return new DefaultQuestionsResults(in);
        }

        public DefaultQuestionsResults[] newArray(int size) {
            return new DefaultQuestionsResults[size];
        }
    };
}
