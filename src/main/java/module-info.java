module com.example.groupassignment {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires jdk.compiler;
    requires com.google.gson;


    opens com.example.groupassignment to javafx.fxml;
    exports com.example.groupassignment;
    exports com.example.groupassignment.User;
    opens com.example.groupassignment.User to javafx.fxml;
}