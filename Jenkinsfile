node {
    stage('Preparation') {
        checkout scm
    }
    stage('Build') {
        sshagent(['8e1d97c5-7949-489a-b28c-54b17e7006e8']) {
            withSonarQubeEnv {
                withMaven(jdk: 'JDK8', maven: 'Maven 3.5.x', mavenSettingsConfig: 'maven-settings') {
                    def branchName = env.BRANCH_NAME
                    echo "Building branch ${branchName}..."
                    if ("master".equals(branchName)) {
                        sh "mvn clean jacoco:prepare-agent test sonar:sonar deploy -DdeployAtEnd=true"
                    } else if (branchName.startsWith("releases/")) {
                        sh 'git config --global user.email "jenkins@carmanconsulting.com"'
                        sh 'git config --global user.name "Jenkins"'
                        def pom = readMavenPom file: 'pom.xml'
                        def currentVersion = pom.version
                        def releaseVersion = currentVersion.replace("-SNAPSHOT", "")
                        def lastDotIndex = releaseVersion.lastIndexOf('.')
                        def releaseRoot = releaseVersion.substring(0, lastDotIndex)
                        def lastDigit = Integer.parseInt(releaseVersion.substring(lastDotIndex + 1))
                        def devVersion= releaseRoot + "." + (lastDigit + 1) + "-SNAPSHOT"
                        echo "Releasing ${releaseVersion} with development version ${devVersion}..."
                        sh "mvn clean jacoco:prepare-agent test sonar:sonar  release:prepare release:perform -DdeployAtEnd=true -DreleaseVersion=${releaseVersion} -DdevelopmentVersion=${devVersion}"
                    }

                }
            }


        }
    }
    stage('Results') {
        junit '**/target/surefire-reports/TEST-*.xml'
        archive '**/target/*.jar'
    }
}