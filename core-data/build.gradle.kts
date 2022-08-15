plugins {
    id("sunflowerclone.android.library")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("sunflowerclone.spotless")
}

dependencies {
    implementation(project(":core-common"))
    implementation(project(":core-model"))
    implementation(project(":core-database"))
    implementation(project(":core-network"))

    testImplementation(project(":core-testing"))

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.gson)
    implementation(libs.androidx.paging)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}
