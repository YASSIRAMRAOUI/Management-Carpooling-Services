#!/bin/sh
# Maven Wrapper Shell Script
# Licensed under the Apache License, Version 2.0

BASEDIR=$(dirname "$0")
WRAPPER_JAR="$BASEDIR/.mvn/wrapper/maven-wrapper.jar"
WRAPPER_PROPERTIES="$BASEDIR/.mvn/wrapper/maven-wrapper.properties"

if [ ! -f "$WRAPPER_JAR" ]; then
  echo "Downloading Maven Wrapper..."
  mkdir -p "$BASEDIR/.mvn/wrapper"
  WRAPPER_URL=$(grep -E "^wrapperUrl=" "$WRAPPER_PROPERTIES" | cut -d'=' -f2)
  curl -fsSL -o "$WRAPPER_JAR" "$WRAPPER_URL"
fi

MAVEN_DIST_URL=$(grep -E "^distributionUrl=" "$WRAPPER_PROPERTIES" | cut -d'=' -f2)

# Set Java opts if provided
MVNW_JAVA="java"

exec "$MVNW_JAVA" -jar "$WRAPPER_JAR" -Dmaven.home="$BASEDIR/.mvn" -Dmaven.multiModuleProjectDirectory="$BASEDIR" -DdistributionUrl="$MAVEN_DIST_URL" "$@"