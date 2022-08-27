package com.example.jdbc.obteniendoDatosDeDB.Tables;

//Separando la logica de las consultas del main

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;

public class Tours {

    public static void displayData(ResultSet rs) throws SQLException {
        System.out.println("si llego hasta aca");
        while(rs.next()){
            StringBuffer buffer = new StringBuffer();
            buffer.append("Tour "+ rs.getInt("tourId")+": ");
            buffer.append(rs.getString("tourName"));

            //transformando antes de agregarlo
            double price = rs.getDouble("price");
            NumberFormat nf = NumberFormat.getCurrencyInstance();
            String formattedPrice = nf.format(price);
            buffer.append(" ("+ formattedPrice + ")");


            System.out.println(buffer.toString());
        }
    }
}
