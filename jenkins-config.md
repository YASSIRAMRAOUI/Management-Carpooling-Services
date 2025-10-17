# Jenkins Pipeline Configuration

This document describes the Jenkins pipeline configuration requirements.

## Prerequisites

### Jenkins Plugins Required:
- Pipeline Plugin
- Docker Pipeline Plugin
- SonarQube Scanner Plugin
- JaCoCo Plugin
- Checkstyle Plugin
- OWASP Dependency Check Plugin
- Email Extension Plugin
- Warnings Next Generation Plugin

### Credentials Required:
1. **dockerhub-credentials**: Docker Hub username/password or access token
   - Type: Username with password
   - ID: `dockerhub-credentials`
   - Username: Your Docker Hub username
   - Password: Your Docker Hub password or access token

### Global Tool Configuration:
1. **Maven**:
   - Name: `Maven-3.8.6`
   - Install automatically or specify MAVEN_HOME

2. **JDK**:
   - Name: `JDK-11`
   - Install automatically or specify JAVA_HOME

### SonarQube Configuration:
1. **SonarQube Server**:
   - Name: `SonarQube`
   - Server URL: Your SonarQube server URL
   - Authentication token configured

## Pipeline Features

1. **Build**: Compiles the Java application using Maven
2. **Unit Tests**: Runs JUnit tests with JaCoCo coverage
3. **Code Quality**: SonarQube analysis and Checkstyle checks
4. **Security Scanning**: OWASP dependency check and Trivy image scanning
5. **Packaging**: Creates WAR file
6. **Docker**: Builds and tests Docker image
7. **Deployment**: Pushes to Docker Hub and deploys to staging/production

## Environment Variables

The following environment variables can be configured:

- `DOCKERHUB_CREDENTIALS`: Jenkins credential ID for Docker Hub
- `DOCKER_IMAGE`: Docker image name (default: yassiramraoui/carpooling-service)
- `SONARQUBE_SERVER`: SonarQube server name in Jenkins configuration

## Branch Strategy

- **main/master**: Deploys to production (with manual approval)
- **develop**: Deploys to staging automatically
- **feature branches**: Runs tests and quality checks only

## Notifications

The pipeline sends email notifications on:
- Success (main/master branch only)
- Failure (all branches)
- Unstable builds (all branches)

Configure the email settings in Jenkins global configuration.