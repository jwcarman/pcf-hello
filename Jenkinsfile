node {
    stage('Preparation') {
        checkout scm
    }
    stage('Build') {
        sshagent(['8e1d97c5-7949-489a-b28c-54b17e7006e8']) {
            withMaven(jdk: 'JDK8', maven: 'Maven 3.5.x', mavenSettingsConfig: 'maven-settings') {
                def branchName = env.BRANCH_NAME
                echo "Building branch ${branchName}..."
                if("master".equals(branchName)) {
                    sh "mvn clean test sonar:sonar deploy -DdeployAtEnd=true"
                } else if (branchName.startsWith("releases/")) {
                    def releaseName = branchName.substring(branchName.indexOf("/") + 1)
                    sh "mvn clean versions:set versions:commit scm:commit test sonar:sonar install scm:push deploy \"-DnewVersion=${releaseName}.${env.BUILD_NUMBER} -DdeployAtEnd\""
                }

            }
        }
    }
    stage('Results') {
        junit '**/target/surefire-reports/TEST-*.xml'
        archive '**/target/*.jar'
    }
}