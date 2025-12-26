package com.example.groupassignment.Admin;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class ChangesController {
    //log changes made by admin
    private static final Path ChangesFile = Paths.get("src/main/resources/com/example/groupassignment/ChangesData.json");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Type JsonToList = new TypeToken<List<Changes>>() {
    }.getType();

    public TableColumn<Changes, String> dateColumn;
    public TableColumn<Changes, String> categoryColumn;
    public TableColumn<Changes, String> descriptionColumn;
    public TableColumn<Changes, String> doneByColumn;
    public TableView<Changes> changesTable;

    public void SaveChange(String date, String category, String desc, String doneBy) {

        //put inside the json file

        //load the file first
        List<Changes> changesList;
        try {
            if (!Files.exists(ChangesFile)) {
                changesList = new java.util.ArrayList<>();
            } else {
                try (var reader = Files.newBufferedReader(ChangesFile)) {
                    changesList = GSON.fromJson(reader, JsonToList);
                    if (changesList == null) {
                        changesList = new java.util.ArrayList<>();
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Changes change = new Changes();

        //set values

        change.setDate(date);
        change.setCategory(category);
        change.setDoneBy(doneBy);
        change.setDescription(desc);

        //add to list
        changesList.add(change);

        //save back to file
        try (Writer writer = Files.newBufferedWriter(
                ChangesFile,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING
        )) {
            System.out.println("Saving changes to file.");
            GSON.toJson(changesList, JsonToList, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //show changes made by admin
    protected void showChanges() {
        //read from database or file

        //load the file first
        List<Changes> changesList;
        if (!Files.exists(ChangesFile)) {
            changesList = new java.util.ArrayList<>();
        } else {
            try (var reader = Files.newBufferedReader(ChangesFile)) {
                changesList = GSON.fromJson(reader, JsonToList);
                if (changesList == null) {
                    changesList = new java.util.ArrayList<>();
                    System.out.println("No changes found.");
                } else {
                    System.out.println("Changes loaded: " + changesList.size());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }
        //display changes
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        doneByColumn.setCellValueFactory(new PropertyValueFactory<>("doneBy"));

        ObservableList<Changes> observable = FXCollections.observableArrayList(changesList);
        changesTable.setItems(observable);
        //
    }

    @FXML
     protected void Back(ActionEvent event) throws IOException {
         //go back to admin dashboard
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/groupassignment/Admin/AdminDashBoard.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
     }
    }