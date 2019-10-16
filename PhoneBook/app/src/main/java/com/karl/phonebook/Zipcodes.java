package com.karl.phonebook;

import java.io.Serializable;

import androidx.annotation.NonNull;

public class Zipcodes implements Serializable {
    private  String code;
    private String city;

    public  Zipcodes(String code,String city){
        this.code=code;
        this.city =city;
    }

    public String getCode() {
        return code;
    }

    public String getCity() {
        return city;
    }


    @Override
    public String toString() {
        return getCode() +""+ getCity();
    }
}
