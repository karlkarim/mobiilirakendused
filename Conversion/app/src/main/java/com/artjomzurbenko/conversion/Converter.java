package com.artjomzurbenko.conversion;

public class Converter {
    public  static final double factor = 1.609344;

    public static  double toKm(double miles)
    {
        return miles * factor;
    }

    public  static double toMiles(double kilometers)
    {
        return kilometers / factor;
    }
}
