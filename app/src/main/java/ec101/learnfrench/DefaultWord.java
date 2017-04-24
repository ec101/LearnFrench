package ec101.learnfrench;

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
}
