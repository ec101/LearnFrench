package ec101.learnfrench.StatsActivity;

import ec101.learnfrench.Test.QuestionsResults;

/**
 * Created by Emmet on 18/06/2017.
 */

public interface StatisticsHandler {

    public static final String PREFS_NAME = "ec101.learnfrench.storageFile";

    void resetStatistics();

    void updateStatistics(QuestionsResults results);

}
