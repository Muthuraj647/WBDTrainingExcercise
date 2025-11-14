def projectConfigs = [
    githubBranch      : "${params.PROJECT_BRANCH}",
    projectPath       : "SMS"
]

try{
  currentBuild.result = 'SUCCESS'
  stages{
    stage('Checkout'){
      
    }
  }
}
catch( e ){
    currentBuild.result = 'FAILURE'
    throw e
}
finally{
    echo "Completed"
}
