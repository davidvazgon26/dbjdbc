package com.example.jdbc.ConnectMySQLModules;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    private static final String USERNAME = "dbuser";
    private static final String PASSWORD = "davg";
    private static final String H_CONN_STRING = "jdbc:hsqldb:data/explorecalifornia"; //no esta configurado este servicio
    private static final String CONN_STRING = "jdbc:mysql://localhost:3306/explorecalifornia";

    public static Connection getConnection(DBType dbType) throws SQLException {
        switch (dbType){
            case MYSQL:
                return DriverManager.getConnection(CONN_STRING,USERNAME,PASSWORD);
            case HSQLDB:
                return DriverManager.getConnection(H_CONN_STRING,USERNAME,PASSWORD);
            default:
                return null;
        }
    }

    public static void excepcionesVarias(SQLException e){ //Esta parte es opcional, sirve para obtener mas detalle de una posible excepcion.
        System.err.println("Mensaje de error: "+ e.getMessage());
        System.err.println("Codigo de error: "+ e.getErrorCode());
        System.err.println("Estado del error: " + e.getSQLState());
    }
}
