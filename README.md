# Corio SDK Dependencies
[![](https://jitpack.io/v/KenkouGmbH/corio-sdk-dependencies.svg)](https://jitpack.io/#KenkouGmbH/corio-sdk-dependencies)

This library contains only the dependencies for the Corio SDK. It is used to keep the dependencies in one place and to avoid version conflicts.

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
implementation 'com.github.KenkouGmbH.corio-sdk-dependencies:headless:$version'

# For Visual SDK
implementation 'com.github.KenkouGmbH.corio-sdk-dependencies:visual:$version'
```
