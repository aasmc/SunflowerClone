plugins {
    id("sunflowerclone.android.library")
    kotlin("kapt")
    id("sunflowerclone.spotless")
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    testImplementation(project(":core-testing"))
}