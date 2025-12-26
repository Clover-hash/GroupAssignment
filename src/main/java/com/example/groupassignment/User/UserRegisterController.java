package com.example.groupassignment.User;

import com.sun.tools.javac.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class UserRegisterController {
    @FXML
    public Label MainLabel;
    public TextField username;
    public PasswordField password;
    public PasswordField password2;
    public TextField email;

    @FXML
    protected void handleRegister(ActionEvent event) {
        String usern = username.getText().trim();
        String pass = password.getText();
        String pass2 = password2.getText();
        String emailID = email.getText().trim();

        if (usern.isEmpty() || pass.isEmpty() || emailID.isEmpty() || !pass.equals(pass2)) {
            MainLabel.setText("Username and password required / Password doesn't match");
            return;
        } else {

            User user = new User();
            user.username = usern;
            user.password = pass;
            user.email = emailID;

            try {
                UserStore.SaveUser(user);
                //go back to login
                Parent root = FXMLLoader.load(getClass().getResource("/com/example/groupassignment/UserStuff/UserLogin.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                //next thing
            } catch (IOException e) {
                MainLabel.setText("Error saving user");
                e.printStackTrace();
            }
        }
    }

    @FXML
    protected void Back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/groupassignment/UserStuff/UserLogin.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
