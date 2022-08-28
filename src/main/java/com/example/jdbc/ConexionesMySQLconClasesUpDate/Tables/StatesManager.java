package com.example.jdbc.ConexionesMySQLconClasesUpDate.Tables;


import com.example.jdbc.ConexionesMySQLconClasesUpDate.Connection.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StatesManager {

    public static void displayAllRows() throws SQLException {

        String sql = "select adminId, userName, password from admin";

        try(
                Connection conn = DBUtil.getConnection();
                Statement stmt  = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                ){
            while(rs.next()){
                String stateFullName = rs.getString("stateId") + ": "+ rs.getString("stateName");
                System.out.println(stateFullName);
            }
        }
    }
}
