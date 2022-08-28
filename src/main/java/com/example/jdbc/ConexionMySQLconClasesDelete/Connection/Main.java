package com.example.jdbc.ConexionMySQLconClasesDelete.Connection;

import com.example.jdbc.ConexionMySQLconClasesDelete.Connection.beans.Admin;
import com.example.jdbc.ConexionMySQLconClasesDelete.Tables.AdminManager;
import com.example.jdbc.ConexionMySQLconClasesDelete.util.InputHelper;

import java.sql.SQLException;

//Ojo, en este ejercicio se estan viendo juntos el Select y el Insert

public class Main {

    public static void main(String[] args) throws SQLException, Exception {
        AdminManager.displayAllRows();  //viene del paquete Tables

      int adminId = InputHelper.getIntegerInput("Selecciona el id ha eliminar");

      if(AdminManager.delete(adminId)){
          System.out.println("Borrado exitoso");
      }else{
          System.err.println("No se encontro el id para su eliminacion");

      }

    }
}


