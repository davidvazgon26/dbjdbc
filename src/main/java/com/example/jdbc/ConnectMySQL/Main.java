package com.example.jdbc.ConnectMySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Conexion simple a la BD

public class Main {

    private static final String USERNAME = "dbuser";
    private static final String PASSWORD = "davg";
    private static final String CONN_STRING = "jdbc:mysql://localhost:3306/explorecalifornia";

    public static void main(String[] args) throws SQLException {

       // Class.forName(("com.mysql.jdbc.Driver")); //solo para java 7 y anteriores

        Connection conn = null;

        try {
            conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
            System.out.println("Conexion exitosa");
        } catch (SQLException e) {
            System.err.println(e);
        }finally {
            if(conn!=null) conn.close();
        }


    }
}
