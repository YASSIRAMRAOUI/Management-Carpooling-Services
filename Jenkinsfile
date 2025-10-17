pipeline {
    agent any
    
    environment {
        // SonarQube configuration
        SONARQUBE_SERVER = 'sonar_integration'
        
        // Maven configuration
        MAVEN_OPTS = '-Dmaven.repo.local=.m2/repository'
    }
    
    // Tools block removed to avoid requiring named tool installations on Jenkins controller/agents
    
    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out source code...'
                checkout scm
            }
        }
        
        stage('Build') {
            steps {
                echo 'Building the application...'
                script {
                    if (isUnix()) {
                        sh 'mvn -version'
                        sh 'mvn clean compile -DskipTests'
                    } else {
                        bat 'mvn -version'
                        bat 'mvn clean compile -DskipTests'
                    }
                }
            }
        }
        
        stage('Unit Tests') {
            steps {
                echo 'Running unit tests...'
                script {
                    if (isUnix()) {
                        sh 'mvn test'
                    } else {
                        bat 'mvn test'
                    }
                }
            }
            post {
                always {
                    // Publish test results using basic Jenkins functionality
                    script {
                        if (fileExists('target/surefire-reports')) {
                            junit testResults: 'target/surefire-reports/*.xml', allowEmptyResults: true
                        }
                    }
                    
                    // Archive coverage reports as artifacts instead of using publishCoverage
                    script {
                        if (fileExists('target/site/jacoco')) {
                            archiveArtifacts artifacts: 'target/site/jacoco/**', allowEmptyArchive: true
                        }
                    }
                }
            }
        }
        
        stage('Code Quality Analysis') {
            parallel {
                stage('SonarQube Analysis') {
                    steps {
                        echo 'Running SonarQube analysis...'
                        withSonarQubeEnv('sonar_integration') {
                            script {
                                def sonarCmd = 'mvn sonar:sonar ' +
                                              '-Dsonar.projectKey=carpooling-service ' +
                                              '-Dsonar.projectName="Carpooling Service" ' +
                                              "-Dsonar.projectVersion=${BUILD_NUMBER} " +
                                              '-Dsonar.sources=src/main/java ' +
                                              '-Dsonar.tests=src/test/java ' +
                                              '-Dsonar.java.binaries=target/classes ' +
                                              '-Dsonar.junit.reportPaths=target/surefire-reports ' +
                                              '-Dsonar.jacoco.reportPaths=target/jacoco.exec ' +
                                              '-Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml'
                                if (isUnix()) {
                                    sh sonarCmd
                                } else {
                                    bat sonarCmd
                                }
                            }
                        }
                    }
                }
                
                stage('Checkstyle Analysis') {
                    steps {
                        echo 'Running Checkstyle analysis...'
                        script {
                            int rc = 0
                            if (isUnix()) {
                                rc = sh(script: 'mvn checkstyle:check', returnStatus: true)
                            } else {
                                rc = bat(script: 'mvn checkstyle:check', returnStatus: true)
                            }
                            if (rc != 0) {
                                echo 'Checkstyle violations detected (non-blocking).'
                            }
                        }
                    }
                    post {
                        always {
                            // Archive checkstyle results as artifacts instead of using recordIssues
                            script {
                                if (fileExists('target/checkstyle-result.xml')) {
                                    archiveArtifacts artifacts: 'target/checkstyle-result.xml', allowEmptyArchive: true
                                }
                            }
                        }
                    }
                }
            }
        }
        
        stage('Quality Gate') {
            steps {
                echo 'Waiting for SonarQube Quality Gate...'
                timeout(time: 10, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: false
                }
            }
        }
        
        stage('Package') {
            steps {
                echo 'Creating WAR package...'
                script {
                    if (isUnix()) {
                        sh 'mvn package -DskipTests'
                    } else {
                        bat 'mvn package -DskipTests'
                    }
                }
            }
            post {
                success {
                    // Archive artifacts
                    archiveArtifacts artifacts: 'target/*.war', fingerprint: true
                }
            }
        }
        
        stage('Security Scan') {
            steps {
                echo 'Running OWASP Dependency Check...'
                script {
                    def depCheckCmd = 'mvn org.owasp:dependency-check-maven:check -DfailBuildOnCVSS=7 -DsuppressionFile=suppressions.xml'
                    int rc = 0
                    if (isUnix()) {
                        rc = sh(script: depCheckCmd, returnStatus: true)
                    } else {
                        rc = bat(script: depCheckCmd, returnStatus: true)
                    }
                    if (rc != 0) {
                        echo 'OWASP Dependency Check reported issues (non-blocking).'
                    }
                }
            }
            post {
                always {
                    // Archive OWASP dependency check results as artifacts instead of using dependencyCheckPublisher
                    script {
                        if (fileExists('target/dependency-check-report.xml')) {
                            archiveArtifacts artifacts: 'target/dependency-check-report.xml', allowEmptyArchive: true
                        }
                    }
                }
            }
        }
        
        stage('Publish Artifacts') {
            steps {
                echo 'Publishing build artifacts...'
                script {
                    // Archive the WAR file for deployment pipeline
                    archiveArtifacts artifacts: 'target/*.war', fingerprint: true, allowEmptyArchive: false
                    
                    // Create build metadata for CD pipeline
                    writeFile file: 'build-metadata.json', text: """
{
    "buildNumber": "${BUILD_NUMBER}",
    "gitCommit": "${env.GIT_COMMIT}",
    "gitBranch": "${env.BRANCH_NAME}",
    "warFile": "target/demo.war",
    "timestamp": "${new Date().format('yyyy-MM-dd HH:mm:ss')}",
    "testResults": {
        "passed": true,
        "coverageReportPath": "target/site/jacoco/index.html"
    },
    "qualityGate": {
        "sonarQubeUrl": "${env.SONAR_HOST_URL}",
        "projectKey": "carpooling-service"
    }
}
"""
                    archiveArtifacts artifacts: 'build-metadata.json', fingerprint: true
                }
            }
        }
    }
    
    post {
        always {
            echo 'Pipeline execution completed.'
        }
        
        success {
            echo 'CI Pipeline succeeded!'
            
            // Send success notification (optional - requires email configuration)
            script {
                try {
                    if (env.CHANGE_AUTHOR_EMAIL) {
                        emailext (
                            subject: "✅ CI SUCCESS: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
                            body: """
                                🎉 CI Pipeline completed successfully!
                                
                                📊 Build Details:
                                - Job: ${env.JOB_NAME}
                                - Build Number: ${env.BUILD_NUMBER}
                                - Branch: ${env.BRANCH_NAME}
                                - Commit: ${env.GIT_COMMIT}
                                - Build URL: ${env.BUILD_URL}
                                
                                📦 Artifacts Ready:
                                - WAR File: target/demo.war
                                - Coverage Report: Available in build artifacts
                                - Build Metadata: build-metadata.json
                                
                                ✅ Ready for deployment pipeline!
                            """,
                            to: "${env.CHANGE_AUTHOR_EMAIL}"
                        )
                    }
                } catch (Exception e) {
                    echo "Email notification failed: ${e.getMessage()}"
                }
            }
        }
        
        failure {
            echo 'CI Pipeline failed!'
            
            // Send failure notification (optional - requires email configuration)
            script {
                try {
                    if (env.CHANGE_AUTHOR_EMAIL) {
                        emailext (
                            subject: "❌ CI FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
                            body: """
                                💥 CI Pipeline failed!
                                
                                📊 Build Details:
                                - Job: ${env.JOB_NAME}
                                - Build Number: ${env.BUILD_NUMBER}
                                - Branch: ${env.BRANCH_NAME}
                                - Build URL: ${env.BUILD_URL}
                                
                                🔍 Please check the logs and fix the issues before deployment.
                            """,
                            to: "${env.CHANGE_AUTHOR_EMAIL}"
                        )
                    }
                } catch (Exception e) {
                    echo "Email notification failed: ${e.getMessage()}"
                }
            }
        }
        
        unstable {
            echo 'CI Pipeline is unstable!'
            
            // Send unstable notification (optional - requires email configuration)  
            script {
                try {
                    if (env.CHANGE_AUTHOR_EMAIL) {
                        emailext (
                            subject: "⚠️ CI UNSTABLE: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
                            body: """
                                ⚠️ CI Pipeline completed but is unstable!
                                
                                📊 Build Details:
                                - Job: ${env.JOB_NAME}
                                - Build Number: ${env.BUILD_NUMBER}
                                - Branch: ${env.BRANCH_NAME}
                                - Build URL: ${env.BUILD_URL}
                                
                                ⚡ Issues Found:
                                - Quality gate warnings
                                - Test instability
                                - Security vulnerabilities
                                
                                🔍 Please review warnings before proceeding with deployment.
                            """,
                            to: "${env.CHANGE_AUTHOR_EMAIL}"
                        )
                    }
                } catch (Exception e) {
                    echo "Email notification failed: ${e.getMessage()}"
                }
            }
        }
    }
}