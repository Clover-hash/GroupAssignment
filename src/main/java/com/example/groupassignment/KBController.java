package com.example.groupassignment;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.Objects;

import static com.example.groupassignment.ArticleController.*;

public class KBController {
    @FXML private TextField searchField;
    @FXML private ListView<String> resultsList;

    private void ReadArticleTitle(ArrayList<String> allItems) throws IOException {
        //go through all files in the folder
        Path folder = Paths.get("C:\\Users\\Hokianto\\Desktop\\Projects\\GroupAssignment\\Knowledgebase");
        try (Stream<Path> stream = Files.list(folder)){
            stream.filter(Files::isRegularFile).forEach(path -> {
                //read the first line
                try(BufferedReader reader = Files.newBufferedReader(path)){
                    String Line=reader.readLine();
                    allItems.add(Line);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        //go next file

        //go till the end
    }
    private ArrayList<String> allItems = new ArrayList<String>();

    @FXML
    private void initialize() throws IOException {

        resultsList.setVisible(false);
        resultsList.setManaged(false);

        ReadArticleTitle(allItems);

        searchField.textProperty().addListener((obs, old, val) -> applyFilter(val));
        resultsList.setOnMouseClicked(event -> {
            if (event.getClickCount()>=2){
                String item = resultsList.getSelectionModel().getSelectedItem();
                try {
                    openArticle(item,event);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    private void openArticle(String item, MouseEvent event) throws IOException {
        System.out.println(item + " Worked");
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/groupassignment/KB/ArticleReader.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        //take item as parameter to read the file and pass it to article controller
        ArticleController.initialize(item);
        stage.show();

    }


    private void applyFilter(String query) {
        if (query.isEmpty()) {
            resultsList.setVisible(false);
            resultsList.setManaged(false);
        }else {
            String q = query.toLowerCase();
            resultsList.getItems().setAll(allItems.stream().filter(Objects::nonNull).filter(s -> s.toLowerCase().contains(q)).toList());
            resultsList.setVisible(true);
            resultsList.setManaged(true);
        }
    }

    @FXML
    private void handleClear() {
        searchField.clear();
        resultsList.setVisible(false);
        resultsList.setManaged(false);
    }
}
