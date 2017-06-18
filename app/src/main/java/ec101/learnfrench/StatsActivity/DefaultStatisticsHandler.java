package ec101.learnfrench.StatsActivity;

import android.content.SharedPreferences;

import ec101.learnfrench.Test.QuestionsResults;

/**
 * Created by Emmet on 18/06/2017.
 */

public class DefaultStatisticsHandler implements StatisticsHandler {

    private SharedPreferences settings;

    public DefaultStatisticsHandler(SharedPreferences settings){
        this.settings = settings;
    }

    @Override
    public void resetStatistics() {
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        editor.commit();
    }

    @Override
    public void updateStatistics(QuestionsResults results) {
        int numTestsTaken = getNumberOfTestsTaken();
        int avgResult = getAverageResult();
        int maxResult = getMaxResult();
        int minResult = getMinResult();
        int avgQuestionsPerTest = getAvgNumberOfQuestionsPerTest();

        numTestsTaken++;

        int result = getResultOfTestAsPercentage(results);

        if(result > maxResult){
            maxResult = result;
        }

        if(minResult == 0 || result < minResult){
            minResult = result;
        }

        avgResult = (avgResult + result) / 2;

        avgQuestionsPerTest = (avgQuestionsPerTest + getSize0fTest(results)) / 2;

        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("tests_taken", numTestsTaken);
        editor.putInt("avg_result", avgResult);
        editor.putInt("max_result", maxResult);
        editor.putInt("min_result", minResult);
        editor.putInt("avg_questions", avgQuestionsPerTest);
        editor.commit();
    }

    private int getSize0fTest(QuestionsResults results){
        return results.getCorrectlyAnsweredQuestions().size() + results.getIncorrectlyAnsweredQuestions().size();
    }

    private int getResultOfTestAsPercentage(QuestionsResults results){
        return (results.getCorrectlyAnsweredQuestions().size() / getSize0fTest(results)) * 100;
    }

    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("# of Tests Taken: ").append(getNumberOfTestsTaken()).append("\n");
        builder.append("Average Result: ").append(getAverageResult()).append("\n");
        builder.append("Max Result: ").append(getMaxResult()).append("\n");
        builder.append("Min Result: ").append(getMinResult()).append("\n");
        builder.append("Average # of questions per test: ").append(getAvgNumberOfQuestionsPerTest()).append("\n");
        return builder.toString();
    }

    private int getNumberOfTestsTaken(){
        return settings.getInt("tests_taken", 0);
    }

    private int getAverageResult(){
        return settings.getInt("avg_result", 0);
    }

    private int getMaxResult(){
        return settings.getInt("max_result", 0);
    }

    private int getMinResult(){
        return settings.getInt("min_result", 0);
    }

    private int getAvgNumberOfQuestionsPerTest(){
        return settings.getInt("avg_questions", 0);
    }
}
