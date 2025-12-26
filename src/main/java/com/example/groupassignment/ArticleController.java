package com.example.groupassignment;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class ArticleController {

    @FXML
    public TextArea Article;

    @FXML
    public  void initialize(String ArticleName) throws IOException {
        // change label to read all the data in the article
        ArrayList<String> Lines;

        //go through all files in the folder
        Path folder = Paths.get("Knowledgebase");
        Path filefound;
        try (Stream<Path> stream = Files.list(folder)){
            //go in file
            filefound=stream.filter(Files::isRegularFile).filter(path -> {
                //read the first line
                try(BufferedReader reader = Files.newBufferedReader(path)){
                    String Line=reader.readLine();
                    return Line != null && Line.equalsIgnoreCase(ArticleName);
                } catch (IOException e) {
                    return false;
                }
            }).findFirst().orElse(null);
            //if not found go next file
        }
        //go till the end

        //read all lines
        assert filefound != null;
        Lines= (ArrayList<String>) Files.readAllLines(filefound);
        String content=String.join("\n", Lines);

        Article.setText(content);
    }

    @FXML
    protected void Back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/groupassignment/KB/KnowledgeBaseSearch.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
