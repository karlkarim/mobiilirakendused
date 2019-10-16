package com.karl.phonebook;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class People implements Serializable {

    private List<Person> people = new ArrayList<>();

    public People (SQLiteDatabase db, Zipcodes zipcode) {
        try {
            String[] params = new String[]{zipcode.getCode()};
            Cursor cursor = db.rawQuery("select * from addresses where code =? ", params);
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                int id = cursor.getInt(DBHelper.ACOLNO_ID);
                String fname = cursor.getString(DBHelper.ACOLNO_FIRSTNAME);
                String lname = cursor.getString(DBHelper.ACOLNO_LASTNAME);
                String addr = cursor.getString(DBHelper.ACOLNO_ADDRESS);
                String phone = cursor.getString(DBHelper.ACOLNO_PHONE);
                String mail = cursor.getString(DBHelper.ACOLNO_MAIL);
                String date = cursor.getString(DBHelper.ACOLNO_DATE);
                String title = cursor.getString(DBHelper.ACOLNO_TITLE);
                people.add(new Person(id, fname, lname, addr, zipcode, phone, mail, date, title));
            }cursor.close();
        } catch (Exception ex){
            people.clear();
        }
    }

    public People(SQLiteDatabase db, String firstname, String lastname, String address, String persontitle){
                try {
                    Cursor cursor = db.query(DBHelper.ATABLE_NAME,DBHelper.ATABLE_COLUMNS,"firstname like ? and lastname like ? and address like ? and title like ?",
                            new String[]{firstname + "%", lastname + "%", "%" + address + "%", "%" + persontitle + "%"}, null, null, null);

                    for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                        int id = cursor.getInt(DBHelper.ACOLNO_ID);
                        String fname = cursor.getString(DBHelper.ACOLNO_FIRSTNAME);
                        String lname = cursor.getString(DBHelper.ACOLNO_LASTNAME);
                        String addr = cursor.getString(DBHelper.ACOLNO_ADDRESS);
                        String code = cursor.getString(DBHelper.ACOLNO_CODE);
                        String phone = cursor.getString(DBHelper.ACOLNO_PHONE);
                        String mail = cursor.getString(DBHelper.ACOLNO_MAIL);
                        String date = cursor.getString(DBHelper.ACOLNO_DATE);
                        String title = cursor.getString(DBHelper.ACOLNO_TITLE);
                        people.add(new Person(id, fname, lname, addr, getZipcode(db, code), phone, mail, date, title));
                    }
                    cursor.close();
                } catch (Exception ex) {
                    people.clear();
                }

            }
            private Zipcodes getZipcode(SQLiteDatabase db,String code){
                Cursor cursor = db.query(DBHelper.ZTABLE_NAME,DBHelper.ZTABLE_COLUMNS,"code = ?",new String[]{code},null,null,null);
                cursor.moveToFirst();
                return new Zipcodes(cursor.getString(DBHelper.ZCOLNO_CODE),cursor.getString(DBHelper.ZCOLNO_CITY));

            }
            public List<Person> getPeople(){return people; }

    }