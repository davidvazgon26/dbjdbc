package com.example.jdbc.ConnectMySQLModules;

import java.sql.*;

//haciendo conexion utilizando el modulo DBUtil para separar la logica.

public class ConnectMySQL {


    public static void main(String[] args) throws SQLException {

        // Class.forName(("com.mysql.jdbc.Driver")); //solo para java 7 y anteriores

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null; //este contiene la respuesta de la BD
        //nunca olvides al final cerrar estos 3 recursos.

        try {
          //  conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);  //Esta seria la conexion de manera directa.

            conn = DBUtil.getConnection(DBType.MYSQL); //utilizando la conexion modularizada en DBUtil y DBType (enum)

            //se establece comexion scolleable, es decir puedo ir en cualquier direccion (puedo volver atras) y de solo lectura
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("Select * from states");

            rs.last();  //me muevo al final
            System.out.println("NUmero de filas: "+ rs.getRow());


            System.out.println("Conexion exitosa");
        } catch (SQLException e) {
//            System.err.println(e); // Si quiero algo simple activo esta linea y desactivo la siguiente
            DBUtil.excepcionesVarias(e);  //recuerda que dependiendo de la BD utilizada sera el tipo de codigo, checa la doc de la BD.
        }finally {
            //No olvidar cerrar las conexiones
            if(rs!=null) rs.close();
            if(stmt!=null) stmt.close();
            if(conn!=null) conn.close();
        }


    }
}


