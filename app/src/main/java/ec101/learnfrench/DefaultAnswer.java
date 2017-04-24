package ec101.learnfrench;

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
    @Override
    public String getAnswer() {
        return this.answer;
    }
}
