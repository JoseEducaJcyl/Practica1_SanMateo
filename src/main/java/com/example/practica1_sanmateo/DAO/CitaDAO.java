package com.example.practica1_sanmateo.DAO;

import com.example.practica1_sanmateo.util.R;

import com.example.practica1_sanmateo.domain.Cita;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CitaDAO {

    private Connection conexion;

    public void conectar() throws ClassNotFoundException, SQLException, IOException {
        Properties configuration = new Properties();
        configuration.load(R.getProperties("database.properties"));
        String host = configuration.getProperty("host");
        String port = configuration.getProperty("port");
        String name = configuration.getProperty("name");
        String username = configuration.getProperty("username");
        String password = configuration.getProperty("password");

        Class.forName("com.mysql.cj.jdbc.Driver");
        conexion = DriverManager.getConnection(
                "jdbc:mysql://" + host + ":" + port + "/" + name + "?serverTimezone=UTC",
                username, password);
    }

    public void desconectar() throws SQLException {
        conexion.close();
    }

    public List<Cita> obtenerCitas(int idPaciente) throws SQLException {
        List<Cita> citas = new ArrayList<>();
        String sql = "SELECT idCita, nombreEspecialidad, fecha, idPaciente FROM Citas WHERE idPaciente = ?";

        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setInt(1, idPaciente);
        ResultSet resultado = sentencia.executeQuery();

        while (resultado.next()) {
            Cita cita = new Cita();
            cita.setIdCita(resultado.getInt("idCita"));
            cita.setNombreEspecialidad(resultado.getString("nombreEspecialidad"));
            cita.setFecha(resultado.getDate("fecha"));
            cita.setIdPaciente(resultado.getInt("idPaciente"));
            citas.add(cita);
        }
        return citas;
    }

    public void guardarCita(Cita cita) throws SQLException {
        String sql = "INSERT INTO Citas (nombreEspecialidad, fecha, idPaciente) VALUES (?, ?, ?)";
        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setString(1, cita.getNombreEspecialidad());
        sentencia.setDate(2, cita.getFecha());
        sentencia.setInt(3, cita.getIdPaciente());
        sentencia.executeUpdate();
    }

    public void eliminarCita(int idCita) throws SQLException {
        String sql = "DELETE FROM Citas WHERE idCita = ?";

        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setInt(1, idCita);
        sentencia.executeUpdate();
    }

    public void modificarCita(Cita cita) throws SQLException {
        String sql = "UPDATE Citas SET nombreEspecialidad = ?, fecha = ? WHERE idCita = ?";
        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setString(1, cita.getNombreEspecialidad());
        sentencia.setDate(2, cita.getFecha());
        sentencia.setInt(3, cita.getIdCita());
        sentencia.executeUpdate();
    }
}

