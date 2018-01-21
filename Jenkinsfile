node {
    stage('Build') {

        checkout scm

        withMaven(jdk: 'JDK8', maven: 'Maven 3.5.x') {
            def branchName = env.BRANCH_NAME
            echo "Building branch ${branchName}..."
            if (branchName.equals("master")) {

                sh "mvn clean install"
            } else if (branchName.startsWith("releases/")) {
                def releaseName = branchName.substring(branchName.indexOf("/") + 1)
                sh "mvn clean install \"-Drevision=${releaseName}.${env.BUILD_NUMBER}\""
            }
        }
    }
}