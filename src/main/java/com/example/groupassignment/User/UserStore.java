package com.example.groupassignment.User;

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

public class UserStore {

    private static final Path UserFile=Paths.get("C:\\Users\\Hokianto\\Desktop\\Projects\\GroupAssignment\\src\\main\\resources\\com\\example\\groupassignment\\UserData.json");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Type JsonToList= new TypeToken<List<User>>() {}.getType();

    //load the user json data first
    public static List<User> LoadUser() throws IOException {
        if (!Files.exists(UserFile)){
            return new ArrayList<>();
        }
        try(Reader reader = Files.newBufferedReader(UserFile)){
            return GSON.fromJson(reader,JsonToList);
        }
    }

    //save user json data
    public static void SaveUser(User newUser) throws IOException {
        Files.createDirectories(UserFile.getParent());
        List<User> users = LoadUser();

        boolean exists = users.stream().anyMatch(u -> u.username.equalsIgnoreCase(newUser.username));
        if(!exists) {
            users.add(newUser);
            try (Writer writer = Files.newBufferedWriter(
                    UserFile,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING
            )) {
                GSON.toJson(users, JsonToList, writer);
            }
        }
    }
}
