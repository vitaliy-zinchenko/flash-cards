#!/usr/bin/env bash

set -e

pushd ../app/ui

npm install
webpack --mode build --progress

popd
pushd ../

sbt clean
sbt elastic-beanstalk:dist
eb deploy

popd



