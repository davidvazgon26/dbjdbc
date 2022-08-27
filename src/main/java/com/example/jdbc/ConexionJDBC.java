package com.example.jdbc;

import java.sql.*;
import java.util.Date;
import java.util.logging.Logger;

//manera mas organizada de hacer la conexion a la bd.

public class ConexionJDBC {
    private Connection conexion = null;

    public static void main(String[] args) {
        try{
            new ConexionJDBC();
        }catch(SQLException e){
            System.err.println(e);
        }
    }

    public ConexionJDBC() throws SQLException {
        try {
            conectar();
         // consulta("Lopez");
            transaccion();
        } finally {
            cerrar();
        }
    }


    public void conectar() throws SQLException{
        String jdbc = "jdbc:mysql://localhost:3306/dbforjdbc";
        conexion = DriverManager.getConnection(jdbc, "dbuser", "davg");

        //pasos para transacciones

        conexion.setAutoCommit(false);
    }
    private void transaccion() throws SQLException {

        final String PROFESOR = "INSERT INTO profesores(id_profesor, nombre, apellidos) VALUES(?,?,?)";
        final String ASIGNATURA = "INSERT INTO asignaturas(id_asignatura, nombre, profesor) VALUES(?,?,?)";

        PreparedStatement profesor = null, asignatura = null;

        try {
            profesor = conexion.prepareStatement(PROFESOR); //aqui va la query
            profesor.setInt(1,50);
            profesor.setString(2,"Pepito");
            profesor.setString(3,"Perez");
            profesor.executeUpdate();

            asignatura = conexion.prepareStatement(ASIGNATURA); // aqui va la query
            asignatura.setInt(1,100);
            asignatura.setString(2, "Fundamentos de Bases de Datos");
            asignatura.setInt(3,50);
            asignatura.executeUpdate();

            conexion.commit();
            System.out.println("ejecucion exitosa");
        } catch (SQLException e) {
            conexion.rollback();
            e.printStackTrace();
        }finally {
            if (profesor != null) profesor.close();
            if (asignatura != null) asignatura.close();
        }


    }

    private void consulta(String apellidos) throws SQLException{
        String query = "Select * from alumnos where apellidos =?"; // consulta preparada contra inyecciones de datos
        PreparedStatement statement = conexion.prepareStatement(query);
        //le paso los parametros en el orden de los signos de ?
        statement.setString(1,apellidos);
        ResultSet set = statement.executeQuery();

        while(set.next()){
            int idAlumno = set.getInt("id_alumno");
            String nombre = set.getString("nombre");
            String ap = set.getString("apellidos");
            Date date = set.getDate("fecha_nac");
            System.out.println("Id: " + idAlumno + " Nombre: "+ nombre + " "+ap + " Fecha nacimiento: "+ date);
        }
        set.close(); // Nunca olvides cerrar tu cursos despues de terminar tu consulta.
        statement.close();
    }
    private void cerrar() throws SQLException{
        if(conexion != null) conexion.close();
    }

    //private static final Logger LOG = LoggerFactory.getLogger(ConexionJDBC.class);

}
