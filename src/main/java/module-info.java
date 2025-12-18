module com.example.groupassignment {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.groupassignment to javafx.fxml;
    exports com.example.groupassignment;
}