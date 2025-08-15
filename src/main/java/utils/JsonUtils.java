package utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class JsonUtils {
    public static String readJsonAsString(String fileName) {
        String filePath = "src/test/resources/payloads/" + fileName;
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (NoSuchFileException e) {
            throw new RuntimeException("JSON file not found: " + filePath, e);
        } catch (IOException e) {
            throw new RuntimeException("Error reading JSON file: " + filePath, e);
        }
    }
    
    public static JsonObject readJsonAsObject(String fileName) {
        String filePath = "src/test/resources/payloads/" + fileName;
        try {
            return new Gson().fromJson(new FileReader(filePath), JsonObject.class);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("JSON file not found: " + filePath, e);
        }
    }
}
