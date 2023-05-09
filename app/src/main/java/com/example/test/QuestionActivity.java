package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity {

    private TextView Question_txt, Score_txt, Question_Num, Timer;
    private RadioGroup RadioGroup;
    private RadioButton Answer1,Answer2,Answer3,Answer4;
    private Button Confirm_btn;

    int TotalQuestions;
    int Question_Counter=0;

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


        Add_Questions();
         TotalQuestions = Questions_List.size();
        NextQuestion();

}

    private void NextQuestion() {
        if(Question_Counter<TotalQuestions){
              CurrentQuestion = Questions_List.get(Question_Counter);
              Question_txt.setText(CurrentQuestion.getQuestions());
              Answer1.setText(CurrentQuestion.getAnswer1());
              Answer2.setText(CurrentQuestion.getAnswer2());
              Answer3.setText(CurrentQuestion.getAnswer3());
              Answer4.setText(CurrentQuestion.getAnswer4());
              Question_Counter++;
        }
        else{
            finish();
        }
    }

    private void Add_Questions() {
        Questions_List.add(new Questions("2+2=?","8","22","4","0",3));
        Questions_List.add(new Questions("10*10=?","100","1000","10","10000",1));
        Questions_List.add(new Questions("4/4=?","2","3","0","1",4));
        Questions_List.add(new Questions("77+33=?","100","110","90","120",2));
        Questions_List.add(new Questions("(123456789*123456789)*0=?","987654321","A lot","0","9999999999",3));

    }
    }