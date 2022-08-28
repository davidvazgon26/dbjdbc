package com.example.jdbc.ConexionMySQLUpdatalbeRS.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class InputHelper {
    public static String getInput(String prompt){
        BufferedReader stdin  = new BufferedReader(new InputStreamReader(System.in)); // 6 Creo un buffer para obtener lo que escriban por consola

        System.out.println(prompt); // 7 Hasta aca utilizo la cadena que viene desde el main
        System.out.flush(); // 8 aun no se exactamente que hace pero se que limpia algo...

        try{
            return stdin.readLine();  // 9 Si ingreso algun valor aqui lo obtenemos y lo retorno a la variable input del metodo getDoubleInput
        }catch(Exception e){  // 10 Si existe alguna excepcion aqui la capturamos
            return "Error: "+ e.getMessage();
        }
    }

    public static double getDoubleInput(String prompt){  // 4 Aqui entro desde el main con mi cadena
        String input = getInput(prompt); // 5  vuelvo a pasar mi cadena hasta el metodo getInput // 11 obtengo el valor ingresado por teclado
        return Double.parseDouble(input); // 12 retorno el valor hasta main con el formato de double

    }

    public static Integer getIntegerInput(String prompt){
        String input = getInput(prompt);
        return Integer.parseInt(input);
    }
}
