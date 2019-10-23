def runScript(command) {
    if (isUnix()) {
        sh script: command
    }
    else {
        bat script: command
    }
}

node {
    stage('Setup') {
        runScript('docker run -d --name postgres-db -e POSTGRES_PASSWORD=mysecretpassword postgres')
        runScript('docker run -d --name budgetapp --link postgres-db:postgres-db -p 8088:8080 -e DB_DRIVER=org.postgresql.Driver -e DB_USERNAME=postgres -e DB_PASSWORD=mysecretpassword -e DB_URL=jdbc:postgresql://postgres-db/postgres -e DB_DIALECT=io.budgetapp.hibernate.dialect.CustomPostgreSQLDialect paukiatwee/budgetapp')
        tool name: 'xvfb', type: 'org.jenkinsci.plugins.xvfb.XvfbInstallation'
    }
    stage('Build project') {
            git 'https://github.com/vladimirzankov/budgetapp-tests'
            //git 'file:////home/vladimir/Yandex.Disk/budgetapp-tests'
        }
    stage('Test'){
          catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
            try {
                    wrap([$class: 'Xvfb', additionalOptions: '', assignedLabels: '', autoDisplayName: true, debug: true, displayNameOffset: 0, installationName: 'xvfb', parallelBuild: true, screen: '1024x758x24', timeout: 25]) {
                        runScript('mvn clean verify')
                    }
                }
            finally {
                junit '**/target/surefire-reports/TEST-*.xml'
            }
        }
    }
    stage('Report'){
        allure includeProperties: false, jdk: '', results: [[path: 'allure-results']]
    }
    stage('Tear down') {
        runScript('docker stop budgetapp')
        runScript('docker stop postgres-db')
        runScript('docker rm budgetapp')
        runScript('docker rm postgres-db')
    }
}