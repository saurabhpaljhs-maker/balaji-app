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

                    // Step 1: Purani app band karo
                    // || exit 0 = error aaye toh bhi continue karo
                    bat """
                        @echo off
                        for /f "tokens=5" %%a in ('netstat -aon ^| findstr :${APP_PORT} ^| findstr LISTENING 2^>nul') do (
                            echo Stopping process %%a on port ${APP_PORT}
                            taskkill /F /PID %%a 2>nul
                        )
                        echo Port check done
                        exit 0
                    """

                    // Step 2: logs folder banao
                    bat 'if not exist logs mkdir logs'

                    // Step 3: Nai app start karo background mein
                    bat """
                        @echo off
                        start /B java -jar ${JAR_FILE} ^
                            --server.port=${APP_PORT} ^
                            --spring.profiles.active=${params.DEPLOY_ENV}
                        echo App started in background
                        exit 0
                    """

                    // Step 4: 25 second wait
                    sleep(25)

                    // Step 5: Health check
                    script {
                        def result = bat(
                            script: """
                                @echo off
                                curl -s -o nul -w "%%{http_code}" http://localhost:${APP_PORT}/ 2>nul
                                exit 0
                            """,
                            returnStdout: true
                        ).trim()

                        echo "Health check result: ${result}"

                        if (result.contains('200')) {
                            echo "App is LIVE on http://localhost:${APP_PORT}"
                        } else {
                            echo "App starting... check http://localhost:${APP_PORT} in browser"
                        }
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
            App   : ${APP_NAME} v${APP_VERSION}
            Port  : ${APP_PORT}
            Env   : ${params.DEPLOY_ENV}
            URL   : http://localhost:${APP_PORT}
            Admin : http://localhost:${APP_PORT}/login
            ============================
            """
        }
        failure {
            echo 'BUILD FAILED! Check console output above.'
        }
        always {
            cleanWs(cleanWhenSuccess: false,
                    cleanWhenFailure: false,
                    cleanWhenAborted: true)
        }
    }
}
