package uk.co.example;

import java.util.ArrayList;

public class RomanPowerOfTen {
    private long unit;
    private ArrayList<String> romans;

    public RomanPowerOfTen(long unit, ArrayList<String> romanNames){
        this.unit = unit;
        this.romans = romanNames;
    }
    long getUnit(){
        return unit;
    }
    String getRoman(int stringIndex) {
        return stringIndex >= 0 && (stringIndex < romans.size()) ? romans.get(stringIndex) : "";
    }
}
