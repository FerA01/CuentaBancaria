package com.cuentabancaria.controlador;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class InputValidator {
    public InputValidator(){}
    public static boolean esTexto(String text){
        boolean esTexto;
        Pattern pattern = Pattern.compile("^[\\p{L} -]+$");
        Matcher matcher = pattern.matcher(text);
        esTexto = matcher.matches();
        return esTexto;
    }
    public static boolean esNumero(String text){
        boolean esSoloNumerico;
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(text);
        esSoloNumerico = matcher.matches();
        return  esSoloNumerico;
    }
    public static boolean esNumeroFlotante(String text, String precisionPostDecimal){
        boolean esNumeroFlotante;
        Pattern pattern = Pattern.compile("^\\d*\\.?\\d{0,"+precisionPostDecimal+"}$");
        Matcher matcher = pattern.matcher(text);
        esNumeroFlotante = matcher.matches();
        return  esNumeroFlotante;
    }
    public static boolean validarEmail(String email){
        Pattern patron = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = patron.matcher(email);
        return matcher.find();
    }
    public static String capitalizarDatos(String dato){ return dato.substring(0, 1).toUpperCase() + dato.substring(1).toLowerCase(); }
    public static String capitalizarTodoLosdatos(String dato){ return dato.toUpperCase(); }
    public static String generarCbu(){
        Random numero = new Random();
        int generado =  (1000 + numero.nextInt((10000+1)-1000));
        return String.valueOf(generado);
    }
}
