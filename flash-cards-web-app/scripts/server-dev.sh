#!/usr/bin/env bash

cd ../

export GOOGLE_CLIENT_ID=563875364656-hhs2f3g7iaaa0vike4a44s816ruiuo1t.apps.googleusercontent.com
export GOOGLE_CLIENT_SECRET=KvmspZsvlP4G4iNNjkdlo01B

echo "GOOGLE_CLIENT_ID=$GOOGLE_CLIENT_ID"
echo "GOOGLE_CLIENT_SECRET=$GOOGLE_CLIENT_SECRET"

sbt clean -jvm-debug 9990 run