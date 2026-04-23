def call() {
    sh """
        echo "Pulling Trivy image..."
        docker pull aquasec/trivy:latest

        echo "Running Trivy scan..."
        docker run --rm \
            -v "\$(pwd):/workspace" \
            aquasec/trivy:latest fs /workspace \
            --severity HIGH,CRITICAL \
            --ignore-unfixed \
            --format table \
            --output /workspace/trivy-report.txt \
            --exit-code 0

        echo "Removing Trivy image..."
        docker rmi aquasec/trivy:latest || true
    """
}
