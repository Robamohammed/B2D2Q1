pipeline {
    agent any

    stages {

        stage('Test') {
            steps {
                
                sh "mvn test"
                echo "Webhook Test 3"

            }

            post {
                always {
                    junit '**/target/surefire-reports/TEST-*.xml'
                }
            }
        }

     
        
    }
}
