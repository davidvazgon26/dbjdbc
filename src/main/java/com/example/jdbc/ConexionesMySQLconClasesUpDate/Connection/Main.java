package com.example.jdbc.ConexionesMySQLconClasesUpDate.Connection;

import com.example.jdbc.ConexionesMySQLconClasesUpDate.Connection.beans.Admin;
import com.example.jdbc.ConexionesMySQLconClasesUpDate.Tables.AdminManager;
import com.example.jdbc.ConexionesMySQLconClasesUpDate.util.InputHelper;

import java.sql.SQLException;

//Ojo, en este ejercicio se estan viendo juntos el Select y el Insert

public class Main {

    public static void main(String[] args) throws SQLException, Exception {
        AdminManager.displayAllRows();  //viene del paquete Tables

      int adminId = InputHelper.getIntegerInput("Selecciona el id ha actualizar");

      Admin bean = new AdminManager().getRow(adminId);
      if(bean == null){
          System.err.println("Id no encontrado");
      }

      String password = InputHelper.getInput("Ingresa el nuevo password");
      bean.setPassword(password);

      if(AdminManager.update(bean)){
          System.out.println("Actualizacion exitosa");
      }else{
          System.err.println("fallo la actualizacion");

      }

    }
}


