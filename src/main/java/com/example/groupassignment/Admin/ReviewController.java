package com.example.groupassignment.Admin;

import com.example.groupassignment.Query.Query;
import com.example.groupassignment.Query.QueryController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReviewController {
    public ListView<String> QueryList;
    public TextArea contentArea;

    public void initialize() throws IOException {
        //show the query list to admin
        List<Query> allQuery = LoadFileTitles();

        //switch to show only the query ID in the list view
        ArrayList<String> queryIDs = new ArrayList<>();
        for (Query q : allQuery) {
            queryIDs.add("Query Number " + q.QueryID );
        }
        QueryList.getItems().setAll(queryIDs);
        QueryList.setOnMouseClicked(event -> {
            if (event.getClickCount() >= 2) {
                String item = QueryList.getSelectionModel().getSelectedItem();
                try {
                    GetQueryContent(item);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    protected List<Query> LoadFileTitles() throws IOException {
        //load query titles from Query Controller
        return QueryController.LoadQuery();
    }

    protected void GetQueryContent(String item) throws IOException {
        //get the content of the selected query
        List<Query> allQuery = LoadFileTitles();
        String queryID = item.replace("Query Number ", "");
        String content = "";
        for (Query q : allQuery) {
            if (q.QueryID.equals(queryID)) {
                content = "Username: " + q.Username + "\n" +
                        "Reason For Forwarding " + "\n" +
                        "AI couldn't find '" + q.Reason + "' in the knowledge base." + "\n";
                break;
            }
        }
        contentArea.setText(content);

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
