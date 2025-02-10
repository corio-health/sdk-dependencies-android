plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("maven-publish")
}

android {
    namespace = "corio.sdk.dependencies.visual"
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
    api(libs.fragment.ktx)
    api(libs.material)
    api(libs.viewbinding.delegate)

    api(libs.lifecycle.livedata)
    api(libs.lifecycle.viewmodel)

    api(libs.dots.indicator)
    api(libs.mpandroidchart)
    api(libs.lottie)
}

publishing {
    publications {
        register<MavenPublication>("release") {
            artifactId = "visual"
            version = libs.versions.corioDependencies.get()

            // Publish the AAR produced by this module
            afterEvaluate {
                from(components["release"])
            }
        }
    }
}
