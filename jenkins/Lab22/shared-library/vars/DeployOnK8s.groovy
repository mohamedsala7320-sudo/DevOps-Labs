def call(String imageName, String imageTag) {
    echo 'Updating image in deployment file...'
    sh "sed -i 's|image: .*|image: ${imageName}:${imageTag}|' k8s/deployment.yaml"
    sh 'grep image: k8s/deployment.yaml'

    echo 'Deploying to Kubernetes...'
    sh 'kubectl apply -f k8s/'
    sh 'kubectl rollout status deployment/demoapp -n demo --timeout=180s'
}
