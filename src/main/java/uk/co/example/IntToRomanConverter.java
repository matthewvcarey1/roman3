package uk.co.example;

import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;
import java.io.IOException;
import java.lang.Math;


public class IntToRomanConverter {
    private static final String message = "Expected as an input parameter of a valid positive integer greater than 0, and less than ";
    public static void main(String[] args) {
        IntToRomanConverter converter = new IntToRomanConverter();
        int limit = converter.getTopLimit();
        try {
            final int value = (args.length > 0) ? Integer.parseInt(args[0]) : 0;
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
    private int limit;
    public IntToRomanConverter(){
        romanPowers = new ArrayList<>();
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
                this.limit = (int) unit * 10;
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
    public String convert(int decimal){
        final int startIndex = (int) Math.log10(decimal);
        StringBuilder result = new StringBuilder();
        ListIterator<RomanPowerOfTen> iterator = romanPowers.listIterator(startIndex < romanPowers.size()? startIndex+1 : romanPowers.size());
        while(iterator.hasPrevious()){
            RomanPowerOfTen rpt = iterator.previous();
            final int unit = (int) rpt.getUnit();
            final int lastUnit = unit * 10;
            final int index = (decimal % lastUnit) / unit;
            result.append(rpt.getRoman(index));
        }
        return result.toString();
    }

    public int getTopLimit(){
        return limit;
    }

    public boolean validate(int decimal){
        int limit = getTopLimit();
        if(decimal <= 0 || decimal >= limit){
            return false;
        }
        return true;
    }
}
