buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(libs.android.gradlePlugin)
        classpath(libs.kotlin.gradlePlugin)
        classpath(libs.kotlin.serializationPlugin)
        classpath(libs.hilt.gradlePlugin)
        val nav_version = "2.5.1"
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")    }
}// Top-level build file where you can add configuration options common to all sub-projects/modules.

