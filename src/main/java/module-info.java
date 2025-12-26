module com.example.groupassignment {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires jdk.compiler;
    requires com.google.gson;
    requires langchain4j;
    requires langchain4j.core;
    requires langchain4j.open.ai;
    requires langchain4j.embeddings.all.minilm.l6.v2;
    requires javafx.base;


    opens com.example.groupassignment to javafx.fxml;
    exports com.example.groupassignment;
    exports com.example.groupassignment.User;
    opens com.example.groupassignment.User to javafx.fxml;
    exports com.example.groupassignment.SImpleRAG;
    opens com.example.groupassignment.SImpleRAG to javafx.fxml;
    exports com.example.groupassignment.Admin;
    opens com.example.groupassignment.Admin to javafx.fxml, com.google.gson;
    exports com.example.groupassignment.Query;
    opens com.example.groupassignment.Query to javafx.fxml;
}