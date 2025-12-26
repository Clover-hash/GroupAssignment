package com.example.groupassignment.Admin;

import com.example.groupassignment.User.User;
import com.example.groupassignment.Admin.AdminStore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class AdminController {
    public static String user;
    public TextField usernameField;
    public PasswordField passwordField;


    @FXML
    private void handleLogin(ActionEvent event) throws IOException {
        user = usernameField.getText().trim();
        String pass = passwordField.getText();

        if (user.isEmpty() || pass.isEmpty()) {
            //MainLabel.setText("Enter username and password");
            //go into the Knowledgebase Search Thingy
        }else{
            try {
                //load users
                List<Admin> admins = AdminStore.LoadAdmin();
                boolean matchedUser = admins.stream().anyMatch(u -> u.username.equalsIgnoreCase(user) && u.password.equals(pass));

                if (matchedUser) {
                    Parent root = FXMLLoader.load(getClass().getResource("/com/example/groupassignment/Admin/AdminDashboard.fxml"));
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.show();
                } else {
                    System.out.println("Error");
                    //MainLabel.setText("Invalid username or password");
                }
            } catch (IOException e) {
                //MainLabel.setText("Error loading user data");
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void TestFunction(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/groupassignment/UserStuff/UserLogin.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
