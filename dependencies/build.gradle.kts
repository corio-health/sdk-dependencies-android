plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("maven-publish")
}

android {
    namespace = "de.corio.sdk.dependencies"
    compileSdk = libs.versions.compileSdk.get().toInt()
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
}

dependencies {
    api(libs.core.ktx)
    api(libs.appcompat)
    api(libs.coroutines.core)
    api(libs.bundles.camera)
}

publishing {
    publications {
        create<MavenPublication>("release") {
            groupId = "de.corio.sdk"
            artifactId = "dependencies"
            version = libs.versions.dependencies.get()

            // Publish the AAR produced by this module
            afterEvaluate {
                from(components["release"])
            }
        }
    }
}
