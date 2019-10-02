package com.kevinsaar.englishkings;

//this is a model class and a resource
public class King {

    //variables
    private String name;
    private int from;
    private int to;


    //constructor (ALT + Insert)
    public King(String name, int from, int to) {
        this.name = name;
        this.from = from;
        this.to = to;
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

    //modify ToString method
    public String toString() {
        return getName();
        // returns the value that appears for each object in the ListView container
    }
}
