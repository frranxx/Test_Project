package com.example.test;

import static android.os.Process.killProcess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FinishActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        Button Finish = findViewById(R.id.Finish_Btn);
        Finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                killProcess(android.os.Process.myPid());
                System.exit(0);
                FinishTest();
            }
        });
    }

    private void FinishTest() {
        Intent intent = new Intent(FinishActivity.this,MainActivity.class);
        startActivity(intent);
    }
}