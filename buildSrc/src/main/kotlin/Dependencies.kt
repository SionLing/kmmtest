// Fallback dependencies for standalone usage
// These are used when the framework is built independently

object Versions {
    const val compileSdk = 34
    const val minSdk = 24
    const val targetSdk = 34
    
    const val kotlin = "1.8.10"
    const val androidGradlePlugin = "7.4.2"
    const val kotlinCompilerExtensionVersion = "1.4.3"
    
    // AndroidX (framework essentials only)
    const val androidxCore = "1.9.0"
    const val androidxLifecycle = "2.6.1"
    const val androidxActivity = "1.7.2"
    
    // Compose (framework essentials only)
    const val composeBom = "2023.06.01"
    const val navigationCompose = "2.7.4"
    
    // Testing
    const val junit = "4.13.2"
    const val androidxJunit = "1.1.5"
    const val espresso = "3.5.1"
}

object Dependencies {
    // AndroidX Core (framework essentials)
    const val androidxCore = "androidx.core:core-ktx:${Versions.androidxCore}"
    const val androidxLifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.androidxLifecycle}"
    const val androidxActivityCompose = "androidx.activity:activity-compose:${Versions.androidxActivity}"
    
    // Compose BOM
    const val composeBom = "androidx.compose:compose-bom:${Versions.composeBom}"
    
    // Compose UI (framework essentials)
    const val composeUi = "androidx.compose.ui:ui"
    const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
    const val composeMaterial3 = "androidx.compose.material3:material3"
    
    // Navigation (framework essential)
    const val navigationCompose = "androidx.navigation:navigation-compose:${Versions.navigationCompose}"
    
    // Testing
    const val junit = "junit:junit:${Versions.junit}"
    const val androidxJunit = "androidx.test.ext:junit:${Versions.androidxJunit}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val composeUiTestJunit4 = "androidx.compose.ui:ui-test-junit4"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling"
    const val composeUiTestManifest = "androidx.compose.ui:ui-test-manifest"
}

object DependencyGroups {
    val androidxCore = listOf(
        Dependencies.androidxCore,
        Dependencies.androidxLifecycleRuntime,
        Dependencies.androidxActivityCompose
    )
    
    val testing = listOf(
        Dependencies.junit,
        Dependencies.androidxJunit,
        Dependencies.espressoCore
    )
    
    val composeDebug = listOf(
        Dependencies.composeUiTooling,
        Dependencies.composeUiTestManifest
    )
}