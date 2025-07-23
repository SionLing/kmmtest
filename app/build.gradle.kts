plugins {
    id("android-app")
}

android {
    namespace = "com.example.picturecardgallery"
    
    defaultConfig {
        applicationId = "com.example.picturecardgallery"
        versionCode = 1
        versionName = "1.0"

        vectorDrawables {
            useSupportLibrary = true
        }
    }
}

dependencies {
    implementation(project(":x3live-framework"))
    
    // AndroidX Core (using root project definitions)
    DependencyGroups.androidxCore.forEach { implementation(it) }
    implementation(Dependencies.androidxAppcompat)
    
    // Compose (using root project definitions)
    implementation(platform(Dependencies.composeBom))
    DependencyGroups.compose.forEach { implementation(it) }
    
    // Navigation (using root project definitions)
    implementation(Dependencies.navigationCompose)
    
    // App-specific dependencies (using root project definitions)
    implementation(Dependencies.kotlinxSerializationJson)
    implementation(Dependencies.coilCompose)
    
    // Testing (using root project definitions)
    DependencyGroups.testing.forEach { testImplementation(it) }
    androidTestImplementation(platform(Dependencies.composeBom))
    androidTestImplementation(Dependencies.composeUiTestJunit4)
    
    // Debug (using root project definitions)
    DependencyGroups.composeDebug.forEach { debugImplementation(it) }
}