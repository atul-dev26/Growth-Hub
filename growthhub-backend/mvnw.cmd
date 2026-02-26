@echo off
setlocal

set MAVEN_PROJECTBASEDIR=%~dp0
if "%MAVEN_PROJECTBASEDIR:~-1%"=="\" set MAVEN_PROJECTBASEDIR=%MAVEN_PROJECTBASEDIR:~0,-1%
set WRAPPER_JAR=%MAVEN_PROJECTBASEDIR%\.mvn\wrapper\maven-wrapper.jar

if not exist "%WRAPPER_JAR%" (
  echo Maven wrapper jar not found: "%WRAPPER_JAR%"
  echo Run from the project root or re-download the wrapper jar.
  exit /b 1
)

set JAVA_EXE=java
if defined JAVA_HOME (
  if exist "%JAVA_HOME%\\bin\\java.exe" (
    set JAVA_EXE=%JAVA_HOME%\\bin\\java.exe
  )
)

"%JAVA_EXE%" %MAVEN_OPTS% -classpath "%WRAPPER_JAR%" "-Dmaven.multiModuleProjectDirectory=%MAVEN_PROJECTBASEDIR%" org.apache.maven.wrapper.MavenWrapperMain %*
