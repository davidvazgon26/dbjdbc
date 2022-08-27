package com.example.jdbc.ConsultaConParametros.Tables;

//Separando la logica de las consultas del main

import java.sql.ResultSet;
import java.sql.SQLException;

public class States {

    public static void displayData(ResultSet rs) throws SQLException {
        while(rs.next()){

            String id = rs.getString("stateId");
            String name = rs.getString("stateName");

            System.out.println(id+": "+ name);

        }
    }
}
