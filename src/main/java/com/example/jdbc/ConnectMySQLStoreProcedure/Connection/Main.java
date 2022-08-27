package com.example.jdbc.ConnectMySQLStoreProcedure.Connection;

import com.example.jdbc.ConnectMySQLStoreProcedure.Tables.Tours;
import com.example.jdbc.ConnectMySQLStoreProcedure.util.InputHelper;

import java.sql.*;

//Ojo, para este ejercicio se debio crear antes el procedimiento almacenado "SP o Store Procedure" en la BD

public class Main {

    //1 Creo la consulta en SQL y la guardo para utilizarla despues
    private static final String SQL = "{call GetToursByPrice(?)}";

    public static void main(String[] args) throws Exception {

        double maxPrice;  // 2 Creo una variable donde guardare el precio maximo por el que vamos a filtrar en el where como parametro
        try{
            maxPrice = InputHelper.getDoubleInput("Enter a maximum price: "); // 3 le paso una cadena con mensaje al getDoubleInput donde me enviaran la cantidad en double (voy a la clase InputHelper) y el resultado lo guardo en esta variable
        }catch(NumberFormatException e){ // 13 si hubo algun error de tipo de valor que venga de inputHelper aqui lo capturo
            System.err.println("Error: invalid nnumber");
            return; //14 para inicializar maxPrice
        }
        //15 ya con nuestro valor capturado en maxPrice desde el teclado continuamos con una consulta cualquiera...
        ResultSet rs = null; //16 Resultset
        try (
                Connection conn = DBUtil.getConnection(); //17 la conexion a la BD
                CallableStatement stmt = conn.prepareCall(  // 18 Utilizamos PreparedStatement que es el metodo que permite pasar parametros a la consulta
                        SQL, //19 Aqui utilizamos nuestra consulta guardada en una constante
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);  // 20 se establece comexion scolleable, es decir puedo ir en cualquier direccion (puedo volver atras) y de solo lectura
        ) {
            stmt.setDouble(1,maxPrice);  // 21 Aqui mandamos todos nuestros parametros, como solo tenemos uno solo es una linea, pero se pueden incluir todos los que se necesiten
            rs = stmt.executeQuery(); // 22 Lanzamos la consulta
            Tours.displayData(rs); // 23 mandamos la logica para obtener la respuesta a la clase Tours

        } catch (SQLException e) {
            DBUtil.excepcionesVarias(e);  // 24 preparamos para obtener posibles excepciones
        }finally {
            if (rs != null) rs.close(); // 25 cerramos conexiones abiertas que no esten dentro del try() para versiones de java 7 en adelante...
        }
    }
}


