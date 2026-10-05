package com.example.practica1_sanmateo.DAO;
import com.example.practica1_sanmateo.domain.Paciente;
import com.example.practica1_sanmateo.util.R;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class PacienteDAO {

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

    public Paciente validarLogin(String email, String password) throws SQLException {
        String sql = "SELECT IdPaciente, DNI, Nombre, Direccion, Telefono " +
                "FROM Paciente WHERE Email = ? AND Password = ?";

        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setString(1, email);
        sentencia.setString(2, password);
        ResultSet resultado = sentencia.executeQuery();

        if (resultado.next()) {
            Paciente p = new Paciente();
            p.setIdPaciente(resultado.getInt("IdPaciente"));
            p.setDni(resultado.getString("DNI"));
            p.setNombre(resultado.getString("Nombre"));
            p.setDireccion(resultado.getString("Direccion"));
            p.setTelefono(resultado.getString("Telefono"));
            return p;
        }
        return null;
    }
}