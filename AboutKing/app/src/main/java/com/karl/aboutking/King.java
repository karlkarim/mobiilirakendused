package com.karl.aboutking;

//this is a model class and a resource
public class King {

    //variables
    private String name, text;
    private int from;
    private int to;




    //constructor (ALT + Insert)
    public King(String name, int from, int to,String text) {
        this.name = name;
        this.from = from;
        this.to = to;
        this.text = text;
    }


    //getters
    public String getName() {
        return name;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public String getText() {
        return text;
    }

    //modify ToString method
    public String toString() { return name; }

    public String getKing(){return name + " reiged " +from + "to " + to; }
}
