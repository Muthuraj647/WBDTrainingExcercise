//Defining Configurations

def checkoutFromGit(configs){
    sh '[ ! -d .git ] || git clean -fdx' //clearing files
    try{
        checkout([$class: 'GitSCM', branches: [[name: "*/${configs.projectGitBranch}"]], userRemoteConfigs: [[url:"${configs.projectGitURL}"]]])
    }catch (e){
       // printStackTrace(e)
        echo "Checkout failed"
        
    }
}

def yamlLint(configs){
    dir(configs.projectDeploymentDir){
        sh 'yamllint sample-node-depl.yaml'
    }
}

def testBuilds(configs){
    echo "Testing Maven Application"
    
}

def buildMaven(configs){
    echo "Building Maven Application"
    dir(configs.projectSpringDir){
        sh 'mvn clean install -DskipTests'
    }
}

def buildNodeJs(configs){
    echo "Building NodeJS Application"
    sh 'npm install'
    //sh 'npm run build'
}

def buildGoApp(configs){
    sh '''go mod tidy
    go build -o app
    '''
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
    node ('ubuntu-host'){
        stage('Checkout'){
            checkoutFromGit(WBDConfigs)
        }
        stage('YAML Lint Check'){
            yamlLint(WBDConfigs)
        }
        stage('Test'){
            echo 'Test Stage'
            testBuilds(WBDConfigs)
        }
        stage('Build-Java'){
            echo "Build stage - Java"
            buildMaven(WBDConfigs)
        }
        stage('Build - NodeJs'){
            echo "Build stage - NodeJs"
            buildNodeJs(WBDConfigs)
        }
        stage('Build - Go'){
            echo "Build stage - Go"
            //buildGoApp(WBDConfigs)
        }
        stage('Static Analysis'){
            echo "Static Analysis Stage"
        }
        stage('Artificatory Upload'){
            echo "Jfron Upload"
        }
        stage('Docker Build'){
            echo "Docker Build"
        }
        stage('Registry Push'){
            echo "Registry Push"
        }
        stage('Trivy Scan'){
            echo "Trivy Scan"
        }
            
    }
}catch(e){
    printStackTrace(e)
    echo "Job failed"
    currentBuid.result = "FAILURE"
}
