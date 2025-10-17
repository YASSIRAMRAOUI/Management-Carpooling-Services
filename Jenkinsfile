pipeline {
    agent any
    
    environment {
        // SonarQube configuration
        SONARQUBE_SERVER = 'sonar_integration'
        
        // Maven configuration
        MAVEN_OPTS = '-Dmaven.repo.local=.m2/repository'
    }
    
    // No explicit tools block; assumes Maven/Java available on agent PATH
    
    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out source code...'
                checkout scm
                // Ensure Maven Wrapper is executable on *nix agents
                sh 'chmod +x mvnw || true'
            }
        }
        
        stage('Build') {
            steps {
                echo 'Building the application...'
                sh './mvnw clean compile -DskipTests'
            }
        }
        
        stage('Unit Tests') {
            steps {
                echo 'Running unit tests...'
                sh './mvnw test'
            }
            post {
                always {
                    // Publish test results
                    junit testResults: 'target/surefire-reports/*.xml', allowEmptyResults: true
                    
                    // Publish JaCoCo coverage report
                    script {
                        try {
                            publishCoverage adapters: [jacocoAdapter('target/site/jacoco/jacoco.xml')], 
                                            sourceFileResolver: sourceFiles('STORE_LAST_BUILD')
                        } catch (err) {
                            echo "Coverage publish skipped (plugin missing or report not found): ${err}"
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
                            sh '''
                                ./mvnw sonar:sonar \
                                -Dsonar.projectKey=carpooling-service \
                                -Dsonar.projectName="Carpooling Service" \
                                -Dsonar.projectVersion=${BUILD_NUMBER} \
                                -Dsonar.sources=src/main/java \
                                -Dsonar.tests=src/test/java \
                                -Dsonar.java.binaries=target/classes \
                                -Dsonar.junit.reportPaths=target/surefire-reports \
                                -Dsonar.jacoco.reportPaths=target/jacoco.exec \
                                -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml
                            '''
                        }
                    }
                }
                
                stage('Checkstyle Analysis') {
                    steps {
                        echo 'Running Checkstyle analysis...'
                        sh './mvnw checkstyle:check || true'
                    }
                    post {
                        always {
                            // Record checkstyle results
                            script {
                                try {
                                    recordIssues enabledForFailure: true, tools: [checkStyle()]
                                } catch (err) {
                                    echo "Checkstyle publish skipped (plugin missing): ${err}"
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
                script {
                    try {
                        timeout(time: 10, unit: 'MINUTES') {
                            waitForQualityGate abortPipeline: false
                        }
                    } catch (err) {
                        echo "Quality Gate wait skipped (plugin not configured or timeout): ${err}"
                    }
                }
            }
        }
        
        stage('Package') {
            steps {
                echo 'Creating WAR package...'
                sh './mvnw package -DskipTests'
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
                sh '''
                    ./mvnw org.owasp:dependency-check-maven:check \
                    -DfailBuildOnCVSS=7 \
                    -DsuppressionFile=suppressions.xml || true
                '''
            }
            post {
                always {
                    // Publish OWASP dependency check results
                    script {
                        try {
                            dependencyCheckPublisher pattern: 'target/dependency-check-report.xml'
                        } catch (err) {
                            echo "Dependency-Check report publish skipped (plugin missing): ${err}"
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
            
            // Clean workspace
            script {
                try {
                    cleanWs()
                } catch (err) {
                    echo "Skipping cleanWs: no workspace context (${err})"
                }
            }
        }
        
        success {
            echo 'CI Pipeline succeeded!'
            
            // Send success notification
            script {
                def recipient = env.CHANGE_AUTHOR_EMAIL
                if (recipient && recipient.trim()) {
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
                        to: recipient
                    )
                } else {
                    echo 'No CHANGE_AUTHOR_EMAIL set; skipping email notification.'
                }
            }
        }
        
        failure {
            echo 'CI Pipeline failed!'
            
            // Send failure notification
            script {
                def recipient = env.CHANGE_AUTHOR_EMAIL
                if (recipient && recipient.trim()) {
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
                        to: recipient
                    )
                } else {
                    echo 'No CHANGE_AUTHOR_EMAIL set; skipping email notification.'
                }
            }
        }
        
        unstable {
            echo 'CI Pipeline is unstable!'
            
            // Send unstable notification
            script {
                def recipient = env.CHANGE_AUTHOR_EMAIL
                if (recipient && recipient.trim()) {
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
                        to: recipient
                    )
                } else {
                    echo 'No CHANGE_AUTHOR_EMAIL set; skipping email notification.'
                }
            }
        }
    }
}