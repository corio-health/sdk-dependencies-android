plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("maven-publish")
}

android {
    namespace = "corio.sdk.dependencies.visual.compose"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.valueOf("VERSION_${libs.versions.jvmTarget.get()}")
        targetCompatibility = JavaVersion.valueOf("VERSION_${libs.versions.jvmTarget.get()}")
    }
    kotlinOptions {
        jvmTarget = libs.versions.jvmTarget.get()
    }

    testOptions.unitTests.isReturnDefaultValues = true
}

dependencies {
    api(platform(libs.compose.bom))
    api(libs.fragment.compose)

    testImplementation(libs.junit)
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.github.corio-health.sdk-dependencies-android"
            artifactId = "visual-compose"
            version = libs.versions.corioDependencies.get()

            // Publish the AAR produced by this module
            afterEvaluate {
                from(components["release"])
            }
        }
    }
}
