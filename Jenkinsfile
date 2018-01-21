pipeline {
    agent any
    tools {
        maven 'Maven 3.5.x'
        jdk 'JDK8'
    }
    environment {
        CF_CREDS = credentials('pcf')
    }
    stages {
        stage('Build') {
            steps {
                echo "${env.BRANCH_NAME}, falling back to the master branch build if there are no builds"
                sh 'mvn clean install'
            }
            post {
                always {
                    junit '**/target/surefire-reports/**/*.xml'
                }
            }
        }
    }
}