package com.karl.phonebook;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {


    public  static  final String ZTABLE_NAME = "zipcodes";
    public  static  final int ZCOLNO_CODE = 0;
    public  static  final int ZCOLNO_CITY = 1;
    public  static  final String[] ZTABLE_COLUMNS = new String[] {"code","city"};
    public  static  final String ATABLE_NAME = "address";
    public  static  final int ACOLNO_ID =0;
    public  static  final int ACOLNO_FIRSTNAME =1;
    public  static  final int ACOLNO_LASTNAME =2;
    public  static  final int ACOLNO_ADDRESS =3;
    public  static  final int ACOLNO_CODE =4;
    public  static  final int ACOLNO_PHONE =5;
    public  static  final int ACOLNO_MAIL =6;
    public  static  final int ACOLNO_DATE =7;
    public  static  final int ACOLNO_TITLE =8;
    public  static  final String [] ATABLE_COLUMNS = new String[]{"id","firstname","lastname","address","code","phone","mail","date","title"};

    private static  final String DBFILENAME = "phonebook.db";
    private  static  final int DBVERSION =1;

    private final Context context;

    private static final String ZINITIAL_SCHEMA = "create table zipcodes (code char primary key," +"city varchar not null)";
    private  static  final  String AINITIAL_SCHEMA = "create table address"+"(id integer primary key autoincrement, firstname varchar not null,lastname varchar,"+
        "address varchar,code char not null,phone varchar,mail varchar, date varchar,title varchar,"+"foreign key (code) references zipcodes(code))";

    public DBHelper(Context context) {
        super(context, DBFILENAME, null, DBVERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ZINITIAL_SCHEMA);
        db.execSQL(AINITIAL_SCHEMA);
        db.execSQL(insertZipcodes());
    }

    private String insertZipcodes(){
        InputStream stream = context.getResources().openRawResource(R.raw.zipcodes);
        StringBuilder builder = new StringBuilder("insert into zipcodes (code, city) values");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))){
            addRow(builder,reader.readLine());
            for (String line = reader.readLine(); line !=null; line=reader.readLine()){
                if(line.length()>0){
                    builder.append(",");
                    addRow(builder,line);
                }
            }

        }catch (Exception ex){}
        return builder.toString();
    }

    private  void addRow(StringBuilder builder,String line){
        String[] elems = line.split(",");
        builder.append("('");
        builder.append(elems[0]);
        builder.append("','");
        builder.append(elems[1]);
        builder.append("')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {



    }
}
