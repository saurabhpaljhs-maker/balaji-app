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
        APP_NAME    = 'balaji-frames'
        APP_VERSION = '1.0.0'
        JAR_FILE    = "target\\${APP_NAME}-${APP_VERSION}.jar"
        APP_PORT    = '9090'
        // JAR ko permanent folder mein copy karenge
        DEPLOY_DIR  = 'C:\\balaji-deploy'
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
                echo "Commit: ${env.GIT_COMMIT_MSG}"
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
                echo "Deploying ${APP_NAME} to port ${APP_PORT}..."
                script {

                    // Step 1: Deploy folder banao
                    bat """
                        @echo off
                        if not exist ${DEPLOY_DIR} mkdir ${DEPLOY_DIR}
                        if not exist ${DEPLOY_DIR}\\logs mkdir ${DEPLOY_DIR}\\logs
                        echo Deploy folder ready
                        exit 0
                    """

                    // Step 2: JAR ko permanent folder mein copy karo
                    bat """
                        @echo off
                        copy /Y ${JAR_FILE} ${DEPLOY_DIR}\\${APP_NAME}.jar
                        echo JAR copied to ${DEPLOY_DIR}
                        exit 0
                    """

                    // Step 3: Purani app band karo
                    bat """
                        @echo off
                        for /f "tokens=5" %%a in ('netstat -aon ^| findstr :${APP_PORT} ^| findstr LISTENING 2^>nul') do (
                            taskkill /F /PID %%a 2>nul
                        )
                        timeout /t 3 /nobreak >nul
                        echo Port cleared
                        exit 0
                    """

                    // Step 4: App ko Windows Task Scheduler se start karo
                    // Ye Jenkins se bilkul alag process hogi
                    bat """
                        @echo off
                        schtasks /Delete /TN "BalajiFrames" /F 2>nul
                        schtasks /Create /TN "BalajiFrames" /TR "java -jar ${DEPLOY_DIR}\\${APP_NAME}.jar --server.port=${APP_PORT} --spring.profiles.active=${params.DEPLOY_ENV}" /SC ONCE /ST 00:00 /F
                        schtasks /Run /TN "BalajiFrames"
                        echo App started via Task Scheduler
                        exit 0
                    """

                    // Step 5: Wait karo
                    sleep(25)

                    // Step 6: Health check
                    def result = bat(
                        script: """
                            @echo off
                            curl -s -o nul -w "%%{http_code}" http://localhost:${APP_PORT}/ 2>nul
                            exit 0
                        """,
                        returnStdout: true
                    ).trim()

                    echo "Health check: ${result}"

                    if (result.contains('200')) {
                        echo "APP IS LIVE! http://localhost:${APP_PORT}"
                    } else {
                        echo "App starting... open http://localhost:${APP_PORT}"
                    }
                }
            }
        }
    }

    post {
        success {
            echo """
            ============================
            SUCCESS!
            URL   : http://localhost:${APP_PORT}
            Admin : http://localhost:${APP_PORT}/login
            ============================
            """
        }
        failure {
            echo 'BUILD FAILED!'
        }
        always {
            cleanWs(cleanWhenSuccess: false,
                    cleanWhenFailure: false,
                    cleanWhenAborted: true)
        }
    }
}
