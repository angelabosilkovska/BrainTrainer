package com.android.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity2 extends AppCompatActivity {

    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    TextView resultTxtView;
    int score = 0;
    int numberOfQuestions = 0;
    TextView scoreTxtView;
    Button btn0;
    Button btn1;
    Button btn2;
    Button btn3;
    TextView sumTxtView;
    TextView timerTxtView;
    Button playAgainBtn;

    public void chooseAnswer (View view) {
        if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())) {
            resultTxtView.setVisibility(View.VISIBLE);
            resultTxtView.setText("Correct!");
            score++;
        }
        else {
            resultTxtView.setVisibility(View.VISIBLE);
            resultTxtView.setText("Wrong :(");
        }
        numberOfQuestions++;
        scoreTxtView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
        newQuestion();
    }

    public void playAgain(View v) {
        score = 0;
        numberOfQuestions = 0;
        timerTxtView.setText("30s");
        scoreTxtView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
        resultTxtView.setVisibility(View.INVISIBLE);
        playAgainBtn.setVisibility(View.INVISIBLE);

        newQuestion();

        new CountDownTimer(30100, 1000) {

            @Override
            public void onTick(long l) {
                timerTxtView.setText(String.valueOf( l / 1000) + "s");
            }

            @Override
            public void onFinish() {
                resultTxtView.setText("Done!");
                playAgainBtn.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public void newQuestion() {
        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTxtView.setText(Integer.toString(a)+" + "+Integer.toString(b));
        locationOfCorrectAnswer = rand.nextInt(4);
        answers.clear();

        for (int i = 0; i<4; i++){
            if(i == locationOfCorrectAnswer){
                answers.add(a+b);
            }
            else {
                int wrongAnswer = rand.nextInt(41);
                while (wrongAnswer == a+b) {
                    wrongAnswer = rand.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        }
        btn0.setText(Integer.toString(answers.get(0)));
        btn1.setText(Integer.toString(answers.get(1)));
        btn2.setText(Integer.toString(answers.get(2)));
        btn3.setText(Integer.toString(answers.get(3)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        sumTxtView = (TextView) findViewById(R.id.SumTxtView);
        btn0 = findViewById(R.id.button0);
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        resultTxtView = (TextView) findViewById(R.id.resultTxtView);
        scoreTxtView = (TextView) findViewById(R.id.ScoreTxtView);
        timerTxtView = (TextView) findViewById(R.id.timerTxtView);
        playAgainBtn = (Button) findViewById(R.id.playAgainBtn);

        playAgain(findViewById(R.id.timerTxtView));


    }
}