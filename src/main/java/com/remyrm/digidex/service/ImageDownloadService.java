package com.remyrm.digidex.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class ImageDownloadService {

    @Value("${image.storage.path}")
    private String imageStoragePath;

    private static final String imagesPathBdd = "images";

    public String downloadImage(String imageUrl) throws IOException {
        URL url = new URL(imageUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        String contentType = connection.getContentType();
        String fileName = extractFileNameFromUrl(imageUrl);
        Path imagePath = Paths.get(imageStoragePath, fileName);

        if (contentType != null && contentType.startsWith("image/")) {

            Files.createDirectories(imagePath.getParent());

            if (Files.exists(imagePath)) {
                System.out.println("Image already exists at " + imagesPathBdd + "/" + fileName);
                return imagesPathBdd + "/" + fileName;
            }
            try (InputStream in = connection.getInputStream()) {
                Files.copy(in, imagePath, StandardCopyOption.REPLACE_EXISTING);
            }

            return imagesPathBdd + "/" + fileName; // Return path relative to imagesPathBdd
        } else {
            throw new IOException("L'URL ne pointe pas vers une image valide");
        }
    }

    public boolean imageExists(String imageUrl) {
        String fileName = extractFileNameFromUrl(imageUrl);
        Path imagePath = Paths.get(imageStoragePath, fileName);

        return Files.exists(imagePath);
    }

    private String extractFileNameFromUrl(String imageUrl) {
        return imageUrl.substring(imageUrl.lastIndexOf('/') + 1);
    }
}
