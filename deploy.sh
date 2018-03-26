#!/usr/bin/env bash
mvn -DskipTests clean package
cp target/shopping-1.0-SNAPSHOT.war registration-webserver/ROOT.war
docker-compose build
docker-compose up