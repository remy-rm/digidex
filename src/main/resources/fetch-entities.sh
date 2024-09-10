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

#field 10
#attribute 7
#type 154
#level 9
#digimon 1460