package com.example.jdbc;

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;


//manera simple o general de hacer la conexion a la base de datos


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainApplication {

	public static void main(String[] args) {

		Connection connection = null;
		String jdbc = "jdbc:mysql://localhost:3306/dbforjdbc";

		try{
			connection = DriverManager.getConnection(jdbc,"dbuser","davg");
			System.out.println("Estamos dentro!!!");
		}catch(SQLException sql){
				sql.printStackTrace();
		}finally {
			if(connection != null){
				try{
					connection.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}

	}

}
