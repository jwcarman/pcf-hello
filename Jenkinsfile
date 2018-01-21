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
                def branchName = env.BRANCH_NAME
                if (branchName.equals("master")) {
                    sh "mvn clean install -Drevision=1.0.0-SNAPSHOT"
                } else if (branchName.startsWith("releases/")) {
                    def releaseName = branchName.substring(branchName.indexOf("/") + 1)
                    sh "mvn clean install -Drevision=${releaseName}.${env.BUILD_NUMBER}"
                }
            }
            post {
                always {
                    junit '**/target/surefire-reports/**/*.xml'
                }
            }
        }
    }
}