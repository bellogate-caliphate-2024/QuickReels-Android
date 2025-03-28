pipeline {
    agent any

    environment {
        // Define any environment variables here
        // For example, you might need to set up Android SDK paths
        ANDROID_HOME = '/path/to/android/sdk'
        PATH = "${ANDROID_HOME}/tools:${ANDROID_HOME}/platform-tools:${env.PATH}"
    }

    stages {
        stage('Clone Repository') {
            steps {
                git branch: 'main', url: 'https://github.com/bellogate-caliphate-2024/QuickReels-Android.git'
            }
        }

        stage('Build APK') {
            steps {
                sh './gradlew assembleDebug'
            }
        }

        stage('Archive Artifacts') {
            steps {
                archiveArtifacts artifacts: 'app/build/outputs/apk/debug/app-debug.apk', allowEmptyArchive: true
            }
        }
    }

    post {
        success {
            echo 'Build succeeded and APK is archived.'
        }
        failure {
            echo 'Build failed.'
        }
    }
}
