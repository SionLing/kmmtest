# BuildSrc Setup Guide

This guide shows how to set up buildSrc for centralized dependency management once the Java version compatibility issue is resolved.

## Directory Structure

```
buildSrc/
├── build.gradle.kts
└── src/main/kotlin/
    ├── Dependencies.kt
    ├── Versions.kt
    └── AndroidConfig.kt
```

## Files to Create

### 1. buildSrc/build.gradle.kts
```kotlin
plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
}
```

### 2. buildSrc/src/main/kotlin/Versions.kt
```kotlin
object Versions {
    const val compileSdk = 34
    const val minSdk = 24
    const val targetSdk = 34
    const val kotlin = "1.8.10"
    const val kotlinCompilerExtensionVersion = "1.4.3"
    const val androidGradlePlugin = "7.4.2"
    
    // AndroidX
    const val androidxCore = "1.9.0"
    const val androidxLifecycle = "2.6.1"
    const val androidxActivity = "1.7.2"
    const val androidxAppcompat = "1.6.1"
    
    // Compose
    const val composeBom = "2023.06.01"
    const val navigationCompose = "2.7.4"
    
    // Other
    const val kotlinxSerialization = "1.5.1"
    const val coil = "2.4.0"
    
    // Testing
    const val junit = "4.13.2"
    const val androidxJunit = "1.1.5"
    const val espresso = "3.5.1"
}
```

### 3. buildSrc/src/main/kotlin/Dependencies.kt
```kotlin
object Dependencies {
    // AndroidX Core
    const val androidxCore = "androidx.core:core-ktx:${Versions.androidxCore}"
    const val androidxLifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.androidxLifecycle}"
    const val androidxActivityCompose = "androidx.activity:activity-compose:${Versions.androidxActivity}"
    const val androidxAppcompat = "androidx.appcompat:appcompat:${Versions.androidxAppcompat}"
    
    // Compose BOM
    const val composeBom = "androidx.compose:compose-bom:${Versions.composeBom}"
    
    // Compose UI
    const val composeUi = "androidx.compose.ui:ui"
    const val composeUiGraphics = "androidx.compose.ui:ui-graphics"
    const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
    const val composeMaterial3 = "androidx.compose.material3:material3"
    
    // Navigation
    const val navigationCompose = "androidx.navigation:navigation-compose:${Versions.navigationCompose}"
    
    // Other Libraries
    const val kotlinxSerializationJson = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinxSerialization}"
    const val coilCompose = "io.coil-kt:coil-compose:${Versions.coil}"
    
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
    
    val compose = listOf(
        Dependencies.composeUi,
        Dependencies.composeUiGraphics,
        Dependencies.composeUiToolingPreview,
        Dependencies.composeMaterial3
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
```

### 4. buildSrc/src/main/kotlin/AndroidConfig.kt
```kotlin
import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

fun Project.configureAndroid() {
    extensions.configure<BaseExtension> {
        compileSdkVersion(Versions.compileSdk)
        
        defaultConfig {
            minSdk = Versions.minSdk
            targetSdk = Versions.targetSdk
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
        
        buildTypes {
            getByName("release") {
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }
        
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
        
        buildFeatures.compose = true
        
        composeOptions {
            kotlinCompilerExtensionVersion = Versions.kotlinCompilerExtensionVersion
        }
    }
}
```

## Usage in Build Files

### Root build.gradle.kts
```kotlin
plugins {
    id("com.android.application") version Versions.androidGradlePlugin apply false
    id("com.android.library") version Versions.androidGradlePlugin apply false
    id("org.jetbrains.kotlin.android") version Versions.kotlin apply false
}
```

### App build.gradle.kts
```kotlin
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.serialization")
}

configureAndroid()

android {
    namespace = "com.example.picturecardgallery"
    
    defaultConfig {
        applicationId = "com.example.picturecardgallery"
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    implementation(project(":x3live-framework"))
    
    // Use dependency groups
    DependencyGroups.androidxCore.forEach { implementation(it) }
    implementation(Dependencies.androidxAppcompat)
    
    // Compose
    implementation(platform(Dependencies.composeBom))
    DependencyGroups.compose.forEach { implementation(it) }
    
    // Other
    implementation(Dependencies.navigationCompose)
    implementation(Dependencies.kotlinxSerializationJson)
    implementation(Dependencies.coilCompose)
    
    // Testing
    DependencyGroups.testing.forEach { testImplementation(it) }
    androidTestImplementation(platform(Dependencies.composeBom))
    androidTestImplementation(Dependencies.composeUiTestJunit4)
    
    // Debug
    DependencyGroups.composeDebug.forEach { debugImplementation(it) }
}
```

### Library build.gradle.kts
```kotlin
plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

configureAndroid()

android {
    namespace = "com.x3live.core"
    
    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    // Core AndroidX
    DependencyGroups.androidxCore.forEach { implementation(it) }
    
    // Compose (minimal for framework)
    implementation(platform(Dependencies.composeBom))
    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeUiToolingPreview)
    implementation(Dependencies.composeMaterial3)
    
    // Navigation
    implementation(Dependencies.navigationCompose)
    
    // Testing
    DependencyGroups.testing.forEach { testImplementation(it) }
    androidTestImplementation(platform(Dependencies.composeBom))
    androidTestImplementation(Dependencies.composeUiTestJunit4)
    
    // Debug
    DependencyGroups.composeDebug.forEach { debugImplementation(it) }
}
```

## Benefits

1. **Centralized Versions**: All dependency versions in one place
2. **Type Safety**: Kotlin DSL provides IDE completion and error checking
3. **Dependency Groups**: Related dependencies grouped for easy management
4. **Reusable Configuration**: Shared Android configuration function
5. **Consistency**: Same versions across all modules
6. **Maintainability**: Easy to update versions project-wide

## Current Status

The buildSrc setup is ready but blocked by a Java version compatibility issue with the Gradle daemon. Once resolved, this system will provide excellent dependency management for the project.