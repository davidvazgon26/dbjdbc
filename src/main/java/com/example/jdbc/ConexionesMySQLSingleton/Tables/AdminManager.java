package com.example.jdbc.ConexionesMySQLSingleton.Tables;


import com.example.jdbc.ConexionesMySQLSingleton.Connection.ConnectionManager;
import com.example.jdbc.ConexionesMySQLSingleton.Connection.DBUtil;
import com.example.jdbc.ConexionesMySQLSingleton.Connection.beans.Admin;

import java.sql.*;

public class AdminManager {

    private static Connection conn = ConnectionManager.getInstance().getConnection();

    public static void displayAllRows() throws SQLException {

        String sql = "select adminId, userName, password FROM admin ";
        try (
             //   Connection conn = DBUtil.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
        ) {
            System.out.println("Admin Table:");

            while (rs.next()) {
                StringBuffer bf = new StringBuffer();
                bf.append(rs.getInt("adminId"));
                bf.append(": ");
                bf.append(rs.getString("userName"));
                bf.append(", ");
                bf.append(rs.getString("password"));
                System.out.println(bf.toString());
            }
        }
    }

    public static Admin getRow(int adminId) throws SQLException {
        String sql = "select * from admin where adminId = ?";
        ResultSet rs = null;


        try (
               // Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setInt(1, adminId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                Admin bean = new Admin(); //La clase auxiliar donde tengo los geterrs y setters
                // mando por set los valores
                bean.setAdminId(adminId);
                bean.setUserName(rs.getString("userName"));
                bean.setPassword(rs.getString("password"));
                return bean;
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            if (rs != null) rs.close();
        }
    }

    public static boolean insert(Admin bean) throws Exception {
        String sql = "INSERT into admin (userName, password)" + "VALUES(?,?)";
        ResultSet keys = null;
        try (
              //  Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            stmt.setString(1, bean.getUserName());
            stmt.setString(2, bean.getPassword());
            int affected = stmt.executeUpdate();  // si tuvo exito devolvera 1
            if (affected == 1) {
                //obteniendo el id del renglon insertado
                keys = stmt.getGeneratedKeys();
                keys.next();
                int newKey = keys.getInt(1);
                //lo coloco en bean para recuperarlo despues
                bean.setAdminId(newKey);
            } else {
                System.err.println("No rows affected");
                return false;
            }
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            if (keys != null) keys.close();
        }
        return true;
    }

    public static boolean update(Admin bean) throws Exception {
        String sql = "SELECT * FROM admin WHERE adminId =? ";

        // System.out.println(sql);
        ResultSet rs = null;
        try (
               // Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(
                        sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_UPDATABLE); // cambios para consulta actualizable
        ) {

//            stmt.setString(1, bean.getUserName());
//            stmt.setString(2,bean.getPassword());
            stmt.setInt(1, bean.getAdminId());

            rs = stmt.executeQuery();  // cambiar update por query

            if (rs.next()) {
                rs.updateString("userName", bean.getUserName());
                rs.updateString("password", bean.getPassword());
                rs.updateRow();
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            if (rs != null) rs.close();
        }
    }

    public static boolean delete(int adminId) throws Exception {

        String sql = "DELETE FROM admin WHERE adminId = ?";

        try (
      //          Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {

            stmt.setInt(1, adminId);
            int affected = stmt.executeUpdate();

            if (affected == 1) return true;
            else return false;

        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }

}
