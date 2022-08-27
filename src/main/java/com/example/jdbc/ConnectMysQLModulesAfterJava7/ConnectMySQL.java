package com.example.jdbc.ConnectMysQLModulesAfterJava7;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//haciendo conexion utilizando el modulo DBUtil para separar la logica y utilizando la sintaxis try-with-resouces
// para java 7 y posteriores en cuanto el cerrado de estados.

public class ConnectMySQL {


    public static void main(String[] args) throws SQLException {

        try (
                Connection conn = DBUtil.getConnection();
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);  //se establece comexion scolleable, es decir puedo ir en cualquier direccion (puedo volver atras) y de solo lectura
                ResultSet rs = stmt.executeQuery("Select * from states");
        ) {
            rs.last();  //me muevo al final
            System.out.println("NUmero de filas: " + rs.getRow());
            System.out.println("Conexion exitosa");
        } catch (SQLException e) {
//            System.err.println(e); // Si quiero algo simple activo esta linea y desactivo la siguiente
            DBUtil.excepcionesVarias(e);  //recuerda que dependiendo de la BD utilizada sera el tipo de codigo, checa la doc de la BD.
        }
    }
}


