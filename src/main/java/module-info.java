module com.example.groupassignment {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.groupassignment to javafx.fxml;
    exports com.example.groupassignment;
    exports com.example.groupassignment.Admin;
    opens com.example.groupassignment.Admin to javafx.fxml;
}