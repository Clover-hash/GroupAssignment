package com.example.groupassignment.Query;

import com.example.groupassignment.User.User;
import com.example.groupassignment.User.UserLoginController;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class QueryController {
    private static final Path QueryFile= Paths.get("src/main/resources/com/example/groupassignment/QueryData.json");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Type JsonToList= new TypeToken<List<Query>>() {}.getType();

    public static void receiveQuery(String username, String reason) throws IOException {

        //generate QueryID
        int existingQueryCount = getQueryCount();
        String GeneratedID = String.valueOf(existingQueryCount + 1);

        //create Query object
        Query query = new Query();
        query.QueryID =GeneratedID ;
        query.Username = UserLoginController.user;
        query.Reason = reason;
        //store query
        System.out.println("Enter Receive Query");
        storeQuery(query);
    }

    private static int getQueryCount() throws IOException {
        try(Reader reader = Files.newBufferedReader(QueryFile)) {
            Type queryListType = new TypeToken<List<Query>>() {
            }.getType();
            List<Query> allQueries = GSON.fromJson(reader, queryListType);
            return allQueries.size();
        }
    }

    public static List<Query> LoadQuery() throws IOException {
        if (!Files.exists(QueryFile)){
            System.out.println("Query file does not exist. Returning empty list.");
            return new ArrayList<Query>();
        }
        try(Reader reader = Files.newBufferedReader(QueryFile)){
            List<Query> queries = GSON.fromJson(reader, JsonToList);
            //System.out.println(queries.size());
            return new ArrayList<>(queries);
        }
    }

    private static void storeQuery(Query query) throws IOException {
        //open json file
        System.out.println("Enter Store Query");
        Files.createDirectories(QueryFile.getParent());
        List<Query> queryList = LoadQuery();

        boolean exists = queryList.stream().anyMatch(u -> u.QueryID.equalsIgnoreCase(query.QueryID));
        if(!exists) {
            queryList.add(query);
            try (Writer writer = Files.newBufferedWriter(
                    QueryFile,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING
            )) {
                GSON.toJson(queryList, JsonToList, writer);
            }
        }
        //store the query object into storage (e.g., JSON file, database)
    }
}
