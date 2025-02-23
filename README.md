# Android Corio SDK Dependencies

[![](https://jitpack.io/v/corio-health/sdk-dependencies-android.svg)](https://jitpack.io/#corio-health/sdk-dependencies-android)

This library contains only the dependencies for the Corio SDK. It is used to keep the dependencies in one place and to avoid version conflicts.

Latest versions of the dependencies are defined in the [version catalog](https://github.com/corio-health/sdk-dependencies-android/blob/master/gradle/libs.versions.toml) file.

## Usage

Add the following lines to your settings.gradle:

```gradle
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```

Then, you can add the dependencies for the Headless SDK and Visual SDK.

Add the following lines to your app/build.gradle:

```gradle
android {
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    # For Headless SDK
    implementation 'com.github.corio-health.sdk-dependencies-android:headless:$version'
    
    # For Visual SDK
    implementation 'com.github.corio-health.sdk-dependencies-android:visual:$version'
}
```
