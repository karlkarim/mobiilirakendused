package com.karl.englishkings;

public class King {

    private  String name;
    private  int from;
    private  int to;

    //constructor


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

    //modify toString method
    public  String toString(){

        return getName();
        //returns the value that appears for each object int the ListView container
    }

}
