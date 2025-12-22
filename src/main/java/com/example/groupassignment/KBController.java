package com.example.groupassignment;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.Arrays;
import java.util.Objects;

public class KBController {
    @FXML private TextField searchField;
    @FXML private ListView<String> resultsList;

    // Sample data
    private final String[] allItems = {
            "Application for Visa",
            "How to apply for International Studies",
            "Scholarship Application",
            "Job Application Tips",
            ""
    };

    @FXML
    private void initialize() {
        resultsList.setVisible(false);
        resultsList.setManaged(false);

        searchField.textProperty().addListener((obs, old, val) -> applyFilter(val));
        resultsList.setOnMouseClicked(event -> {
            if (event.getClickCount()>=2){
                String item = resultsList.getSelectionModel().getSelectedItem();
                openArticle(item);
            }
        });
    }
    private void openArticle(String item){
        System.out.println(item+" Worked");
    }

    private void applyFilter(String query) {
        if (query.isEmpty()) {
            resultsList.setVisible(false);
            resultsList.setManaged(false);
        }else {
            String q = query.toLowerCase();
            resultsList.getItems().setAll(Arrays.stream(allItems).filter(Objects::nonNull).filter(s -> s.toLowerCase().contains(q)).toList());
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
