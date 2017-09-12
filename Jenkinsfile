pipeline {
    agent any
    tools {
        maven 'Maven 3.5.x'
        jdk 'JDK8'
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
                pushToCloudFoundry cloudSpace: 'development', credentialsId: '686765c5-0546-4864-a071-4ec44c1493dc', manifestChoice: [appName: 'pcf-hello', appPath: '', buildpack: 'java_buildpack', command: '', domain: '', hostname: 'pcf-hello', instances: 1, memory: 1024, stack: '', timeout: 60, value: 'jenkinsConfig'], organization: 'microbule', target: 'https://api.run.pivotal.io'
            }
        }
    }
}