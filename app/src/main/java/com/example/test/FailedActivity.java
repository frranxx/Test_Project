package com.example.test;
import static android.os.Process.killProcess;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class FailedActivity extends AppCompatActivity {
    float Correct_Ans=0,Wrong_Ans=0,Answered=0,totalQ=5,Passed=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_failed);
        GettingResult();
        TextView Result2_Txt = (TextView) findViewById(R.id.Result2_Txt);
        Result2_Txt.setText("Your Percentage: "+((3*Correct_Ans)-Wrong_Ans)/(totalQ*3)*100+"%");
        TextView Correct2_Txt = (TextView) findViewById(R.id.Correct2_Txt);
        Correct2_Txt.setText("Your Correct Answers: "+(int)Correct_Ans);
        TextView Wrong2_Txt = (TextView) findViewById(R.id.Wrong2_Txt);
        Wrong2_Txt.setText("Your Wrong Answers: "+(int)Wrong_Ans);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView Ans_Txt = (TextView) findViewById(R.id.Ans_Txt);
        Ans_Txt.setText("Total Questions You Answered: "+(int)Answered);
        TextView Passed2_Txt = (TextView) findViewById(R.id.Passed2_Txt);
        Passed2_Txt.setText("Total Questions You Passed: "+(int)Passed);
        Button Try_Again = findViewById(R.id.Try_Again_Btn);
        Try_Again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Try_Again();
                killProcess(android.os.Process.myPid());
                System.exit(0);
            }
        });
    }

    private void Try_Again() {
        Intent intent = new Intent(FailedActivity.this,QuestionActivity.class);
        startActivity(intent);
    }
    private void GettingResult() {
        Intent intent = getIntent();
        Correct_Ans = intent.getIntExtra("Correct", 0);
        Wrong_Ans = intent.getIntExtra("Wrong",0);
        Answered = intent.getIntExtra("Answered",0);
        Passed = intent.getIntExtra("Passed",0);
    }



}