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
            // Utilisez le nom de fichier de l'URL pour éviter les doublons
            String fileName = extractFileNameFromUrl(imageUrl);

            // Créer le répertoire si nécessaire
            Path imagePath = Paths.get(imageStoragePath, fileName);
            Files.createDirectories(imagePath.getParent());

            // Si l'image existe déjà, ne pas la télécharger à nouveau
            if (Files.exists(imagePath)) {
                System.out.println("Image déjà existante : " + imagePath);
                return imagePath.toString(); // Retourne le chemin local de l'image existante
            }

            // Télécharger et sauvegarder l'image si elle n'existe pas
            try (InputStream in = connection.getInputStream()) {
                Files.copy(in, imagePath, StandardCopyOption.REPLACE_EXISTING);
            }

            return imagePath.toString(); // Retourne le chemin local de l'image téléchargée
        } else {
            throw new IOException("L'URL ne pointe pas vers une image valide");
        }
    }

    public boolean imageExists(String imageUrl) {
        // Utiliser le même nom de fichier généré à partir de l'URL
        String fileName = extractFileNameFromUrl(imageUrl);
        Path imagePath = Paths.get(imageStoragePath, fileName);

        // Vérifier si le fichier existe
        return Files.exists(imagePath);
    }

    // Méthode utilitaire pour extraire le nom de fichier à partir de l'URL
    private String extractFileNameFromUrl(String imageUrl) {
        return imageUrl.substring(imageUrl.lastIndexOf('/') + 1);
    }
}
