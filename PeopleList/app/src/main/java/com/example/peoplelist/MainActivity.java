package com.example.peoplelist;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText firstName;
    EditText lastName;
    Button  btnAddName;
    Button btnClear;
    ListView txtViewList;

    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstName =(EditText)findViewById(R.id.editFirstName);
        firstName.setSelection(0);
        lastName = (EditText)findViewById(R.id.editLastName);
        btnAddName = (Button)findViewById(R.id.btnAdd);
        btnClear = (Button)findViewById(R.id.btnClear);
        txtViewList = (ListView)findViewById(R.id.editListView);

        arrayList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,arrayList);

        txtViewList.setAdapter(adapter);

        onBtnClick();
        onClrClick();
    }

    public void onBtnClick(){
        btnAddName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = firstName.getText().toString() +" "+ lastName.getText().toString();
                arrayList.add(result);
                adapter.notifyDataSetChanged();
                clearFields();
            }
        });


    }

    public  void clearFields(){
        firstName.setText(null);
        lastName.setText(null);

    }

    public void onClrClick(){
        //btnClear.setOnClickListener(new View.OnClickListener() {
            //@Override
            //public void onClick(View v) {
                //arrayList.clear();
                //adapter.notifyDataSetChanged();

            //}
        //});

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                firstName.setText(null);
                lastName.setText(null);

            }
        });




    }


}
