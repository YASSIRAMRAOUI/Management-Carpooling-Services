@ECHO OFF
SETLOCAL

SET BASEDIR=%~dp0
SET WRAPPER_JAR=%BASEDIR%\.mvn\wrapper\maven-wrapper.jar
SET WRAPPER_PROPERTIES=%BASEDIR%\.mvn\wrapper\maven-wrapper.properties

IF NOT EXIST "%WRAPPER_JAR%" (
  ECHO Downloading Maven Wrapper...
  IF NOT EXIST "%BASEDIR%\.mvn\wrapper" MKDIR "%BASEDIR%\.mvn\wrapper"
  FOR /F "tokens=2 delims==" %%A IN ('findstr /R "^wrapperUrl=" "%WRAPPER_PROPERTIES%"') DO SET WRAPPER_URL=%%A
  powershell -Command "Invoke-WebRequest -UseBasicParsing -OutFile '%WRAPPER_JAR%' '%WRAPPER_URL%'"
)

SET JAVA_EXE=java
"%JAVA_EXE%" -classpath "%WRAPPER_JAR%" org.apache.maven.wrapper.MavenWrapperMain %*
