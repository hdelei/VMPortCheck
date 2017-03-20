package com.example.vanderlei.vmportcheck;

/**
 * Created by user on 18/03/2017.
 */

public class Util {
    private static int myValue = 65535;
    private static final int MAX_VALUE = 65535;
    private static final int MIN_VALUE = 0;

    public  static int intTryParse(String value){
            try {
                myValue = Integer.parseInt(value);
            } catch (NumberFormatException e) {

            }

            if (myValue < MIN_VALUE)
                return MIN_VALUE;
            else if (myValue > MAX_VALUE)
                return MAX_VALUE;
            else
                return myValue;
    }

}
