package com.example.jdbc.querySQLestatica;


import java.sql.*;

public class Main {

    private static final String USERNAME = "dbuser";
    private static final String PASSWORD = "davg";
    private static final String CONN_STRING = "jdbc:mysql://localhost:3306/explorecalifornia";

    public static void main(String[] args) throws SQLException {

        // Class.forName(("com.mysql.jdbc.Driver")); //solo para java 7 y anteriores

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null; //este contiene la respuesta de la BD

        try {
            conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);

            //se establece comexion scolleable, es decir puedo ir en cualquier direccion (puedo volver atras) y de solo lectura
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("Select * from states");

            rs.last();  //me muevo al final
            System.out.println("NUmero de filas: "+ rs.getRow());


            System.out.println("Conexion exitosa");
        } catch (SQLException e) {
            System.err.println(e);
        }finally {
            //No olvidar cerrar las conexiones
            if(rs!=null) rs.close();
            if(stmt!=null) stmt.close();
            if(conn!=null) conn.close();
        }


    }
}

