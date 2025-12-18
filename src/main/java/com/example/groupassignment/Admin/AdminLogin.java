package com.example.groupassignment.Admin;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminLogin extends Application {

    @Override
    public void start(Stage stage) {

        // Title
        Label title = new Label("ADMIN LOGIN");
        title.setStyle(
                "-fx-font-size: 40px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #5A6CFF;"
        );

        // Username field
        TextField username = new TextField();
        username.setPromptText("ENTER YOUR USERNAME HERE");
        username.setPrefWidth(380);
        username.setPrefHeight(45);
        username.setStyle(
                "-fx-background-radius: 30;" +
                        "-fx-border-radius: 30;" +
                        "-fx-font-size: 14px;"
        );

        // Password field
        PasswordField password = new PasswordField();
        password.setPromptText("ENTER YOUR PASSWORD HERE");
        password.setPrefWidth(380);
        password.setPrefHeight(45);
        password.setStyle(
                "-fx-background-radius: 30;" +
                        "-fx-border-radius: 30;" +
                        "-fx-font-size: 14px;"
        );

        // Login button
        Button loginBtn = new Button("LOGIN");
        loginBtn.setPrefWidth(220);
        loginBtn.setPrefHeight(45);
        loginBtn.setStyle(
                "-fx-background-radius: 30;" +
                        "-fx-font-size: 16px;" +
                        "-fx-font-weight: bold;"
        );

        // Back button
        Button backBtn = new Button("BACK");
        backBtn.setPrefWidth(220);
        backBtn.setPrefHeight(45);
        backBtn.setStyle(
                "-fx-background-radius: 30;" +
                        "-fx-font-size: 16px;" +
                        "-fx-font-weight: bold;"
        );

        // Layout
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(40));
        layout.setStyle("-fx-background-color: #E6E9FF;");

        layout.getChildren().addAll(
                title,
                username,
                password,
                loginBtn,
                backBtn
        );

        Scene scene = new Scene(layout, 800, 500);
        stage.setScene(scene);
        stage.setTitle("Admin Login");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
