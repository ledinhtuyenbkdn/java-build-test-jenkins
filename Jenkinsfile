pipeline {
agent any
tools {
maven 'maven 3'
jdk 'Java 17'
}
environment {
JAVA_HOME = '/var/jenkins_home/tools/hudson.model.JDK/Java_17/jdk-17.0.10/bin'
DB_ENGINE    = 'sqlite'
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
            junit 'target/surefire-reports/*.xml'
        }

        success {
            script {
                def summary = junit testResults: 'target/surefire-reports/TEST-*.xml'

                // Compose message for Telegram
                echo 'totalcount: ' + summary.totalCount
            }
        }

        failure {
            // Send notification or take corrective action on build failure
            echo 'Build failed! Check logs for details.'
        }
    }

}
