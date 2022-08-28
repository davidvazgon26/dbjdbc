package com.example.jdbc.ConexionesMySQLTransactions.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {


    public static void excepcionesVarias(SQLException e){ //Esta parte es opcional, sirve para obtener mas detalle de una posible excepcion.
        System.err.println("Mensaje de error: "+ e.getMessage());
        System.err.println("Codigo de error: "+ e.getErrorCode());
        System.err.println("Estado del error: " + e.getSQLState());
    }
}
