def call() {
    sh """
        echo "Pulling Trivy image..."
        docker pull aquasec/trivy:latest

        echo "Running Trivy scan..."
        docker run --rm \
            -v /var/run/docker.sock:/var/run/docker.sock \
            -v \$(pwd):/workspace \
            -w /workspace \
            aquasec/trivy:latest fs . \
            --severity HIGH,CRITICAL \
            --ignore-unfixed \
            --format table \
            --output trivy-report.txt \
            --exit-code 0

        echo "Removing Trivy image..."
        docker rmi aquasec/trivy:latest || true
    """
}
