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

    public String downloadImage(String imageUrl) throws IOException {
        URL url = new URL(imageUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        String contentType = connection.getContentType();
        String fileName = extractFileNameFromUrl(imageUrl);
        Path imagePath = Paths.get(imageStoragePath, fileName);
        if (contentType.startsWith("image/")) {

            Files.createDirectories(imagePath.getParent());

            if (Files.exists(imagePath)) {
                return imagePath.toString();
            }
            try (InputStream in = connection.getInputStream()) {
                Files.copy(in, imagePath, StandardCopyOption.REPLACE_EXISTING);
            }

            return imagePath.toString();
        } else {
            throw new IOException("L'URL ne pointe pas vers une image valide");
        }
    }

    public boolean imageExists(String imageUrl) {
        System.out.println(imageUrl);
        String fileName = extractFileNameFromUrl(imageUrl);
        Path imagePath = Paths.get(imageStoragePath, fileName);
        System.out.println(imagePath);

        return Files.exists(imagePath);
    }

    private String extractFileNameFromUrl(String imageUrl) {
        System.out.println(imageUrl.substring(imageUrl.lastIndexOf('/') + 1));
        return imageUrl.substring(imageUrl.lastIndexOf('/') + 1);
    }
}
