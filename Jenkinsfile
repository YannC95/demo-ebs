pipeline {
    agent any

    stages {        
        stage('Build') {
            steps {
                echo '--=====-- Building App  --======-- '
                sh "mvn clean package -DskipTests=true"
                archive "target/*.jar"
            }
        }
        
        stage('Test') {
            steps {
                echo '--=====-- Testingg App  --======-- '
                sh "mvn test"
            }
        }
        
        stage('Deploy') {
            steps {
                echo '--=====-- Deploying App  --======-- '
            }
        }
    }
}