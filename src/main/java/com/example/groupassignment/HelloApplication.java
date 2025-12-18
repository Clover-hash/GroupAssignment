package com.example.groupassignment;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        URL fxml = getClass().getResource("AdminLogin.fxml"); // or "/admin/AdminLogin.fxml"
        System.out.println(fxml); // should not print null
        FXMLLoader loader = new FXMLLoader(fxml);
        Scene scene = new Scene(loader.load(), 320, 420);
        stage.setScene(scene);
        stage.show();
    }
}
