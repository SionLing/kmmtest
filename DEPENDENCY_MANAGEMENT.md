# Dependency Management System

This project uses Gradle Version Catalogs to manage dependencies across multiple modules. The system provides centralized dependency management while allowing flexibility for standalone library usage.

## Architecture

### Root Project (`/gradle/libs.versions.toml`)
Contains the main dependency definitions that are shared across all modules in the project:

- **Versions**: Centralized version definitions
- **Libraries**: Library declarations with version references
- **Bundles**: Grouped dependencies for common use cases
- **Plugins**: Plugin declarations with versions

### Library Project (`/x3live-framework/gradle/libs.versions.toml`)
Contains minimal dependency definitions for standalone usage:

- **Core Dependencies**: Only the essential dependencies needed by the framework
- **Fallback Versions**: Used when the library is built independently
- **Simplified Bundles**: Focused on library-specific needs

## How It Works

### When Used in Root Project
1. The root project's version catalog takes precedence
2. All modules (app, x3live-framework) use the same dependency versions
3. Ensures consistency across the entire project
4. App-specific dependencies (like serialization, coil) are available

### When Library Used Standalone
1. If no root project version catalog exists, the library uses its own
2. Only includes dependencies needed by the framework
3. Allows the library to be used in projects with different dependency versions
4. Maintains independence and reusability

## File Structure

```
autodesign/
├── gradle/
│   └── libs.versions.toml          # Root project dependencies
├── build.gradle.kts                # Root build configuration
├── app/
│   └── build.gradle.kts            # App module using root catalog
└── x3live-framework/
    ├── gradle/
    │   └── libs.versions.toml      # Framework fallback dependencies
    ├── settings.gradle             # Standalone project settings
    └── build.gradle.kts            # Framework build using catalog
```

## Usage Examples

### In Root Project Context
```kotlin
// app/build.gradle.kts
dependencies {
    implementation(libs.bundles.androidx.core)  // From root catalog
    implementation(libs.kotlinx.serialization.json)  // App-specific
    implementation(project(":x3live-framework"))
}
```

### In Standalone Library Context
```kotlin
// x3live-framework/build.gradle.kts (when used alone)
dependencies {
    implementation(libs.bundles.androidx.core)  // From library catalog
    implementation(libs.navigation.compose)     // Core framework need
}
```

## Benefits

1. **Consistency**: When used together, all modules share the same dependency versions
2. **Flexibility**: Library can be used independently with its own versions
3. **Maintainability**: Single place to update versions in root project
4. **Compatibility**: Prevents version conflicts between modules
5. **Reusability**: Framework can be used in other projects seamlessly

## Version Catalog Format

```toml
[versions]
kotlin = "1.8.10"
compose = "2023.06.01"

[libraries]
androidx-core = { group = "androidx.core", name = "core-ktx", version.ref = "kotlin" }

[bundles]
compose = ["compose-ui", "compose-material3"]

[plugins]
android-library = { id = "com.android.library", version = "7.4.2" }
```

## Best Practices

1. **Root Catalog**: Include all dependencies used by any module
2. **Library Catalog**: Keep minimal, only framework essentials
3. **Version Consistency**: Use the same version references across catalogs
4. **Bundle Usage**: Group related dependencies for cleaner build files
5. **Documentation**: Update this file when adding new dependencies

This system ensures both modules can work together harmoniously while maintaining the library's independence for reuse in other projects.