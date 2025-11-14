def getBuildType(){
    def names = "${env.JOB_NAME}".tokenize('/')
    print (names)
}
def checkoutProject(configs){
    buildType = getBuildType();
    print(configs.githubBranch)
   // print(buildType)
}

def projectConfigs = [
    githubBranch      : "${params.PROJECT_BRANCH}",
    projectPath       : "SMS"
]

try{
  currentBuild.result = 'SUCCESS'
  node('built-in'){
    stage('Checkout'){
         checkoutProject(projectConfigs)     
    }
    stage('YAML Lint Check'){
        
    }
    stage('Test') {
        
    }
    stage('Build'){

        
    }
    stage('Static Analysis'){
        
    }
    stage('Code Coverage'){
        
    }
    stage('Artifact Upload'){
        
    }
    stage('Registry Push'){
        
    }
    stage('Docker Image Scan'){
        
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
