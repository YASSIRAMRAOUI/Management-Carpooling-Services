# Jenkins Pipeline Configuration

This document describes the Jenkins pipeline configuration requirements.

## Prerequisites

### Jenkins Plugins Required:
- Pipeline Plugin
- SonarQube Scanner Plugin
- Git Plugin
- Workspace Cleanup Plugin (optional)
- Email Extension Plugin (optional)

### Credentials Required:
1. **SonarQube Token** (if using SonarQube):
   - Type: Secret text
   - ID: Configure in SonarQube server settings
   - Value: Your SonarQube authentication token

### Global Tool Configuration:

#### Option 1: Use Maven Wrapper (Recommended)
The project includes Maven wrapper files (`mvnw`, `mvnw.cmd`, `.mvn/`) so no Maven installation is required on Jenkins agents.

#### Option 2: Install Maven on Jenkins
If you prefer to install Maven globally:
1. **Maven**:
   - Name: `Maven-3.8.6` (or any name)
   - Install automatically from Apache or specify MAVEN_HOME
   - Add the tools block back to Jenkinsfile if using this option

### SonarQube Configuration:
1. **SonarQube Server**:
   - Go to: Manage Jenkins → Configure System → SonarQube servers
   - Name: `sonar_integration`
   - Server URL: Your SonarQube server URL (e.g., `http://localhost:9000`)
   - Authentication token: Configure if required

## Pipeline Features

1. **Build**: Compiles the Java application using Maven/Maven wrapper
2. **Unit Tests**: Runs JUnit tests with result publishing
3. **Code Quality**: SonarQube analysis and Checkstyle checks
4. **Security Scanning**: OWASP dependency check
5. **Packaging**: Creates WAR file
6. **Artifact Publishing**: Archives build artifacts and metadata

## Environment Variables

The following environment variables are used:

- `SONARQUBE_SERVER`: SonarQube server name in Jenkins (default: `sonar_integration`)
- `MAVEN_OPTS`: Maven options (default: `-Dmaven.repo.local=.m2/repository`)

## Troubleshooting

### Maven Not Found Error
If you see "mvn: not found" error:
1. The pipeline will automatically try to use Maven wrapper (`mvnw` or `mvnw.cmd`)
2. If wrapper is not available, install Maven on Jenkins agent or use global tool configuration

### SonarQube Issues
- Ensure SonarQube server is configured with correct name `sonar_integration`
- Check SonarQube server is running and accessible
- Verify authentication token if required

### No Test Results
- JUnit step allows empty results, so it won't fail if no tests exist
- Tests results will be archived as build artifacts

## Branch Strategy

- **All branches**: Runs complete CI pipeline
- **No deployment**: This is CI-only, use separate CD pipeline for deployment

## Notifications

Email notifications are optional and will only work if:
- Email Extension plugin is installed and configured
- `CHANGE_AUTHOR_EMAIL` environment variable is available
- Jenkins email configuration is set up

Configure email settings in Jenkins global configuration if needed.