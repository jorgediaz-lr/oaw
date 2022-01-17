#!/bin/bash
set -o nounset
set -o errexit
cd oaw
mvn clean install -P docker -DskipTests