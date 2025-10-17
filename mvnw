#!/bin/sh
# Apache Maven Wrapper startup script (v3.2.0)

set -e

BASEDIR=$(cd "$(dirname "$0")"; pwd -P)
WRAPPER_JAR="$BASEDIR/.mvn/wrapper/maven-wrapper.jar"
WRAPPER_PROPERTIES="$BASEDIR/.mvn/wrapper/maven-wrapper.properties"

download() {
  url="$1"
  out="$2"
  if command -v curl >/dev/null 2>&1; then
    curl -fsSL -o "$out" "$url"
  elif command -v wget >/dev/null 2>&1; then
    wget -q -O "$out" "$url"
  else
    echo "Error: Please install curl or wget to download Maven Wrapper" >&2
    exit 1
  fi
}

if [ ! -f "$WRAPPER_JAR" ]; then
  echo "Downloading Maven Wrapper..."
  mkdir -p "$(dirname "$WRAPPER_JAR")"
  WRAPPER_URL=$(grep -E "^wrapperUrl=" "$WRAPPER_PROPERTIES" | cut -d'=' -f2-)
  download "$WRAPPER_URL" "$WRAPPER_JAR"
fi

JAVA_EXE="${JAVA_HOME:+$JAVA_HOME/bin/}java"
exec "$JAVA_EXE" -classpath "$WRAPPER_JAR" org.apache.maven.wrapper.MavenWrapperMain "$@"