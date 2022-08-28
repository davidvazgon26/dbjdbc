package com.example.jdbc.ConexionesMySQLconClases.Connection;

import com.example.jdbc.ConexionesMySQLconClases.Connection.beans.Admin;
import com.example.jdbc.ConexionesMySQLconClases.Tables.AdminManager;
import com.example.jdbc.ConexionesMySQLconClases.util.InputHelper;

import java.sql.SQLException;

//Ojo, en este ejercicio se estan viendo juntos el Select y el Insert

public class Main {

    public static void main(String[] args) throws SQLException, Exception {
        AdminManager.displayAllRows();  //viene del paquete Tables

        // Inicio para consultar renglon de la tabla admin ingresado por teclado
        int adminId = InputHelper.getIntegerInput("Select a row: ");
        Admin bean = AdminManager.getRow(adminId);
        if (bean == null) {
            System.err.println("No rows were found");
        }else{
            System.out.println("Admin id: " + bean.getAdminId());
            System.out.println("User name: "+ bean.getUserName());
            System.out.println("Password: "+ bean.getPassword());
        }
        //Fin para consultar renglon de la tabla admin ingresado por teclado

        //Inicio de Insertar renglon en la tabla Admin

        Admin beanI = new Admin();
        beanI.setUserName(InputHelper.getInput("User name: "));
        beanI.setPassword(InputHelper.getInput("Password: "));

        boolean result = AdminManager.insert(beanI);

        if (result) System.out.println("Se inserto la linea con el id: "+ beanI.getAdminId());

        // Fin de Insertar renglon en la tabla Admin
    }
}


