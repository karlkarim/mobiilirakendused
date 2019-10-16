package com.karl.phonebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private People people;
    private SQLiteDatabase db;
    private DBHelper dbHelper;
    private EditText etCode, etCity;
    private ArrayAdapter<Zipcodes> adapter;
    private Zipcodez zipcodez;
    private ListView lstCodes;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);
        db = dbHelper.getReadableDatabase();

        zipcodez = new Zipcodez(db);

        etCode = findViewById(R.id.etCode);

        lstCodes = findViewById(R.id.lstCodes);

        etCity= findViewById(R.id.etCity);
        registerForContextMenu(lstCodes);
        ondisplayCodes("","");
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuinfo = (AdapterView.AdapterContextMenuInfo)
                item.getMenuInfo();
        Zipcodes zipcodes = adapter.getItem(menuinfo.position);
        switch (item.getItemId()){
            case R.id.show:
                return true;
            case R.id.create:
                return true;
                default:
                    return super.onContextItemSelected(item);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.search){
            Intent search = new Intent(getApplicationContext(), SearchActivity.class);
            startActivity(search);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClear(View view) {
        etCity.setText("");
        etCode.setText("");
    }

    public  void ondisplayCodes(String code,String city)
    {
        lstCodes.setAdapter(adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, getCodes(code, city)));
    }

    private void createPerson(Zipcodes zipcodes){
        Intent create = new Intent(getApplicationContext(),PersonActivity.class);
        create.putExtra("zipcode",zipcodes);
        startActivity(create);
    }

    private void showPeople(Zipcodes zipcodes){
        people = new People(db,zipcodes);
        if(people.getPeople().size()==0){
            Toast.makeText(this,getResources().getString(R.string.ntf),Toast.LENGTH_SHORT.show)
        }else if (people.getPeople().size()==1){
            Intent person = new Intent(getApplicationContext(),PersonActivity.class);
            person.putExtra("person",people)
        }
    }

    public void onSearch(View view) {

        ondisplayCodes(etCode.getText().toString(),etCity.getText().toString());
    }

    private List<Zipcodes> getCodes (String code, String city){
        List<Zipcodes> zipcodeList = new ArrayList<>();
        for(Zipcodes zipcodes : zipcodez.getZipcodes())
            if(zipcodes.getCode().startsWith(code) && zipcodes.getCity().contains(city))zipcodeList.add(zipcodes);
            return  zipcodeList;

    }
}
