package com.example.jdbc.ConexionesMySQLTransactions.Connection;

import com.example.jdbc.ConexionesMySQLTransactions.Connection.beans.Admin;
import com.example.jdbc.ConexionesMySQLTransactions.Tables.AdminManager;
import com.example.jdbc.ConexionesMySQLTransactions.util.InputHelper;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws Exception {

        System.out.println("Starting application");

        ConnectionManager.getInstance().setDBType(DBType.MYSQL);

        AdminManager.displayAllRows();

        int adminId=0;
        try {
            adminId = InputHelper.getIntegerInput("Select a row to update: ");
        } catch (NumberFormatException e) {
            System.err.println("Invalid entry");
        }

        Admin bean = AdminManager.getRow(adminId);
        if (bean == null) {
            System.err.println("Row not found");
            return;
        }

        String password = InputHelper.getInput("Enter new password: ");
        bean.setPassword(password);

        Connection conn = ConnectionManager.getInstance().getConnection(); // Creando una referencia a mi conexion para aceptar o rechazar los movimientos con codigo (mas abajo)
        conn.setAutoCommit(false);

        if (AdminManager.update(bean)) {
            System.out.println("Success!");
        } else
        {
            System.err.println("whoops!");
        }

        //Con estas 2 instrucciones podemos revertir los cambios
//        conn.rollback();
//        System.out.println("Transaction rolled back");

        //estas 2 lineas confirman que queremos hacer el movimiento
        conn.commit();
        System.out.println("Transaccion commitiada");

        ConnectionManager.getInstance().close();
    }
}

