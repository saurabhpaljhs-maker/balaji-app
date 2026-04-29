pipeline {
    agent any

    tools {
        maven 'Maven-3.9'
        jdk   'JDK-21'
    }

    parameters {
        choice(name: 'DEPLOY_ENV',
               choices: ['dev', 'staging', 'prod'],
               description: 'Target environment')
        booleanParam(name: 'SKIP_TESTS',
                     defaultValue: false,
                     description: 'Skip tests?')
    }

    environment {
        APP_NAME = 'balaji-frames'
        APP_VERSION = '1.0.0'
        JAR_FILE = "target/${APP_NAME}-${APP_VERSION}.jar"
        APP_PORT = '9090'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
                script {
                    env.GIT_COMMIT_MSG = bat(
                        script: 'git log -1 --pretty=%%B',
                        returnStdout: true
                    ).trim()
                }
                echo "Branch: ${env.BRANCH_NAME} | Commit: ${env.GIT_COMMIT_MSG}"
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile -q'
            }
        }

        stage('Test') {
            when {
                expression { !params.SKIP_TESTS }
            }
            steps {
                bat 'mvn test'
            }
            post {
                always {
                    junit allowEmptyResults: true,
                          testResults: '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Package') {
            steps {
                bat 'mvn package -DskipTests -q'
                archiveArtifacts artifacts: 'target/*.jar',
                                 fingerprint: true,
                                 allowEmptyArchive: false
            }
        }

        stage('Deploy') {
            steps {
                echo "Deploying to ${params.DEPLOY_ENV} on port ${APP_PORT}..."
                script {

                    // Step 1: Purani app band karo
                    bat """
                        for /f "tokens=5" %%a in ('netstat -aon ^| findstr :${APP_PORT} ^| findstr LISTENING') do (
                            taskkill /F /PID %%a
                        )
                    """

                    // Step 2: logs folder banao
                    bat 'if not exist logs mkdir logs'

                    // Step 3: Nai app start karo background mein
                    bat """
                        start /B java -jar ${JAR_FILE} ^
                            --server.port=${APP_PORT} ^
                            --spring.profiles.active=${params.DEPLOY_ENV} ^
                            > logs\\app.log 2>&1
                    """

                    // Step 4: 20 second wait karo app start hone do
                    sleep(20)

                    // Step 5: Health check
                    def status = bat(
                        script: "curl -s -o nul -w %%{http_code} http://localhost:${APP_PORT}/",
                        returnStdout: true
                    ).trim()

                    echo "Health check status: ${status}"

                    if (status.contains('200')) {
                        echo "App is UP on port ${APP_PORT}!"
                    } else {
                        echo "Warning: App may not be ready yet. Check logs/app.log"
                    }
                }
            }
        }
    }

    post {
        success {
            echo """
            ============================
            BUILD + DEPLOY SUCCESS!
            App: ${APP_NAME} v${APP_VERSION}
            Port: ${APP_PORT}
            Env: ${params.DEPLOY_ENV}
            URL: http://localhost:${APP_PORT}
            ============================
            """
        }
        failure {
            echo 'BUILD FAILED! Check console output.'
        }
        always {
            // JAR delete mat karo - cleanWhenSuccess: false
            cleanWs(cleanWhenSuccess: false,
                    cleanWhenFailure: false,
                    cleanWhenAborted: true)
        }
    }
}
