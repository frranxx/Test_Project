package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class QuestionActivity extends AppCompatActivity {
    private TextView Question_txt, Question_Num, Timer;
    private RadioGroup RadioGroup;
    private RadioButton Answer1, Answer2, Answer3, Answer4;
    private Button Confirm_btn,Pass_Btn;
    int TotalQuestions;
    int Question_Counter = 0;
    int Correct_Ans;
    int Wrong_Ans;
    int Answered;
    int Seconds=20000;
    int Passed;
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
        Pass_Btn= findViewById(R.id.Pass_Btn);
        Timer();

        Add_Questions();
        TotalQuestions = Questions_List.size();
        NextQuestion();
        Pass_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Answer == false){
                    if (Answer1.isChecked()||Answer2.isChecked()||Answer3.isChecked()||Answer4.isChecked()){
                        Toast.makeText(QuestionActivity.this, "CHOOSE CONFIRM!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Passed++;
                        NextQuestion();
                    }
                }
            }
        });
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
           Correct_Ans++;
           Answered++;
        }
        else{
            Wrong_Ans++;
            Answered++;
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
            if (Correct_Ans>=3) {
                Count_Down.cancel();
                Intent intent = new Intent(QuestionActivity.this, FinishActivity.class);
                intent.putExtra("Correct", Correct_Ans);
                intent.putExtra("Wrong", Wrong_Ans);
                intent.putExtra("Answered", Answered);
                intent.putExtra("Passed", Passed);
                startActivity(intent);
            }
            else{
                Count_Down.cancel();
                Intent intent = new Intent(QuestionActivity.this, FailedActivity.class);
                intent.putExtra("Correct", Correct_Ans);
                intent.putExtra("Wrong", Wrong_Ans);
                intent.putExtra("Answered", Answered);
                intent.putExtra("Passed", Passed);
                startActivity(intent);
            }
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
                     Timer_Done();
                }
            }.start();
        }
    }
    private void Timer_Done(){
        if (Correct_Ans>=3) {
            Count_Down.cancel();
            Intent intent = new Intent(QuestionActivity.this, FinishActivity.class);
            intent.putExtra("Correct", Correct_Ans);
            intent.putExtra("Wrong", Wrong_Ans);
            intent.putExtra("Answered", Answered);
            intent.putExtra("Passed", Passed);
            startActivity(intent);
        }
        else{
            Count_Down.cancel();
            Intent intent = new Intent(QuestionActivity.this, FailedActivity.class);
            intent.putExtra("Correct", Correct_Ans);
            intent.putExtra("Wrong", Wrong_Ans);
            intent.putExtra("Answered", Answered);
            intent.putExtra("Passed", Passed);
            startActivity(intent);
        }

        }


        private void Add_Questions () {
            Questions_List.add(new Questions("2+2=?", "8", "22", "4", "0", 3));
            Questions_List.add(new Questions("10*10=?", "100", "1000", "10", "10000", 1));
            Questions_List.add(new Questions("4/4=?", "2", "3", "0", "1", 4));
            Questions_List.add(new Questions("77+33=?", "100", "110", "90", "120", 2));
            Questions_List.add(new Questions("2+2*2=?", "8", "2", "6", "4", 3));

        }
    }
