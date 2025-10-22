# Build stage: compile the WAR using Maven with JDK 11
FROM maven:3.9.6-eclipse-temurin-11 AS build
WORKDIR /app

# Copy pom first to leverage Docker layer caching
COPY pom.xml ./
# Copy the source code
COPY src ./src

# Package the application (skip tests for faster image builds)
RUN mvn -B -DskipTests package

# Runtime stage: Tomcat 9 with JDK 11 (avoid Tomcat 10+ due to Jakarta namespace)
FROM tomcat:9.0-jdk11-temurin

# Remove default webapps and deploy our WAR as ROOT.war
RUN rm -rf /usr/local/tomcat/webapps/*
COPY --from=build /app/target/demo.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 7070
CMD ["catalina.sh", "run"]
