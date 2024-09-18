# Digidex Project

## Description

Le projet **Digidex** est une application Spring qui permet de récupérer des données depuis
l'API [Digi-API](https://digi-api.com) et de les redistribuer sous forme d'API REST, avec une documentation interactive
via Swagger. L'application permet aussi de télécharger et de stocker des images associées aux données.

## Prérequis

Avant de démarrer le projet, assurez-vous d'avoir les éléments suivants installés :

- Java 21 ou supérieur
- Maven
- Une base de données MySQL

## Configuration

1. Créez un schéma nommé **digidex** dans votre base de données MySQL :
   ```sql
   CREATE DATABASE digidex;

2. Dans le fichier src/main/resources/secret.properties, spécifiez les informations suivantes :

- spring.datasource.username=VOTRE_NOM_UTILISATEUR
- spring.datasource.password=VOTRE_MOT_DE_PASSE
- spring.datasource.url=jdbc:mysql://localhost:3306/digidex

## Initialisation

### Option 1 :

- démarrez l'application en exécutant la commande suivante :
  ```shell
  mvn spring-boot:run
  ```
- utilliser les scripts pour recuperer les données depuis l'API Digi-API :
  ```shell
  #Recuperer les champs(field)
  src/main/resources/fetch-entities.sh field 1 10
  #Recuperer les attributs(attribute)
  src/main/resources/fetch-entities.sh attribute 1 7
  #Recuperer les niveaux(level)
  src/main/resources/fetch-entities.sh level 1 9
  #Recuperer les types(type)
  src/main/resources/fetch-entities.sh type 1 154
  #Recuperer les digimons
  src/main/resources/fetch-entities.sh digimon 1 1460
  ```

### Option 2 :

- Téléchargez les images à partir du lien suivant :
  [Images](https://onedrive.live.com/?redeem=aHR0cHM6Ly8xZHJ2Lm1zL3UvYy8xMTZiMzljNjkxNTczOGI5L0VhY2FTRE1yMnJkT2hEZFJjSVdXengwQkR3UXd6R0NkenNVZ3FxR1NYZXRseEE%5FZT1IQnFxME4&cid=116B39C6915738B9&id=116B39C6915738B9%21s33481aa7da2b4eb7843751708596cf1d&parId=root&o=OneUp)
- Décompressez le fichier et placez le dossier **images** dans le répertoire **src/main/resources/static**.

## Utilisation

- si l'application n'est pas demarré en exécutant la commande suivante :
  ```shell
  mvn spring-boot:run
  ```
- Accédez à la documentation Swagger via le lien suivant :
  [Swagger](http://localhost:8080/swagger-ui.html)

## Fonctionnalités principales

- Récupération des données depuis l'API [Digi-API](https://digi-api.com)
- Redistribution des données sous forme d'API REST
- Documentation interactive via Swagger

## Auteurs

- Marquis Rémy 