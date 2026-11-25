def call(String imageName, String imageTag, String credsId) {
    echo "Building image ${imageName}:${imageTag}"
    sh "docker build -t ${imageName}:${imageTag} ."

    echo 'Scanning image...'
    sh "trivy image --severity HIGH,CRITICAL --exit-code 0 --scanners vuln --timeout 20m --no-progress ${imageName}:${imageTag}"

    echo 'Pushing image to DockerHub...'
    withCredentials([usernamePassword(credentialsId: credsId,
                                      usernameVariable: 'DH_USER',
                                      passwordVariable: 'DH_PASS')]) {
        sh 'echo $DH_PASS | docker login -u $DH_USER --password-stdin'
        sh "docker push ${imageName}:${imageTag}"
        sh 'docker logout'
    }

    echo 'Deleting image locally...'
    sh "docker rmi ${imageName}:${imageTag}"
}
