package com.example.practica1_sanmateo.Controller;

import com.example.practica1_sanmateo.DAO.CitasDAO;
import com.example.practica1_sanmateo.util.AlertUtils;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AppController implements Initializable {
    private CitasDAO citasDAO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        citasDAO = new CitasDAO();
        try {
            citasDAO.conectar();
            cargarDatos();
        } catch (SQLException sqle) {
            AlertUtils.mostrarError("Error al conectar con la base de datos");
        } catch (ClassNotFoundException cnfe) {
            AlertUtils.mostrarError("Error al iniciar la aplicación");
        } catch (IOException ioe) {
            AlertUtils.mostrarError("Error al cargar la configuración");
        }
    }

    public void cargarDatos() {
        /*
        modoEdicion(false);

        lvCoches.getItems().clear();
        try {
            List<Coche> coches = cocheDAO.obtenerCoches();
            lvCoches.setItems(FXCollections.observableList(coches));

            String[] tipos = new String[]{"<Selecciona tipo>", "Familiar", "Monovolumen", "Deportivo", "SUV"};
            cbTipo.setItems(FXCollections.observableArrayList(tipos));
        } catch (SQLException sqle) {
            AlertUtils.mostrarError("Error cargando los datos de la aplicación");
        }

        */
    }
}
