package uk.co.example;

import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;
import java.io.IOException;
import java.lang.Math;


public class IntToRomanConverter {
    private final long BASE = 10;
    private static final String message = "Expected as an input parameter of a valid positive integer greater than 0, and less than ";
    public static void main(String[] args) {
        IntToRomanConverter converter = new IntToRomanConverter();
        long limit = converter.getTopLimit();
        try {
            final long value = (args.length > 0) ? Long.parseLong(args[0]) : 0;
            if(!converter.validate(value)){
                System.out.println(message + limit);
                return;
            }
            System.out.println(converter.convert(value));
        } catch (Exception e){
            System.out.println(message + limit);
        }
    }
    private ArrayList<RomanPowerOfTen> romanPowers;
    private long limit;
    public IntToRomanConverter(){
        romanPowers = new ArrayList<>();
        // Read definitions of Roman 'digit character strings' from a json configuration file
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("src/main/resources/definitions/romanNumbers.json"));
            JSONObject jsonObject = (JSONObject)obj;
            JSONArray romanNumbers = (JSONArray) jsonObject.get("romanNumbers");
            Iterator romanNumbersIterator = romanNumbers.iterator();
            while (romanNumbersIterator.hasNext()) {
                Object ob = romanNumbersIterator.next();
                JSONObject romanObject = (JSONObject)ob;
                long unit = (Long) romanObject.get("unit");
                this.limit =  unit * BASE;
                JSONArray romans = (JSONArray) romanObject.get("romans");
                ArrayList<String> romansList = new ArrayList<>();
                Iterator romansIterator = romans.iterator();
                while(romansIterator.hasNext()){
                    romansList.add((String) romansIterator.next());
                }
                RomanPowerOfTen rpt = new RomanPowerOfTen(unit,romansList);
                romanPowers.add(rpt);
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading configuration file");
            System.exit(1);
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("other exeption");
            System.exit(2);
        }
    }
    public String convert(long decimal){
        // I should really check if Math.log!0() is actually quicker than the n iterations in avoids
        final int startIndex = (int) Math.log10(decimal);
        StringBuilder result = new StringBuilder();
        ListIterator<RomanPowerOfTen> iterator = romanPowers.listIterator((int) startIndex < romanPowers.size()? startIndex+1 : romanPowers.size());
        while(iterator.hasPrevious()){
            RomanPowerOfTen rpt = iterator.previous();
            final long unit = rpt.getUnit();
            final long lastUnit = unit * BASE;
            // indexOfString: always a value in the range 0-9
            final int indexOfString = (int) ((decimal % lastUnit) / unit);
            result.append(rpt.getRoman(indexOfString));
        }
        return result.toString();
    }

    public long getTopLimit(){
        return limit;
    }

    public boolean validate(long decimal){
        long limit = getTopLimit();
        if(decimal <= 0 || decimal >= limit){
            return false;
        }
        return true;
    }
}
