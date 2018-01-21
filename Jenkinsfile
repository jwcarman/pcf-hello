node {
    stage('Build') {
        withMaven(maven: 'Maven 3.5.x', jdk: 'JDK8') {
            def branchName = env.BRANCH_NAME
            if (branchName.equals("master")) {
                sh "mvn clean install -Drevision=1.0.0-SNAPSHOT"
            } else if (branchName.startsWith("releases/")) {
                def releaseName = branchName.substring(branchName.indexOf("/") + 1)
                sh "mvn clean install -Drevision=${releaseName}.${env.BUILD_NUMBER}"
            }
        }
    }
}