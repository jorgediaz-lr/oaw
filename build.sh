#!/bin/bash
set -o nounset
set -o errexit
cd oaw
mvn clean install -P docker -DskipTests
cd -
cp portal/target/oaw.war docker/oaw.war
echo
echo "INSTRUCTIONS:"
echo "1. Execute docker-composer up in docker folder"
echo "2. Go to http://localhost:8080/oaw/diagnostico.html"
echo "3. The reports are generated inside the docker/fake-smtp-emails folder"
echo