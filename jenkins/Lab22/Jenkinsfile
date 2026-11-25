@Library('my-shared-lib') _

pipeline {
    agent { label 'node-1' }

    environment {
        IMAGE = 'mosala7320/demoapp'
        TAG   = "${BUILD_NUMBER}"
    }

    stages {
        stage('Build App') {
            steps {
                BuildApp()
            }
        }

        stage('Build, Scan and Push Image') {
            steps {
                BuildImage(env.IMAGE, env.TAG, 'dockerhub-creds')
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                DeployOnK8s(env.IMAGE, env.TAG)
            }
        }
    }

    post {
        always {
            echo "Pipeline finished with status: ${currentBuild.currentResult}"
        }
        success {
            echo 'Pipeline succeeded: the application was deployed.'
        }
        failure {
            echo 'Pipeline failed: check the console output.'
        }
    }
}
