package com.karl.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText city, code;
    private ListView numbers;

    private Zipcodes zipcodes = new Zipcodes();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        city = findViewById(R.id.etCity);
        code = findViewById(R.id.etCode);
        numbers = findViewById(R.id.listView1);
    }


    public void otsi(View view) {

        if (code.length() == 0 && city.length() == 0) {
            Toast.makeText(this, "Enter value!", Toast.LENGTH_LONG).show();
        } else {
            List<ZipCode> List = zipcodes.search(code.getText().toString().trim(), city.getText().toString().trim());
            final ArrayAdapter<ZipCode> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, List);
            numbers.setAdapter(adapter);
            new CountDownTimer(8000, 1000) {

                @Override
                public void onTick(long l) {

                }

                public void onFinish() {
                    code.setText("");
                    city.setText("");
                    code.requestFocus();
                    adapter.clear();
                }
            }.start();
        }
    }
}
