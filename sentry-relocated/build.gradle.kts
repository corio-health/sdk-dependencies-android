plugins {
    `java-library`
    alias(libs.plugins.shadow)
    id("maven-publish")
}

dependencies {
    implementation(libs.sentry)
}

// Shadow ignores path changes in eachFile/rename, so the notices are moved by a plain Copy task.
val sentryNotices by tasks.registering(Copy::class) {
    from({ zipTree(configurations.runtimeClasspath.get().single { it.name.startsWith("sentry-") }) }) {
        include("META-INF/SENTRY_THIRD_PARTY_NOTICES.md")
        eachFile { relativePath = RelativePath(true, "META-INF", "corio-sentry-relocated", name) }
    }
    includeEmptyDirs = false
    into(layout.buildDirectory.dir("sentry-notices"))
}

tasks.shadowJar {
    archiveClassifier.set("")
    relocate("io.sentry", "de.corio.shaded.sentry")
    mergeServiceFiles()
    // Same paths as in the host's own io.sentry jars, which fails the host's mergeJavaResource.
    exclude("META-INF/native-image/**")
    exclude("META-INF/SENTRY_THIRD_PARTY_NOTICES.md")
    from(sentryNotices)
    isPreserveFileTimestamps = false
    isReproducibleFileOrder = true
}

// Only the shadow jar is published; keep the empty plain jar from clashing with its file name.
tasks.jar {
    archiveClassifier.set("plain")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.assemble {
    dependsOn(tasks.shadowJar)
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.github.corio-health.sdk-dependencies-android"
            artifactId = "sentry-relocated"
            version = libs.versions.corioDependencies.get()

            from(components["shadow"])
        }
    }
}
