plugins {
    id("android-library")
}

android {
    namespace = "com.x3live.core"
    
    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    // AndroidX Core (using root project definitions)
    DependencyGroups.androidxCore.forEach { implementation(it) }
    
    // Compose (using root project definitions - minimal for framework)
    implementation(platform(Dependencies.composeBom))
    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeUiToolingPreview)
    implementation(Dependencies.composeMaterial3)
    
    // Navigation Compose (using root project definitions)
    implementation(Dependencies.navigationCompose)
    
    // Testing (using root project definitions)
    DependencyGroups.testing.forEach { testImplementation(it) }
    androidTestImplementation(platform(Dependencies.composeBom))
    androidTestImplementation(Dependencies.composeUiTestJunit4)
    
    // Debug (using root project definitions)
    DependencyGroups.composeDebug.forEach { debugImplementation(it) }
}