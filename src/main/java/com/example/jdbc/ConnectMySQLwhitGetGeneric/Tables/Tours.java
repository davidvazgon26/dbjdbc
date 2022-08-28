package com.example.jdbc.ConnectMySQLwhitGetGeneric.Tables;

//Separando la logica de las consultas del main

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;

public class Tours {
    public static void displayData(ResultSet rs , int nRows) throws SQLException { // nuevo parametro

//        rs.last();
//        int nRows = rs.getRow();
//        if (nRows == 0){
//            System.out.println("No tours were found");
//        }else{
//            System.out.println("Number of tours: "+ nRows);
//            rs.beforeFirst();
//        }

        while(rs.next()){
            StringBuffer buffer = new StringBuffer();

            int tourId = rs.getObject("tourId", Integer.class);
            String tourName = rs.getObject("tourName", String.class);
            BigDecimal price = rs.getObject("price", BigDecimal.class);

//            double price = rs.getDouble("price");

            buffer.append("Tour "+ rs.getInt("tourId")+": ");
            buffer.append(rs.getString("tourName"));

            NumberFormat nf = NumberFormat.getCurrencyInstance();
            String formattedPrice = nf.format(price);
            buffer.append(" ("+ formattedPrice + ")");

            System.out.println(buffer.toString());
        }
    }
}
