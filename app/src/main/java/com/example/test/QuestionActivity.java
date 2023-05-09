package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class QuestionActivity extends AppCompatActivity {
    private TextView Question_txt, Question_Num, Timer;
    private RadioGroup RadioGroup;
    private RadioButton Answer1, Answer2, Answer3, Answer4;
    private Button Confirm_btn;
    private static long Start_Time = 20000;
    int TotalQuestions;
    int Question_Counter = 0;
    int Score;
    int Seconds=20000;
    int Second2;
    boolean Answer;
    CountDownTimer Count_Down;


    private Questions CurrentQuestion;


    private List<Questions> Questions_List;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        Questions_List = new ArrayList<>();
        Question_txt = findViewById(R.id.Question_Txt);
        Question_Num = findViewById(R.id.Question_Counter_Txt);
        Timer = findViewById(R.id.Timer_txt);
        RadioGroup = findViewById(R.id.Radio_Group);
        Answer1 = findViewById(R.id.Radio_Btn_1);
        Answer2 = findViewById(R.id.Radio_Btn_2);
        Answer3 = findViewById(R.id.Radio_Btn_3);
        Answer4 = findViewById(R.id.Radio_Btn_4);
        Confirm_btn = findViewById(R.id.Confirm_Btn);
        Timer();

        Add_Questions();
        TotalQuestions = Questions_List.size();
        NextQuestion();

        Confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Answer == false) {
                    if (Answer1.isChecked() || Answer2.isChecked() || Answer3.isChecked() || Answer4.isChecked()) {
                        CheakAnswer();
                        NextQuestion();
                    } else {
                        Toast.makeText(QuestionActivity.this, "Please Select An Answer!", Toast.LENGTH_SHORT).show();
                    }


                }

            }
        });

    }


    private void CheakAnswer() {
        Answer = true;
        RadioButton Selected_Radio_Btn = findViewById(RadioGroup.getCheckedRadioButtonId());
        int Answer_Num = RadioGroup.indexOfChild(Selected_Radio_Btn) + 1;
        if (Answer_Num == CurrentQuestion.getCorrect_Ans_Num()) {
            Score++;
        }


    }

    private void NextQuestion() {

        RadioGroup.clearCheck();

        if (Question_Counter < TotalQuestions) {
            CurrentQuestion = Questions_List.get(Question_Counter);
            Question_txt.setText(CurrentQuestion.getQuestions());
            Answer1.setText(CurrentQuestion.getAnswer1());
            Answer2.setText(CurrentQuestion.getAnswer2());
            Answer3.setText(CurrentQuestion.getAnswer3());
            Answer4.setText(CurrentQuestion.getAnswer4());
            Question_Counter++;
            Question_Num.setText("Question: " + Question_Counter + "/" + TotalQuestions);
            Answer = false;

        } else {
            Intent intent = new Intent(QuestionActivity.this, FinishActivity.class);
            startActivity(intent);
        }
    }

    private void Timer() {
        if (Count_Down != null) {
            Count_Down.cancel();
            Count_Down.start();
        } else {
            Count_Down = new CountDownTimer(Seconds, 1000) {

                public void onTick(long Time_Left) {
                    Timer.setText("00:" + Time_Left / 1000);
                }

                public void onFinish() {
                      Failed();
                }
            }.start();
        }
    }
    private void Failed(){
            Intent intent = new Intent(QuestionActivity.this, FailedActivity.class);
            startActivity(intent);

        }


        private void Add_Questions () {
            Questions_List.add(new Questions("2+2=?", "8", "22", "4", "0", 3));
            Questions_List.add(new Questions("10*10=?", "100", "1000", "10", "10000", 1));
            Questions_List.add(new Questions("4/4=?", "2", "3", "0", "1", 4));
            Questions_List.add(new Questions("77+33=?", "100", "110", "90", "120", 2));
            Questions_List.add(new Questions("2+2*2=?", "8", "2", "6", "4", 3));

        }
    }
