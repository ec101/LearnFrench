package ec101.learnfrench.CustomizeActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import ec101.learnfrench.Learn.DefaultLearnableCollection;
import ec101.learnfrench.R;
import ec101.learnfrench.Test.DefaultTestConfig;
import ec101.learnfrench.TestActivity.QuizActivity;

public class CustomizeActivity extends AppCompatActivity {

    private boolean[] selectedSubjects;
    private String[] subjects;
    private NumberPicker numberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize);

        Set<String> subjectsSet = DefaultLearnableCollection.LEARNABLES.getSubjects();
        subjects = subjectsSet.toArray(new String[subjectsSet.size()]);
        selectedSubjects = new boolean[subjects.length];

        numberPicker = (NumberPicker) findViewById(R.id.numberPicker);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(100);

        Button selectSubjectsButton = (Button) findViewById(R.id.button7);
        View.OnClickListener selectSubjectslistener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectSubjects();
            }
        };
        selectSubjectsButton.setOnClickListener(selectSubjectslistener);

        Button takeTestButton = (Button) findViewById(R.id.button6);
        View.OnClickListener next_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeTest();
            }
        };
        takeTestButton.setOnClickListener(next_listener);
    }

    private void selectSubjects() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Subjects");
        builder.setMultiChoiceItems(subjects, selectedSubjects, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked) {
                    selectedSubjects[which] = true;
                } else {
                    selectedSubjects[which] = false;
                }
            }
        });
        builder.setCancelable(false);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        AlertDialog selectSubjectDialog = builder.create();
        selectSubjectDialog.show();
    }

    private void takeTest() {
        int testSize = this.numberPicker.getValue();
        String[] selectedSubjects = getSelectedSubjects();

        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra(QuizActivity.QUIZ_CONFIG, new DefaultTestConfig(testSize, selectedSubjects));
        startActivity(intent);
    }

    private String[] getSelectedSubjects() {
        ArrayList<String> ret = new ArrayList<String>();
        for(int i = 0; i < selectedSubjects.length; i++){
            if(selectedSubjects[i]){
                ret.add(subjects[i]);
            }
        }
        return ret.toArray(new String[ret.size()]);
    }
}
