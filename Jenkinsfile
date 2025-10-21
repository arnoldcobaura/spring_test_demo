pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Corregir esto
                git url: 'https://github.com/arnoldcobaura/', branch: 'main', credentialsId: 'jenkins'
            }
        }

        stage('Build') {
            steps {
                // Run the Maven clean package command
                sh './mvnw clean package'
            }
        }
        
        stage('Copiando JAR to Local Folder') {
            steps {
                // Copy the JAR file from the workspace to a local folder
                sh "cp \$WORKSPACE/target/appdemo-0.0.1-SNAPSHOT.jar /Users/manuelcoba/Arnold/01_Projects/01_my"
            }
        }

        stage('Ejecutando JAR') {
            steps {
                // Run the JAR file
                sh "java -jar /Users/manuelcoba/Arnold/01_Projects/01_my/03_spring_test-0.0.1-SNAPSHOT.jar"
            }
        }
    }
}
