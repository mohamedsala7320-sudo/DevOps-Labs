pipeline {
    agent any

    environment {
        IMAGE      = 'mosala7320/demoapp'
        TAG        = "${BUILD_NUMBER}"
        DOCKERHUB  = credentials('dockerhub-creds')
    }

    stages {
        stage('Build App') {
            steps {
                sh 'mvn -B clean package'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t $IMAGE:$TAG .'
            }
        }

        stage('Scan Docker Image') {
            steps {
                sh 'trivy image --severity HIGH,CRITICAL --exit-code 0 --scanners vuln --timeout 20m --no-progress $IMAGE:$TAG'
            }
        }

        stage('Push Image to DockerHub') {
            steps {
                sh 'echo $DOCKERHUB_PSW | docker login -u $DOCKERHUB_USR --password-stdin'
                sh 'docker push $IMAGE:$TAG'
                sh 'docker logout'
            }
        }

        stage('Delete Image Locally') {
            steps {
                sh 'docker rmi $IMAGE:$TAG'
            }
        }

        stage('Update Image in Deployment File') {
            steps {
                sh "sed -i 's|image: .*|image: ${IMAGE}:${TAG}|' k8s/deployment.yaml"
                sh 'grep image: k8s/deployment.yaml'
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                sh 'kubectl apply -f k8s/'
                sh 'kubectl rollout status deployment/demoapp -n demo --timeout=180s'
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
