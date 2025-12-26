package com.example.groupassignment.Admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Stream;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class KBUploadController {

    public TextArea contentArea;
    public ListView<String> TitleView;
    public TextField searchField;
    public Button EditSaveBtn;
    ArrayList<String> allTitles = new ArrayList<String>();


    public void init() throws IOException {
        LoadFileTitles(allTitles);
        TitleView.getItems().setAll(allTitles);
        searchField.textProperty().addListener((obs, old, val) -> applyFilter(val));
        TitleView.setOnMouseClicked(event -> {
            if (event.getClickCount() >= 2) {
                String item = TitleView.getSelectionModel().getSelectedItem();
                try {
                    GetFileContent(item);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }



    protected void LoadFileTitles(ArrayList<String> allTitles) throws IOException {
        //load files into the system

        //read the files
        Path folder = Paths.get("Knowledgebase");
        try (Stream<Path> stream = Files.list(folder)) {
            //go in file
            stream.filter(Files::isRegularFile).forEach(path -> {
                //read the first line and get the title
                try (BufferedReader reader = Files.newBufferedReader(path)) {
                    //check if the first line already exists
                    String Line = reader.readLine();
                    if (!allTitles.contains(Line)) {
                        allTitles.add(Line);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }


    protected void GetFileContent(String fileName) throws IOException {
        //get file content by its title
        ArrayList<String> Lines;

        //go through all files in the folder
        Path folder = Paths.get("Knowledgebase");
        Path filefound;
        try (Stream<Path> stream = Files.list(folder)) {
            //go in file
            filefound = stream.filter(Files::isRegularFile).filter(path -> {
                //read the first line
                try (BufferedReader reader = Files.newBufferedReader(path)) {
                    String Line = reader.readLine();
                    return Line != null && Line.equalsIgnoreCase(fileName);
                } catch (IOException e) {
                    return false;
                }
            }).findFirst().orElse(null);
            //if not found go next file
        }
        //go till the end

        //read all lines
        assert filefound != null;
        Lines = (ArrayList<String>) Files.readAllLines(filefound);
        String content = String.join("\n", Lines);

        contentArea.setText(content);
    }

    private void applyFilter(String query) {
        if (query.isEmpty()) {
            TitleView.getItems().setAll(allTitles);
        } else {
            String q = query.toLowerCase();
            TitleView.getItems().setAll(allTitles.stream().filter(Objects::nonNull).filter(s -> s.toLowerCase().contains(q)).toList());
            TitleView.setVisible(true);
            TitleView.setManaged(true);
        }
    }

    @FXML
    private void UploadFile(ActionEvent event) throws IOException {
        //upload file to knowledge base
        ChangesController changesController = new ChangesController();
        changesController.SaveChange(java.time.LocalDate.now().toString(),"Knowledge Base","Uploaded a file into the Knowledge Base",AdminController.user);

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select knowledge base files");

        // optional: restrict to text/pdf/etc.
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt", "*.md"));

        // get current window
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            try {
                Path source = file.toPath();

                // your KB directory (relative to project or absolute)
                Path kbDir = Paths.get("Knowledgebase");
                if (!Files.exists(kbDir)) {
                    Files.createDirectories(kbDir);
                }

                // same filename in KB folder
                Path target = kbDir.resolve(source.getFileName());

                Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);

                System.out.println("Copied to: " + target.toAbsolutePath());
                System.out.println("Selected file: " + file.getAbsolutePath());
                init();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    protected void DeleteFile() throws IOException {
        //delete file by its title
        ChangesController changesController = new ChangesController();
        changesController.SaveChange(java.time.LocalDate.now().toString(),"Knowledge Base","Deleted a file from the Knowledge Base",AdminController.user);

        String fileName = TitleView.getSelectionModel().getSelectedItem();
        //go through all files in the folder
        Path folder = Paths.get("Knowledgebase");
        Path filefound;
        try (Stream<Path> stream = Files.list(folder)) {
            //go in file
            filefound = stream.filter(Files::isRegularFile).filter(path -> {
                //read the first line
                try (BufferedReader reader = Files.newBufferedReader(path)) {
                    String Line = reader.readLine();
                    return Line != null && Line.equalsIgnoreCase(fileName);
                } catch (IOException e) {
                    return false;
                }
            }).findFirst().orElse(null);
            //if not found go next file
        }
        //go till the end

        //delete file
        assert filefound != null;
        Files.delete(filefound);

        //refresh everything
        allTitles.clear();
        contentArea.clear();
        LoadFileTitles(allTitles);
        TitleView.getItems().setAll(allTitles);
    }

    protected boolean editMode = false;

    @FXML
    protected void ToggleEditSave() throws IOException {
        if (editMode) {
            SaveFileContent();
            EditSaveBtn.setText("EDIT");
        } else {
            EditFileContent();
            EditSaveBtn.setText("SAVE");
        }
    }


    protected void EditFileContent() throws IOException {
        //make text area editable
        contentArea.setEditable(true);
        editMode=true;
        //change button to save

    }

    protected void SaveFileContent() throws IOException {
        //save text area content to file
        ChangesController changesController = new ChangesController();
        changesController.SaveChange(java.time.LocalDate.now().toString(),"Knowledge Base","Edited a file into the Knowledge Base",AdminController.user);

        contentArea.setEditable(false);
        editMode=false;
        String fileName = TitleView.getSelectionModel().getSelectedItem();
        //go through all files in the folder
        Path folder = Paths.get("Knowledgebase");
        Path filefound;
        try (Stream<Path> stream = Files.list(folder)) {
            //go in file
            filefound = stream.filter(Files::isRegularFile).filter(path -> {
                //read the first line
                try (BufferedReader reader = Files.newBufferedReader(path)) {
                    String Line = reader.readLine();
                    return Line != null && Line.equalsIgnoreCase(fileName);
                } catch (IOException e) {
                    return false;
                }
            }).findFirst().orElse(null);
            //if not found go next file
        }
        //go till the end

        //write all lines
        assert filefound != null;
        Files.writeString(filefound, contentArea.getText());
    }

    @FXML
    protected void BackToDashboard(ActionEvent event) throws IOException {
        //go back to admin dashboard
        javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("/com/example/groupassignment/Admin/AdminDashboard.fxml"));
        javafx.scene.Parent root = loader.load();
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new javafx.scene.Scene(root));
        stage.show();
    }
}
