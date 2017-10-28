#!groovy

pipeline {
    agent {
        docker {
            image 'maven:3.5.0-jdk-8'
            args '-v $HOME/.m2:/root/.m2'
        }
    }
    stages {
        stage('Build App') {
            steps {
                ansiColor('xterm') {
                    sh 'mvn clean install -DskipTests=true jxr:jxr jxr:test-jxr'
                }
            }
        }
        stage ('Tests and Quality Checks') {
            parallel {
                stage('Unit Tests') {
                    steps {
                        ansiColor('xterm') {
                            sh 'mvn test'
                            step([$class: 'Publisher', reportFilenamePattern: '**/testng-results.xml'])
                        }
                    }
                }
                stage('PMD') {
                    steps {
                        ansiColor('xterm') {
                            sh 'mvn pmd:pmd'
                            pmd(pattern: '**/pmd.xml', failedTotalAll: '30', healthy: '5', unHealthy: '25', unstableTotalAll: '20')
                        }
                    }
                }
                stage('Checkstyle') {
                    steps {
                        ansiColor('xterm') {
                            sh 'mvn checkstyle:checkstyle'
                            checkstyle(pattern: '**/checkstyle.xml', failedTotalAll: '30', healthy: '5', unHealthy: '25', unstableTotalAll: '20')
                        }
                    }
                }
                stage('Warnings') {
                    steps {
                        ansiColor('xterm') {
                            warnings(consoleParsers: [[parserName: 'Maven']], failedTotalAll: '35', healthy: '10', unHealthy: '30', unstableTotalAll: '25')
                        }
                    }
                }
            }
        }
    }
}
