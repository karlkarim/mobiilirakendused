package com.artjomzurbenko.conversion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void on(View view) {
        EditText userValue = findViewById(R.id.etUserInput);
        if (view.getId()== R.id.button)
        {
            userValue.setText("");
        }else if (view.getId() == R.id.button2)

        {
            RadioButton ml = findViewById(R.id.radioButton);

            if(userValue.getText().length()==0)
            {
                Toast.makeText(this,
                        getResources().getString(R.string.toast), Toast.LENGTH_LONG).show();
                return;

            }

            double value = Double.parseDouble(userValue.getText().toString());
            if (ml.isChecked())userValue.setText(String.valueOf(Converter.toKm(value)));
            else userValue.setText(String.valueOf(Converter.toMiles(value)));

        }

    }
}
