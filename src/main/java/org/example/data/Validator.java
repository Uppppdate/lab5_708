package org.example.data;

public class Validator {
    public static boolean isNull(Object obj){
        return obj == null;
    }

    public static boolean isNull(String input){
        return isNull((Object) input)  || input.isEmpty() || input.equals("null");
    }

    public static boolean checkStreet(String street){
        return !isNull(street)||street.length()<=85;
    }

}
