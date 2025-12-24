package com.example.groupassignment;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class UserApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        URL fxml = getClass().getResource("UserStuff/UserLogin.fxml");
        FXMLLoader loader = new FXMLLoader(fxml);
        Scene scene = new Scene(loader.load(), 395, 297);
        stage.setScene(scene);
        stage.show();
    }
}
