import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

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
    
    // Configure Kotlin compiler options
    tasks.withType(KotlinCompile::class.java).configureEach {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}