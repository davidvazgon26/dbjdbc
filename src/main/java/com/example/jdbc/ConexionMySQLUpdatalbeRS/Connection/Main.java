package com.example.jdbc.ConexionMySQLUpdatalbeRS.Connection;

import com.example.jdbc.ConexionMySQLUpdatalbeRS.Connection.beans.Admin;
import com.example.jdbc.ConexionMySQLUpdatalbeRS.Tables.AdminManager;
import com.example.jdbc.ConexionMySQLUpdatalbeRS.util.InputHelper;

import java.sql.SQLException;

//Ojo, en este ejercicio se estan viendo juntos el Select y el Insert

public class Main {

    public static void main(String[] args) throws SQLException, Exception {
        AdminManager.displayAllRows();  //viene del paquete Tables

      int adminId = InputHelper.getIntegerInput("Selecciona el id ha actualizar");

      Admin bean = AdminManager.getRow(adminId);
      if (bean == null){
          System.err.println("Id no encontrado");
          return;
      }

      String password = InputHelper.getInput("Enter new password");
      bean.setPassword(password);

      if(AdminManager.update(bean)){
          System.out.println("Actualizacion exitosa");
      }else{
          System.err.println("Fallo la actualizacion");

      }

    }
}


