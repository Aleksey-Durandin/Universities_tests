package utils;

import api.University;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;

public class FileHelper {
    private static final String resourcesFolder = "src/test/resources/";

    public static List<University> getUniversitiesListFromFile(String fileName) {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = Paths.get(resourcesFolder + fileName).toFile();
        try {
            return objectMapper.readValue(file, new TypeReference<>(){});
        } catch (IOException exception){
            throw new RuntimeException("File read ERROR!");
        }


    }
}
