package com.example.practica1_sanmateo;

import com.example.practica1_sanmateo.util.R;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        AppController controller = new AppController();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(R.getUI("hello-view.fxml"));
        Scene scene = new Scene(loader.load(), 600, 400);
        stage.setTitle("Gestión Hospitalaria");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}