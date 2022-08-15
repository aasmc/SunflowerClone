plugins {
    id("sunflowerclone.android.library")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("sunflowerclone.spotless")
}

android {
    defaultConfig {
        buildConfigField("String", "UNSPLASH_ACCESS_KEY",  "\"" + getUnsplashAccess() + "\"")
    }
}

dependencies {
    implementation(project(":core-common"))
    implementation(project(":core-model"))

    testImplementation(project(":core-testing"))

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.core)
    implementation(libs.gson)
    implementation(libs.retrofit.gson.converter)

    implementation(libs.hilt.android)
    implementation(libs.androidx.paging)
    kapt(libs.hilt.compiler)
}

fun getUnsplashAccess(): String {
    return project.findProperty("UNSPLASH_ACCESS_KEY") as String
}