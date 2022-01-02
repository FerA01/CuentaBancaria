package com.cuentabancaria.controlador;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class InputValidator {
    public static boolean textIsLatinButNoPunctuation(String text){
        boolean isTextOnly;
        Pattern pattern = Pattern.compile("^[\\p{L} -]+$");
        Matcher matcher = pattern.matcher(text);
        isTextOnly = matcher.matches();
        return isTextOnly;
    }
    public static boolean textIsNumericOnly(String text){
        boolean isNumericOnly;
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(text);
        isNumericOnly = matcher.matches();
        return  isNumericOnly;
    }
    public static boolean textIsDecimalOnly(String text, String precisionPostDecimal){
        boolean isNumericOnly;
        Pattern pattern = Pattern.compile("^\\d*\\.?\\d{0,"+precisionPostDecimal+"}$");
        Matcher matcher = pattern.matcher(text);
        isNumericOnly = matcher.matches();
        return  isNumericOnly;
    }
}
