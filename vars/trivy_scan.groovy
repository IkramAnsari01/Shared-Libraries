def call() {
    powershell """
        Write-Host "Pulling Trivy image..."
        docker pull aquasec/trivy:latest

        Write-Host "Running Trivy scan..."
        docker run --rm `
            -v "${env.WORKSPACE}:/workspace" `
            aquasec/trivy:latest fs /workspace `
            --severity HIGH,CRITICAL `
            --ignore-unfixed `
            --format table `
            --output /workspace/trivy-report.txt `
            --exit-code 0

        Write-Host "Removing Trivy image..."
        docker rmi aquasec/trivy:latest
    """
}
