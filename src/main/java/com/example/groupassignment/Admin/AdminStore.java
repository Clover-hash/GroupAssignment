package com.example.groupassignment.Admin;

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

public class AdminStore {

    private static final Path AdminFile=Paths.get("src/main/resources/com/example/groupassignment/AdminData.json");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Type JsonToList= new TypeToken<List<Admin>>() {}.getType();

    //load the admin json data first
    public static List<Admin> LoadAdmin() throws IOException {
        if (!Files.exists(AdminFile)){
            System.out.println("No Admin Data File Found, Creating New One.");
            return new ArrayList<>();
        }
        try(Reader reader = Files.newBufferedReader(AdminFile)){
            List<Admin> users = GSON.fromJson(reader, JsonToList);
            return users == null ? new ArrayList<>() : users;
        }
    }
}
