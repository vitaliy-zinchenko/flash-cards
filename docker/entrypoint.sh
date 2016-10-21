#!/bin/bash

echo "FC_PROFILE=$FC_PROFILE"

java -jar -Dspring.profiles.active=$FC_PROFILE flashcards.jar