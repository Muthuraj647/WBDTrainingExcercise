def checkoutFromGit(configs){
    sh '[ ! -d .git ] || git clean -fdx' //clearing files
    try{
        checkout([$class: 'GitSCM', branches: [[name: "*/${configs.projectGitBranch}"]], userRemoteConfigs: [[url:"${configs.projectGitURL}"]]])
    }catch (e){
       // printStackTrace(e)
        echo "Checkout failed"
        
    }
}

def WBDConfigs = [
    projectGitBranch        :    "${params.PROJECT_BRANCH}",
    projectGitURL           :    "https://github.com/Muthuraj647/WBDTrainingExcercise",
    projectSpringDir        :    "SMS",
    projectNodeJsDir        :    "Login_Module",
    projectGoDir            :    "CMS",
    projectDeploymentDir    :    "kubernetes",
    projectBuildScripts     :     "Scripts"
]

try{
  currentBuild.result = 'SUCCESS'
  node('ubuntu-host'){
    stage('checkout'){
      checkoutFromGit(WBDConfigs)
    }
    stage('Build'){
      dir(WBDConfigs.projectSpringDir){
        sh 'mvn clean install -DskipTests'
      }
    }
    stage('Static Analysis'){
      dir(WBDConfigs.projectSpringDir){
        def sa = load '../Scripts/sa.groovy'
      }
    }
  }
}catch(e){
  currentBuild.result = 'FAILURE'
}
