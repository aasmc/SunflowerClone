import ru.aasmc.sunflowerclone.FlavorDimension
import ru.aasmc.sunflowerclone.Flavor

plugins {
    id("sunflowerclone.android.application")
    id("sunflowerclone.android.application.compose")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
//    id("sunflowerclone.spotless")
    id("androidx.navigation.safeargs")
}

android {
    defaultConfig {
        applicationId = "ru.aasmc.sunflowerclone"
        versionCode = 1
        versionName = "0.0.1"

        // Custom test runner to set up Hilt dependency graph
        testInstrumentationRunner = "ru.aasmc.sunflowerclone.core.testing.MainTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        buildConfigField("String", "UNSPLASH_ACCESS_KEY",  "\"" + getUnsplashAccess() + "\"")
    }

    buildTypes {
        val debug by getting {
            applicationIdSuffix = ".debug"
        }
        val release by getting {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

            // To publish on the Play store a private signing key is required, but to allow anyone
            // who clones the code to sign and run the release variant, use the debug signing key.
            signingConfig = signingConfigs.getByName("debug")
        }
        val benchmark by creating {
            // Enable all the optimizations from release build through initWith(release).
            initWith(release)
            matchingFallbacks.add("release")
            // Debug key signing is available to everyone.
            signingConfig = signingConfigs.getByName("debug")
            // Only use benchmark proguard rules
            proguardFiles("benchmark-rules.pro")
            //  FIXME enabling minification breaks access to demo backend.
            isMinifyEnabled = false
            applicationIdSuffix = ".benchmark"
        }
    }

    // @see Flavor for more details on the app product flavors.
    flavorDimensions += FlavorDimension.contentType.name
    productFlavors {
        Flavor.values().forEach {
            create(it.name) {
                dimension = it.dimension.name
                if (it.applicationIdSuffix != null) {
                    applicationIdSuffix = it.applicationIdSuffix
                }
            }
        }
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    implementation(project(":core-designsystem"))
    implementation(project(":core-common"))
    implementation(project(":core-data"))
    implementation(project(":core-database"))
    implementation(project(":core-model"))
    implementation(project(":core-network"))
    implementation(project(":core-testing"))

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.fragment)
    implementation(libs.androidx.lifecycle.livedata)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)
    implementation(libs.androidx.paging)
    implementation(libs.androidx.viewpager)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.androidx.tracing.ktx)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.constraintlayout)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.foundation.layout)
    implementation(libs.androidx.compose.material.version2)
    implementation(libs.androidx.compose.ui.viewbinding)
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.runtime.livedata)
    implementation(libs.androidx.lifecycle.viewModelCompose)
    implementation(libs.material.theme.adapter)
    implementation(libs.coil.kt.compose)
    implementation(libs.androidx.work.ktx)
    implementation(libs.material3)
    implementation(libs.androidx.profileinstaller)

    implementation(libs.coil.kt)

    implementation(libs.hilt.android)
    implementation("androidx.hilt:hilt-work:1.0.0")
    kapt(libs.hilt.compiler)
    kaptAndroidTest(libs.hilt.compiler)

    androidTestImplementation(libs.androidx.navigation.testing)
    androidTestImplementation(libs.androidx.test.core)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(libs.androidx.test.espresso.contrib)
    androidTestImplementation(libs.androidx.test.espresso.intents)
    androidTestImplementation(libs.androidx.test.uiautomator)
    androidTestImplementation(libs.truth.assertions)
    androidTestImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.hilt.android.testing)
    androidTestImplementation(libs.androidx.compose.ui.test)
    debugImplementation(libs.androidx.compose.ui.testManifest)

    // androidx.test is forcing JUnit, 4.12. This forces it to use 4.13
    configurations.configureEach {
        resolutionStrategy {
            force(libs.junit4)
            // Temporary workaround for https://issuetracker.google.com/174733673
            force("org.objenesis:objenesis:2.6")
        }
    }
    testImplementation(libs.junit4)
}

fun getUnsplashAccess(): String {
    return project.findProperty("UNSPLASH_ACCESS_KEY") as String
}
