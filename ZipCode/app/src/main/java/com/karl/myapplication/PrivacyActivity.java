package com.karl.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class PrivacyActivity extends AppCompatActivity {

    //declaring my variables
    private CheckBox agreed;
    private CountDownTimer timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);

        //bonding with the graphical side'
        agreed = findViewById(R.id.checkBox);
        timer = new CountDownTimer(8000,1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                Toast.makeText(PrivacyActivity.this,
                        getResources().getString(R.string.arg), Toast.LENGTH_LONG).show();
                this.start();//this will replay the countdown again

            }
        }.start();

        agreed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = ((CheckBox)view).isChecked();
                if(checked){
                    //this will start a new activity like take user to next page
                    Intent start = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(start);
                    //we want the timer to stop giving us toast message
                    timer.cancel();
                }
            }
        });
    }
}
