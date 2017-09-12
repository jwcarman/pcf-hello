pipeline {
    agent any
    tools {
        maven 'Maven 3.5.x'
        jdk 'JDK8'
    }
    environment {
        CF_CREDS     = credentials('pcf')
    }
    stages {
        stage ('Initialize') {
            steps {
                sh '''
                    mvn -v
                '''
            }
        }

        stage ('Build') {
            steps {
                sh 'mvn -Dmaven.test.failure.ignore=true clean install'
            }
            post {
                success {
                    junit '**/target/surefire-reports/**/*.xml'
                }
            }
        }

        stage('Deploy') {
            steps {
                sh 'mvn cf:push -Dcf.password=$CF_CREDS_PSW'
            }
        }
    }
}