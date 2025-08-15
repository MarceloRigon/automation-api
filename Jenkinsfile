pipeline {
    agent any  

    environment {
        
        MAVEN_HOME = '/usr/local/apache-maven'
    }

    stages {
        stages {
        stage('Clone do repositório') {
            steps {
                git url: 'https://github.com/MarceloRigon/automation_api.git', branch: 'main' 
            }
        }

        stage('Build') {
            steps {
                script {
                    
                    echo 'Rodando mvn clean install'
                    sh 'mvn clean install'
                }
            }
        }

        stage('Testes') {
            steps {
                script {
            
                    echo 'Rodando mvn test'
                    sh 'mvn test'  
                }
            }
        }

    }

    post {
        success {
            echo 'Pipeline finalizada com sucesso!'
        }
        failure {
            echo 'Falha na execução da pipeline!'
        }
    }
    }
}
