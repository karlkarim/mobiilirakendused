package com.karl.englishkings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<King> kings =(new Kings()).getKings();
    private TextView from, to;
    private ListView kingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        from = findViewById(R.id.txtFrom);
        to = findViewById(R.id.txtTo);
        kingList = findViewById(R.id.lstKings);

        kingList.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, kings));

        

    }
}
