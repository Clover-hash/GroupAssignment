package com.example.groupassignment.User;

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
import java.util.List;

public class UserLoginController {

    @FXML
    public Label MainLabel;
    public TextField username;
    public PasswordField password;

    @FXML
    protected void Login(ActionEvent event) {
        //check if valid credentials
        String user = username.getText().trim();
        String pass = password.getText();

        if (user.isEmpty() || pass.isEmpty()) {
            MainLabel.setText("Enter username and password");
            //go into the Knowledgebase Search Thingy
        }else{
            try {
                //load users
                List<User> users = UserStore.LoadUser();
                boolean matchedUser = users.stream().anyMatch(u -> u.username.equalsIgnoreCase(user) && u.password.equals(pass));

                if (matchedUser) {
                    Parent root = FXMLLoader.load(getClass().getResource("/com/example/groupassignment/KB/KnowledgeBaseSearch.fxml"));
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.show();
                } else {
                    MainLabel.setText("Invalid username or password");
                }
            } catch (IOException e) {
                MainLabel.setText("Error loading user data");
                e.printStackTrace();
            }
        }
    }

    @FXML
    protected void Register(ActionEvent event) throws IOException {
        System.out.println("Coming soon");
        //pass to the UserRegister
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/groupassignment/UserStuff/UserRegister.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void AdminLogin(){
        //pass to Admin Login
    }
}
