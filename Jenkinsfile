pipeline {
    agent any
    
    environment {
        AWS_ACCESS_KEY_ID       =   credentials("AWS_ACCESSKEY") 
        AWS_SECRET_ACCESS_KEY   =   credentials("AWS_SECRET_ACCESSKEY")
        AWS_S3_BUCKET           =   "yann-demo-ebs-1"
        ARTIFACT_NAME           =   "demo-ebs-jar"
        AWS_EB_APP_NAME         = "yann-demo-ebs"
        AWS_EB_ENVIRONMENT      = "yann-demo-ebs-env"
        AWS_EB_APP_VERSION      = "${BUILD_ID}"
    }

    stages {
        stage('Build') {
            steps {
                echo '--=====-- Building App  --======-- '
                sh "mvn clean package -DskipTests=true"
                archiveArtifacts "target/*.jar"
            }
        }
        
        stage('Test') {
            steps {
                echo '--=====-- Testing App  --======-- '
                sh "mvn test"
            }
            post {
                success {
                    echo '--=====-- Uploading App in S3  --======-- '
                    sh "aws configure set region eu-central-1"
                    sh "aws s3 cp ./target/*.jar s3://$AWS_S3_BUCKET/$ARTIFACT_NAME" 
                }
            }
        }
        
        stage('Deploy') {
            steps {
                echo '--=====-- Deploying App  --======-- '
                sh 'aws elasticbeanstalk create-application-version --application-name $AWS_EB_APP_NAME --version-label $AWS_EB_APP_VERSION --source-bundle S3Bucket=$AWS_S3_BUCKET,S3Key=$ARTIFACT_NAME'
                
                sh 'aws elasticbeanstalk update-environment --application-name $AWS_EB_APP_NAME --environment-name $AWS_EB_ENVIRONMENT --version-label $AWS_EB_APP_VERSION'
            }
        }
    }
}