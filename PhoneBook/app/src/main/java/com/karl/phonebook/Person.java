package com.karl.phonebook;

import java.io.Serializable;

import androidx.annotation.NonNull;

public class Person implements Serializable {
    private int id;
    private String firstname;
    private String  lastname;
    private String aadress;
    private Zipcodes zipcodes;
    private String phone;
    private String mail;
    private String date;
    private String title;



    public String toString() {
        return firstname+" "+lastname;
    }

    public Person(){}

    public Person(String firstname, String lastname, String aadress, Zipcodes zipcodes, String phone, String mail, String date, String title) {
        this.id = 0;
        this.firstname = firstname;
        this.lastname = lastname;
        this.aadress = aadress;
        this.zipcodes = zipcodes;
        this.phone = phone;
        this.mail = mail;
        this.date = date;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAadress() {
        return aadress;
    }

    public void setAadress(String aadress) {
        this.aadress = aadress;
    }

    public Zipcodes getZipcodes() {
        return zipcodes;
    }

    public void setZipcodes(Zipcodes zipcodes) {
        this.zipcodes = zipcodes;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Person(int id, String firstname, String lastname, String aadress, Zipcodes zipcodes, String phone, String mail, String date, String title) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.aadress = aadress;
        this.zipcodes = zipcodes;
        this.phone = phone;
        this.mail = mail;
        this.date = date;
        this.title = title;
    }
}
