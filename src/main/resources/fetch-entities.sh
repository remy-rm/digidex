#!/bin/bash

# Variables
ENTITY=$1        # Le nom de l'entité, par exemple "field"
START_ID=$2      # L'ID de départ
END_ID=$3        # L'ID de fin
PAUSE=$4         # Temps de pause en secondes

# Boucle sur les ID
for (( id=$START_ID; id<=$END_ID; id++ ))
do
  # Effectue la requête HTTP POST
  url="http://localhost:8080/$ENTITY/fetch"
  echo "Fetching: $url with ID $id"
  response=$(curl -s -X POST "$url" -H "accept: */*" -d "id=$id")

  # Affiche la réponse
  echo "Response for $ENTITY with ID $id: $response"

  # Pause
  if [ $PAUSE -gt 0 ]; then
    echo "Waiting for $PAUSE seconds..."
    sleep $PAUSE
  fi
done

# src/main/resources/fetch-entities.sh field 1 10 1
# src/main/resources/fetch-entities.sh attribute 1 7 1
# src/main/resources/fetch-entities.sh type 1 154 1
# src/main/resources/fetch-entities.sh level 1 9 1
# src/main/resources/fetch-entities.sh digimon 1 1460 1