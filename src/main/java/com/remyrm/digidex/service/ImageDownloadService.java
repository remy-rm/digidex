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
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        // Vérifiez que l'URL pointe vers une image
        String contentType = connection.getContentType();
        if (contentType.startsWith("image/")) {
            // Obtenez l'extension de fichier à partir du type MIME
            String fileExtension = "." + contentType.substring(contentType.lastIndexOf('/') + 1);
            String fileName = UUID.randomUUID().toString() + fileExtension;

            // Créer le répertoire si nécessaire
            Path imagePath = Paths.get(imageStoragePath, fileName);
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
