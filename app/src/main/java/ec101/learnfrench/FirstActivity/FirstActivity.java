package ec101.learnfrench.FirstActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

import ec101.learnfrench.CustomizeActivity.CustomizeActivity;
import ec101.learnfrench.Learn.LearnableCollection;
import ec101.learnfrench.Learn.LearnableItemsLoader;
import ec101.learnfrench.R;
import ec101.learnfrench.ResultsActivity.ResultsActivity;
import ec101.learnfrench.Test.DefaultTestConfig;
import ec101.learnfrench.TestActivity.QuizActivity;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        Button takeDefaultTestButton = (Button) findViewById(R.id.button3);
        View.OnClickListener takeDefaultTestListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeDefaultTest();
            }
        };
        takeDefaultTestButton.setOnClickListener(takeDefaultTestListener);

        Button takeCustomisedTestButton = (Button) findViewById(R.id.button4);
        View.OnClickListener takeCustomisedTestListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeCustomisedTest();
            }
        };
        takeCustomisedTestButton.setOnClickListener(takeCustomisedTestListener);

        Button viewStatsButton = (Button) findViewById(R.id.button5);
        View.OnClickListener viewStatsListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewStats();
            }
        };
        viewStatsButton.setOnClickListener(viewStatsListener);

        LearnableItemsLoader loader = new LearnableItemsLoader();
        try {
            LearnableCollection learnableCollection = loader.loadAllLearnableItems(getResources());
        } catch (IOException e) {
            Toast message = Toast.makeText(this, "Unable to load words from file", Toast.LENGTH_LONG);
            message.show();
        }
    }

    private void viewStats() {
//        Intent intent = new Intent(this, ResultsActivity.class);
//        startActivity(intent);
    }

    private void takeCustomisedTest() {
        Intent intent = new Intent(this, CustomizeActivity.class);
        startActivity(intent);
    }

    private void takeDefaultTest() {
        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra(QuizActivity.QUIZ_CONFIG, new DefaultTestConfig());
        startActivity(intent);
    }
}
