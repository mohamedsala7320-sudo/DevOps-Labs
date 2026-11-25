def call() {
    echo 'Running unit tests...'
    sh 'mvn -B test'
    echo 'Building the application...'
    sh 'mvn -B clean package -DskipTests'
}
