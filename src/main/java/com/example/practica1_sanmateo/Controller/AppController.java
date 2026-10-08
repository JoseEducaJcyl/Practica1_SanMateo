package com.example.practica1_sanmateo.Controller;

import com.example.practica1_sanmateo.DAO.CitaDAO;
import com.example.practica1_sanmateo.DAO.PacienteDAO;
import com.example.practica1_sanmateo.domain.Cita;
import com.example.practica1_sanmateo.domain.Paciente;
import com.example.practica1_sanmateo.util.AlertUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;
import java.util.Locale;
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

        setDatosCita();
    }

    public void setDatosCita() {
        try {
            List<Cita> citas = citasDAO.obtenerCitas(idPacienteLogueado);
            tvCitasPaciente.setItems(FXCollections.observableArrayList(citas));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
