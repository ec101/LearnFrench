package ec101.learnfrench.StatsActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import ec101.learnfrench.FirstActivity.FirstActivity;
import ec101.learnfrench.R;

public class StatisticsActivity extends AppCompatActivity {

    private StatisticsHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        SharedPreferences settings = getSharedPreferences(StatisticsHandler.PREFS_NAME, 0);
        handler = new DefaultStatisticsHandler(settings);

        Button resetStatsButton = (Button) findViewById(R.id.button8);
        View.OnClickListener resetStatsListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetStats();
            }
        };
        resetStatsButton.setOnClickListener(resetStatsListener);

        Button takeTestButton = (Button) findViewById(R.id.button11);
        View.OnClickListener next_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeTest();
            }
        };
        takeTestButton.setOnClickListener(next_listener);

        TextView outputView = (TextView) findViewById(R.id.textView2);
        outputView.setMovementMethod(new ScrollingMovementMethod());
        String generatedReport = generateStatisticsReport();
        outputView.setText(generatedReport, TextView.BufferType.NORMAL);
    }

    private String generateStatisticsReport() {
        return handler.toString();
    }

    private void resetStats() {
        handler.resetStatistics();
    }

    private void takeTest() {
        Intent intent = new Intent(this, FirstActivity.class);
        startActivity(intent);
    }
}
