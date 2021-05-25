buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:${Versions.agpPlugin}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}")
    }
}

plugins {
    id("io.gitlab.arturbosch.detekt") version Versions.detekt
}

allprojects {
    repositories {
        maven { url = uri("https://maven.fabric.io/public") }
        maven { url = uri("https://jitpack.io") }
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}