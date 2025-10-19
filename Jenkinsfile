pipeline {
    agent any

    environment {
        SONARQUBE_ENV = 'sonar_integration'   // Jenkins credential ID
        SONAR_PROJECT_KEY = 'tp_devops'
        SONAR_PROJECT_NAME = 'tp_devops'
        MAVEN_HOME = tool 'Default Maven'
    }

    stages {

        stage('1️⃣ Clone Repository') {
            steps {
                echo "📥 Cloning repository..."
                checkout scm
            }
        }

        stage('2️⃣ Compile Project') {
            steps {
                echo "🏗️ Compiling the code..."
                sh "${MAVEN_HOME}/bin/mvn clean compile"
            }
        }

        stage('3️⃣ Run Unit Tests') {
            steps {
                echo "🧪 Running tests..."
                sh "${MAVEN_HOME}/bin/mvn test"
            }
        }

        stage('4️⃣ Package Application') {
            steps {
                echo "📦 Packaging the application..."
                sh "${MAVEN_HOME}/bin/mvn package -DskipTests"
            }
            post {
                success {
                    archiveArtifacts artifacts: 'target/*.jar, target/*.war', fingerprint: true
                }
            }
        }

        stage('5️⃣ SonarQube Analysis') {
            steps {
                echo "🔍 Running SonarQube analysis..."
                withSonarQubeEnv('sonar_integration') {
                    sh """
                        ${MAVEN_HOME}/bin/mvn sonar:sonar \
                        -Dsonar.projectKey=${SONAR_PROJECT_KEY} \
                        -Dsonar.projectName=${SONAR_PROJECT_NAME} \
                        -Dsonar.sources=src \
                        -Dsonar.java.binaries=target/classes
                    """
                }
            }
        }
    }

    post {
        always {
            echo "🧹 Cleaning workspace..."
            cleanWs()
        }
        success {
            echo "✅ Pipeline finished successfully!"
        }
        failure {
            echo "❌ Pipeline failed. Check logs for errors."
        }
    }
}
