package ru.itpank.travel.insurance;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Component
public class JsonFileReader {

    public String readJsonFromFile(String filePath) throws IOException {
        File file = new ClassPathResource(filePath).getFile();
        return Files.readString(file.toPath());
    }
}