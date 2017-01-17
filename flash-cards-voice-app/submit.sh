#!/bin/bash

echo "submitting..."

curl -vX POST http://127.0.0.1:9000/dictionary -d @dictionary.json --header "Content-Type: application/json"