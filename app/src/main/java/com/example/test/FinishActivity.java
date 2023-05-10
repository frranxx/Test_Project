package com.example.test;
import static android.os.Process.killProcess;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class FinishActivity extends AppCompatActivity {
     float Correct_Ans=0,Wrong_Ans=0,Answered=0,totalQ=5,Passed=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        GettingResult();
        TextView Result_Txt = (TextView) findViewById(R.id.Result_Txt);
        Result_Txt.setText("Your Percentage: "+((3*Correct_Ans)-Wrong_Ans)/(totalQ*3)*100+"%");
        TextView Correct_Txt = (TextView) findViewById(R.id.Correct_Txt);
        Correct_Txt.setText("Your Correct Answers: "+(int)Correct_Ans);
        TextView Wrong_Txt = (TextView) findViewById(R.id.Wrong_Txt);
        Wrong_Txt.setText("Your Wrong Answers: "+(int)Wrong_Ans);
        TextView Answered_Txt = (TextView) findViewById(R.id.Answered_Txt);
        Answered_Txt.setText("Total Questions You Answered: "+(int)Answered);
        TextView Passed_Txt = (TextView) findViewById(R.id.Passed_Txt);
        Passed_Txt.setText("Total Questions You Passed: "+(int)Passed);
        Button Finish = findViewById(R.id.Finish_Btn);
        Finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                FinishTest();
                killProcess(android.os.Process.myPid());
                System.exit(0);
            }
        });
    }

    private void FinishTest() {
        Intent intent = new Intent(FinishActivity.this,MainActivity.class);
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