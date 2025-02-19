# Android Corio SDK Dependencies
[![](https://jitpack.io/v/corio-health/sdk-dependencies-android.svg)](https://jitpack.io/#corio-health/sdk-dependencies-android)

This library contains only the dependencies for the Corio SDK. It is used to keep the dependencies in one place and to avoid version conflicts.

Latest versions of the dependencies are defined in the [version catalog](https://github.com/corio-health/sdk-dependencies-android/blob/master/gradle/libs.versions.toml) file.

## Usage

Add the following lines to your root build.gradle:
    
```gradle
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

Add the following lines to your project/build.gradle:

```gradle
# For Headless SDK
implementation 'com.github.corio-health.sdk-dependencies-android:headless:$version'

# For Visual SDK
implementation 'com.github.corio-health.sdk-dependencies-android:visual:$version'
```
