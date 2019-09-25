package com.karl.myapplication;

public class ZipCode {

    private  String code;
    private  String city;

    //Press Alt + Insert

    //constructor
    public ZipCode(String code, String city) {
        this.code = code;
        this.city = city;
    }


    //getters
    public String getCode() {
        return code;
    }

    public String getCity() {
        return city;
    }

    //kirjutab yle nii et me hiljem esitame seda sellisel kujul
    public String toString(){
      return getCode() + " " + getCity();

    }
}
