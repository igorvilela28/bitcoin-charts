buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:${Versions.agpPlugin}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")
    }
}

allprojects {
    repositories {
        maven { url = uri("https://maven.fabric.io/public") }
        maven { url = uri("https://jitpack.io") }
        google()
        jcenter()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}