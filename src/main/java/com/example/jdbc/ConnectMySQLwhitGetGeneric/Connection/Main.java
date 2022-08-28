package com.example.jdbc.ConnectMySQLwhitGetGeneric.Connection;

import com.example.jdbc.ConnectMySQLwhitGetGeneric.Tables.Tours;
import com.example.jdbc.ConnectMySQLwhitGetGeneric.util.InputHelper;

import java.sql.*;

//Ojo, para este ejercicio se debio crear antes el procedimiento almacenado "SP o Store Procedure" en la BD
public class Main {
    private static final String SQL = "{call GetToursWithCountByPrice(?,?)}";

    public static void main(String[] args) throws Exception {

        double maxPrice;
        try{
            maxPrice = InputHelper.getDoubleInput("Enter a maximum price: ");
        }catch(NumberFormatException e){
            System.err.println("Error: invalid number");
            return;
        }
        ResultSet rs = null;
        try (
                Connection conn = DBUtil.getConnection();
                CallableStatement stmt = conn.prepareCall(
                        SQL, //19 Aqui utilizamos nuestra consulta guardada en una constante
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
        ) {
            stmt.setDouble(1,maxPrice);
            stmt.registerOutParameter("total", Types.INTEGER); // Esto es nuevo, en lugar de identificar el parametro por posicion lo identificamos por nombre, tambien se puede
            rs = stmt.executeQuery();

            int nRows = stmt.getInt("total");

            Tours.displayData(rs, nRows); // ahora paso 2 parametros

        } catch (SQLException e) {
            DBUtil.excepcionesVarias(e);
        }finally {
            if (rs != null) rs.close();
        }
    }
}


