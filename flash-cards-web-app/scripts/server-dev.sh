#!/usr/bin/env bash

cd ../

sbt clean -jvm-debug 9990 -Dconfig.resource=local-env.conf run