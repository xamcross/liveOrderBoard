#!/usr/bin/env sh

./gradlew clean build

docker --context default build -t liveorderboard:latest .

docker --context default run -p 8080:8080 liveorderboard:latest