pipeline {
    agent any 
    stages {
	stage('Build Maven'){
		steps{
			echo 'Project compile stage'
			bat label: 'Compilation running', script: '''mvn clean package'''
	       	}
	}
	
	stage('Run Gatling') {
	   steps {
			echo 'Project Performance Testing stage'
			bat label: 'Test running', script: '''mvn gatling:test'''
	       
       		}
		post {
            always {
                    gatlingArchive()
                }
            }
		}      	
	    
   }
}