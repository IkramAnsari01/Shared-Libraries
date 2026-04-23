def call(String SonarQubeAPI, String Projectname, String ProjectKey){
  withSonarQubeEnv("${SonarQubeAPI}"){
      bat """
      %SONAR_HOME%\\bin\\sonar-scanner.bat ^
      -Dsonar.projectName=${Projectname} ^
      -Dsonar.projectKey=${ProjectKey} ^
      -X
      """
  }
}

