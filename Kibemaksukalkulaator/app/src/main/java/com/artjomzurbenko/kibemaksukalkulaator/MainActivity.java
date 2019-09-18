package com.artjomzurbenko.kibemaksukalkulaator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClear(View view) {
        ((EditText)findViewById(R.id.hind)).setText("");
        ((EditText)findViewById(R.id.yhik)).setText("");
        ((EditText)findViewById(R.id.ilmamaks)).setText("");
        ((EditText)findViewById(R.id.kaibemaks)).setText("");
        ((EditText)findViewById(R.id.kooskaibemaks)).setText("");
        findViewById(R.id.hind).requestFocus();

    }

    public void onOK(View view) {
                try {
                    double price = Double.parseDouble(((EditText)findViewById(R.id.hind)).getText().toString());
                    double items = Double.parseDouble(((EditText)findViewById(R.id.yhik)).getText().toString());

                    if (price > 0 && items > 0)
                    {
                        double excl = price * items;
                        double vat = excl * 0.20;
                        double incl = excl + vat;

                        if(((RadioButton)findViewById(R.id.radioButton2)).isChecked())
                        {
                            vat = excl * 0.20;
                            incl = excl;
                            excl -= vat;

                        }

                        ((EditText)findViewById(R.id.ilmamaks)).setText(NumberFormat.getInstance().format(excl));
                        ((EditText)findViewById(R.id.kaibemaks)).setText(NumberFormat.getInstance().format(vat));
                        ((EditText)findViewById(R.id.kooskaibemaks)).setText(NumberFormat.getInstance().format(incl));

                    }

                } catch (IllegalArgumentException ex){
                    ex.printStackTrace();
                    displayExeptsionMessage(ex.getMessage());
                }


    }

    private void displayExeptsionMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
