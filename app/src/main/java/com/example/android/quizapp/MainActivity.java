package com.example.android.quizapp;

import android.content.Context;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RatingBar ratingBar;
    TextView scoreTextView;
    RadioGroup r1, r2, r5;
    RadioButton q1a1, q2a2, q5a3;
    CheckBox q3a1, q3a2, q3a3;
    EditText q4a1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ratingBar = findViewById(R.id.rating_bar);
        scoreTextView = findViewById(R.id.scoreTextView);
        r1 = findViewById(R.id.rbg1);
        r2 = findViewById(R.id.rbg2);
        r5 = findViewById(R.id.rbg5);
        q1a1 = findViewById(R.id.q1a1);
        q2a2 = findViewById(R.id.q2a2);
        q3a1 = findViewById(R.id.q3a1);
        q3a2 = findViewById(R.id.q3a2);
        q3a3 = findViewById(R.id.q3a3);
        q4a1 = findViewById(R.id.q4a1);
        q5a3 = findViewById(R.id.q5a3);

    }

    public void submit(View view) {
        int score = 0;

        boolean q1a1Checked = q1a1.isChecked();
        boolean q2a2Checked = q2a2.isChecked();
        boolean q3a1Checked = q3a1.isChecked();
        boolean q3a2Checked = q3a2.isChecked();
        boolean q3a3Checked = q3a3.isChecked();
        String q4a1Text = q4a1.getText().toString().toLowerCase().trim();
        boolean q5a3Checked = q5a3.isChecked();

        if (q1a1Checked) {
            score += 1;
        }
        if (q2a2Checked) {
            score += 1;
        }
        if (q3a1Checked & q3a2Checked & (!q3a3Checked)) {
            score += 1;
        }
        if (q4a1Text.equals("ottawa")) {
            score += 1;
        }
        if (q5a3Checked) {
            score += 1;
        }
        displayScore(score);
    }

    private void displayScore(int number) {
        String zwrot = getString(R.string.scoreText, number, howGood(number));
        scoreTextView.setText(zwrot);

        ratingBar.setVisibility(View.VISIBLE);
        scoreTextView.setVisibility(View.VISIBLE);

//      Float would be usefull if the quiz will have 10 questions (but we would have only 5 stars)
        ratingBar.setRating((float) number);

        // added toast
        Context context = getApplicationContext();
        CharSequence text = getString(R.string.scoreText, number, howGood(number));
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

    }

    private String howGood(int number) {
        if (number < 2) {
            return getString(R.string.dobetter);
        } else if (number < 4) {
            return getString(R.string.gj);
        } else {
            return getString(R.string.exj);
        }
    }


    public void reset(View view) {
        scoreTextView.setText("");
        r1.clearCheck();
        r2.clearCheck();
        r5.clearCheck();

        q3a1.setChecked(false);
        q3a2.setChecked(false);
        q3a3.setChecked(false);

        q4a1.setText("");

        ratingBar.setVisibility(View.GONE);
        scoreTextView.setRawInputType(View.GONE);
    }
}
