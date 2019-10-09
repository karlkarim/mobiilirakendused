package com.artjomzurbenko.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Stack;

public class QuestionActivity extends AppCompatActivity {


    int questionNumber;
    Stack<Integer> previousQuestion = new Stack<>();
    public  static  final String SHARED_DATA = "data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        Bundle data = this.getIntent().getExtras();
        displayQuestions(data.getInt("questionNumber"));
    }

    private void displayQuestions(int n) {

        questionNumber = n;
        Resources res = getResources();
        TypedArray questions = res.obtainTypedArray(R.array.questions);
        String[] question = res.getStringArray(questions.getResourceId(n-1,-1));

       if(n == questions.length()) {
           Button next = findViewById(R.id.nextBtn);
           next.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   onNext(view);
               }
           });
           next.setText(getString(R.string.nextButton));

       }

       if(n==1){
           (findViewById(R.id.previousBtn)).setEnabled(false);
       }else { (findViewById(R.id.previousBtn)).setEnabled(true);}

        TextView t = findViewById(R.id.questionNumber);
       t.setText(String.format(getString(R.string.questionNumber),n,questions.length()));
        ((TextView)findViewById(R.id.qDescription)).setText(question[0]);

       for (int i = 1; i <=4 ; ++i)

    }


    public void onNext(View view) {
    }

    public void onBack(View view) {
    }

    public void onSubmit(View view) {
    }
}
