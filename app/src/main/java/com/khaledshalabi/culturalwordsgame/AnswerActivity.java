package com.khaledshalabi.culturalwordsgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AnswerActivity extends AppCompatActivity {

    private TextView answerText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);


        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getIntent().getStringExtra("ANSWER")+": ");
        stringBuilder.append(getIntent().getStringExtra("ANSWER_DESCRIBE"));
        answerText = (TextView) findViewById(R.id.answerTextView);
        answerText.setText(stringBuilder);
    }

    public void goBack(View view){
        finish();
    }
}