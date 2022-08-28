package com.example.jdbc.ConnectMySQLSP2.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    private static final String USERNAME = "dbuser";
    private static final String PASSWORD = "davg";
    private static final String CONN_STRING = "jdbc:mysql://localhost:3306/explorecalifornia";

    public static Connection getConnection() throws SQLException {

                return DriverManager.getConnection(CONN_STRING,USERNAME,PASSWORD);
    }
    public static void excepcionesVarias(SQLException e){ //Esta parte es opcional, sirve para obtener mas detalle de una posible excepcion.
        System.err.println("Mensaje de error: "+ e.getMessage());
        System.err.println("Codigo de error: "+ e.getErrorCode());
        System.err.println("Estado del error: " + e.getSQLState());
    }
}
