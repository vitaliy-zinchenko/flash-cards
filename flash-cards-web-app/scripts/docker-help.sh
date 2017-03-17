#!/usr/bin/env bash

docker run -d --name fc_postgres -e DB_NAME=flash_cards -e DB_USER=fcuser -e DB_PASS=password -e DEBUG=false sameersbn/postgresql:9.5-2
docker run -d --link fc_postgres -e ENV_NAME=dev-env -p 80:9000 flash-cards-web-app:1.0-SNAPSHOT
