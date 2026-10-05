package com.example.practica1_sanmateo.Controller;

import com.example.practica1_sanmateo.DAO.CitaDAO;
import com.example.practica1_sanmateo.DAO.PacienteDAO;
import com.example.practica1_sanmateo.domain.Paciente;
import com.example.practica1_sanmateo.util.AlertUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AppController implements Initializable {
    private int idPacienteLogueado;
    private CitaDAO citasDAO;
    private PacienteDAO pacienteDAO;

    @FXML
    private TextField tfDNI;

    @FXML
    private TextField tfNumeroCita;

    @FXML
    private TextField tfNombrePaciente;

    @FXML
    private TextField tfDireccionPaciente;

    @FXML
    private TextField tfTelefono;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pacienteDAO = new PacienteDAO();
        citasDAO = new CitaDAO();
        try {
            citasDAO.conectar();
            pacienteDAO.conectar();
        } catch (SQLException sqle) {
            AlertUtils.mostrarError("Error al conectar con la base de datos");
        } catch (ClassNotFoundException cnfe) {
            AlertUtils.mostrarError("Error al iniciar la aplicación");
        } catch (IOException ioe) {
            AlertUtils.mostrarError("Error al cargar la configuración");
        }
    }

    public void setDatosPaciente(Paciente paciente) {
        this.idPacienteLogueado = paciente.getIdPaciente();

        tfDNI.setText(paciente.getDni());
        tfNombrePaciente.setText(paciente.getNombre());
        tfDireccionPaciente.setText(paciente.getDireccion());
        tfTelefono.setText(paciente.getTelefono());

        tfDNI.setEditable(false);
        tfNombrePaciente.setEditable(false);
        tfDireccionPaciente.setEditable(false);
        tfTelefono.setEditable(false);

    }
}
