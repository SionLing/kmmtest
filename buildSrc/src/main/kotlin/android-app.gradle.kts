plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("plugin.serialization")
}

configureAndroid()

android {
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}