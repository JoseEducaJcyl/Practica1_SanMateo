package com.example.practica1_sanmateo.Controller;

import com.example.practica1_sanmateo.App;
import com.example.practica1_sanmateo.DAO.CitaDAO;
import com.example.practica1_sanmateo.DAO.PacienteDAO;
import com.example.practica1_sanmateo.domain.Paciente;
import com.example.practica1_sanmateo.util.AlertUtils;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    private PacienteDAO pacienteDAO;

    @FXML
    private TextField tfEmail;

    @FXML
    private PasswordField pfPassword;

    @FXML
    private Button btLogin;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pacienteDAO = new PacienteDAO();
        try {
            pacienteDAO.conectar();
        }catch (SQLException sqle) {
            AlertUtils.mostrarError("Error cargando los datos de la aplicación");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void manejarLogin(ActionEvent evento) {
        String email = tfEmail.getText().trim();
        String password = pfPassword.getText().trim();

        if (email.isEmpty() || password.isEmpty()) {
            AlertUtils.mostrarError("Rellena todos los campos");
            return;
        }

        try {
            Paciente paciente = pacienteDAO.validarLogin(email, password);

            if (paciente == null) {
                AlertUtils.mostrarError("Email o contraseña incorrectos");
                return;
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/App.fxml"));
            Parent root = loader.load();

            AppController controller = loader.getController();
            controller.setDatosPaciente(paciente);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            ((Stage) btLogin.getScene().getWindow()).close();

        } catch (SQLException e) {
            AlertUtils.mostrarError("Error al validar el login");
        } catch (IOException e) {
            AlertUtils.mostrarError("Error al abrir la ventana");
        }
    }
}
