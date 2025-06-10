pipeline{
    agent any

    environment{
        PROJECT_NAME = 'locitakaes-dev'
        BUILD_NAME = 'java-bni-project-git'
    }

    stages{
        stage('Trigger Build'){
            steps{
                sh "oc start-build ${BUILD_NAME} --from-dir=. --follow -n ${PROJECT_NAME}"
            }
        }
        stage('Deploy'){
            steps{
                sh "oc rollout restart deployment/${BUILD_NAME} -n ${PROJECT_NAME}"
            }
        }
    }

    post{
        success{
            echo "Build and deployment of ${PROJECT_NAME} completed successfully."
        }
        failure{
            echo "Build or deployment of ${PROJECT_NAME} failed."
        }
    }
}