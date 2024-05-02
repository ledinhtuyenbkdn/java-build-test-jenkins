pipeline {
    agent any
    tools {
        maven 'maven 3'
        jdk 'Java 17'
    }
    environment {
        JAVA_HOME = '/var/jenkins_home/tools/hudson.model.JDK/Java_17/jdk-17.0.10/bin'
        DB_ENGINE = 'sqlite'
        TELEGRAM_BOT_TOKEN = credentials('telegram_bot_token')
    }

    stages {
         stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/ledinhtuyenbkdn/java-build-test-jenkins.git'
            }
        }
        stage('Clean') {
            steps {
                sh 'mvn clean'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
    }

    post {
        always {
            // Archive generated artifacts
            junit 'target/surefire-reports/testng-results.xml'
            script {
                def summary = junit testResults: 'target/surefire-reports/TEST-*.xml'

                // Compose message for Telegram
                sh """curl --location 'https://api.telegram.org/bot${TELEGRAM_BOT_TOKEN}/sendDocument' \
                                    --form 'chat_id=\"-1002114838090\"' \
                                    --form 'document=@\"target/surefire-reports/emailable-report.html\"' \
                                    --form 'caption=\" Total: ${summary.totalCount}, Failures: ${summary.failCount}, Skipped: ${summary.skipCount}, Passed: ${summary.passCount}\"'"""
            }
        }

        success {
            echo 'Build success! Check logs for details.'
        }

        failure {
            echo 'Build failed! Check logs for details.'
        }
    }

}
