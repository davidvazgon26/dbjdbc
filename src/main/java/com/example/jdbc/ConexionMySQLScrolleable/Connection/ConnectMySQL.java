package com.example.jdbc.ConexionMySQLScrolleable.Connection;

import com.example.jdbc.ConexionMySQLScrolleable.Tables.States;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectMySQL {

    public static void main(String[] args) throws SQLException {

        try (
                Connection conn = DBUtil.getConnection();
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);  //se establece comexion scolleable, es decir puedo ir en cualquier direccion (puedo volver atras) y de solo lectura
                ResultSet rs = stmt.executeQuery("Select stateId, stateName from states limit 5, 30");
        ) {
            States.displayData(rs);

            rs.last();
            System.out.println("La ultima fila es: " + rs.getRow());

            rs.first();
            System.out.println("El primer registro es: " + rs.getString("stateName"));

            rs.absolute(10);
            System.out.println("El registro numero 10 es: "+ rs.getString("stateName"));

        } catch (SQLException e) {
            DBUtil.excepcionesVarias(e);
        }
    }
}


