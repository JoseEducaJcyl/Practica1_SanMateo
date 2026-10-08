package com.example.practica1_sanmateo.Controller;

import com.example.practica1_sanmateo.DAO.CitaDAO;
import com.example.practica1_sanmateo.DAO.PacienteDAO;
import com.example.practica1_sanmateo.domain.Cita;
import com.example.practica1_sanmateo.domain.Paciente;
import com.example.practica1_sanmateo.util.AlertUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Date;
import java.util.*;

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

    @FXML
    private TableView<Cita> tvCitasPaciente;

    @FXML
    private TableColumn<Cita, Integer> numCita;

    @FXML
    private TableColumn<Cita, Date> fecha;

    @FXML
    private TableColumn<Cita, String> especialidad;

    @FXML
    private ComboBox<String> cbEspecialidad;

    @FXML
    private Button btVerCitas, btNuevaCita, btBorrarCita, btModificarCita;

    @FXML
    private DatePicker dtFechaCita;

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

        numCita.setCellValueFactory(new PropertyValueFactory<Cita, Integer>("idCita"));
        fecha.setCellValueFactory(new PropertyValueFactory<Cita, Date>("fecha"));
        especialidad.setCellValueFactory(new PropertyValueFactory<Cita, String>("nombreEspecialidad"));

        cbEspecialidad.getItems().addAll(
                "Medicina general",
                "Pediatría",
                "Traumatología",
                "Dermatología",
                "Cardiología",
                "Endocrinología",
                "Neurologia",
                "Oftalmología",
                "Urología",
                "Ginecologá"
        );
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


    @FXML
    public void verCitas(Event event) {
        try {
            List<Cita> citas = citasDAO.obtenerCitas(idPacienteLogueado);
            tvCitasPaciente.setItems(FXCollections.observableArrayList(citas));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void seleccionarCita(MouseEvent mouseEvent) {
        Cita cita = tvCitasPaciente.getSelectionModel().getSelectedItem();
        if (cita != null) {
            tfNumeroCita.setText(String.valueOf(cita.getIdCita()));

            dtFechaCita.setValue(cita.getFecha().toLocalDate());

            cbEspecialidad.setValue(cita.getNombreEspecialidad());
        }
    }

    @FXML
    public void modificarCita(Event event) throws SQLException {
        Cita cita = tvCitasPaciente.getSelectionModel().getSelectedItem();
        try {
            if (cita.getNombreEspecialidad()!= cbEspecialidad.getValue()
            || !Objects.equals(cita.getFecha(), Date.valueOf(dtFechaCita.getValue()))){
                cita.setNombreEspecialidad(cbEspecialidad.getValue());
                cita.setFecha(Date.valueOf(dtFechaCita.getValue()));
                citasDAO.modificarCita(cita);
                Alert confirmacion = new Alert(Alert.AlertType.INFORMATION);
                confirmacion.setTitle("Exito");
                confirmacion.setContentText("Cita modificada con exito");
                confirmacion.showAndWait();
            }
        } catch (SQLException e) {
            AlertUtils.mostrarError("Error al modificar la cita");
        }
    }

    @FXML
    public void nuevaCita(Event event) throws SQLException {
        Date fecha = Date.valueOf(dtFechaCita.getValue());
        String especialidad = cbEspecialidad.getValue();
        int idPaciente = this.idPacienteLogueado;
        Cita cita = new Cita(especialidad, fecha, idPaciente);

        if (dtFechaCita.getValue() != null || cbEspecialidad.getValue() != null){
            try {
                citasDAO.nuevaCita(cita);
                Alert confirmacion = new Alert(Alert.AlertType.INFORMATION);
                confirmacion.setTitle("Exito");
                confirmacion.setContentText("Nueva cita hecha");
                confirmacion.showAndWait();
            } catch (SQLException e) {
                AlertUtils.mostrarError("Error al crear la cita");
            }
        }
    }

    @FXML
    public void eliminarCita(Event event) throws SQLException {
        Cita cita = tvCitasPaciente.getSelectionModel().getSelectedItem();
        try {
            if (cita!=null){
                Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
                confirmacion.setTitle("Eliminar cita");
                confirmacion.setContentText("¿Estás seguro?");
                Optional<ButtonType> respuesta = confirmacion.showAndWait();
                if (respuesta.get().getButtonData() == ButtonBar.ButtonData.CANCEL_CLOSE)
                    return;
                citasDAO.eliminarCita(cita.getIdCita());
            }
        } catch (SQLException e) {
            AlertUtils.mostrarError("Error al borrar la cita");
        }
    }
}
