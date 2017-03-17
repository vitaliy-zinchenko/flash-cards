#!/usr/bin/env bash

pushd ../app/ui

npm install
webpack --mode build --progress

popd
pushd ../


sbt clean

docker login -u zinjvi -p BIMANSjkdiTZiNav
sbt docker:publish

ssh -i "~/.ssh/zinjvi.pem" ubuntu@ec2-35-163-102-202.us-west-2.compute.amazonaws.com 'docker stop flash-cards-web-app && docker rm flash-cards-web-app && docker rmi zinjvi/flash-cards-web-app:1.0-SNAPSHOT'
ssh -i "~/.ssh/zinjvi.pem" ubuntu@ec2-35-163-102-202.us-west-2.compute.amazonaws.com 'docker login -u zinjvi -p BIMANSjkdiTZiNav && docker run -d -e ENV_NAME=dev-env --link fc_postgres -p 80:9000 --name flash-cards-web-app zinjvi/flash-cards-web-app:1.0-SNAPSHOT'

popd



