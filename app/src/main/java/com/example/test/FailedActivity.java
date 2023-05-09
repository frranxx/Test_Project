package com.example.test;
import androidx.appcompat.app.AppCompatActivity;
import static android.os.Process.killProcess;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FailedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_failed);

        Button Try_Again = findViewById(R.id.Try_Again_Btn);
        Try_Again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                killProcess(android.os.Process.myPid());
                System.exit(0);
                StartTest();
            }
        });
    }

    private void StartTest() {
        Intent intent = new Intent(FailedActivity.this,QuestionActivity.class);
        startActivity(intent);
    }
}