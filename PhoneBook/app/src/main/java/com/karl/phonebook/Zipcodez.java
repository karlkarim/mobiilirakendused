package com.karl.phonebook;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Zipcodez implements Serializable {

    private List<Zipcodes> zipcodes =new ArrayList<>();

    public Zipcodez (SQLiteDatabase db){
        try{
            Cursor cursor = db.query(DBHelper.ZTABLE_NAME,DBHelper.ZTABLE_COLUMNS,null,null,null,null,null);
            for(cursor.moveToFirst(); !cursor.isAfterLast();cursor.moveToNext()){
                String code = cursor.getString(DBHelper.ZCOLNO_CODE);
                String city = cursor.getString(DBHelper.ZCOLNO_CITY);
                zipcodes.add(new Zipcodes(code,city));

            }
            cursor.close();
        }catch (Exception ex){zipcodes.clear(); }

    }
    public List<Zipcodes> getZipcodes(){return zipcodes; }
}
