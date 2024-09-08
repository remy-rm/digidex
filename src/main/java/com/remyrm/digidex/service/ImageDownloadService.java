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
import java.util.UUID;

@Service
public class ImageDownloadService {

    @Value("${image.storage.path}")
    private String imageStoragePath;

    public String downloadImage(String imageUrl) throws IOException {
        URL url = new URL(imageUrl);
        String fileExtension = imageUrl.substring(imageUrl.lastIndexOf('.'));
        String fileName = UUID.randomUUID().toString() + fileExtension; // Génère un nom unique pour chaque image

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        // Vérifiez que l'URL pointe vers une image
        String contentType = connection.getContentType();
        if (contentType.startsWith("image/")) {
            // Créer le répertoire si nécessaire
            Path imagePath = Paths.get(imageStoragePath + fileName);
            Files.createDirectories(imagePath.getParent());

            // Télécharger et sauvegarder l'image
            try (InputStream in = connection.getInputStream()) {
                Files.copy(in, imagePath, StandardCopyOption.REPLACE_EXISTING);
            }

            return imagePath.toString(); // Retourne le chemin local de l'image
        } else {
            throw new IOException("L'URL ne pointe pas vers une image valide");
        }
    }
}
