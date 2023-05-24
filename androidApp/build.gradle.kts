plugins {
    id("com.android.application")
    kotlin("android")
    id("com.google.devtools.ksp") version "1.8.10-1.0.9"
}

android {
    namespace = "de.olegrom.starwars.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "de.olegrom.starwars.android"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        compose = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
        freeCompilerArgs = listOf("-opt-in=kotlin.RequiresOptIn")
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }

    applicationVariants.all {
        kotlin.sourceSets {
            getByName(name) {
                kotlin.srcDir("build/generated/ksp/$name/kotlin")
            }
        }
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":shared"))
    with(Accompanist) {
        implementation(swiperefresh)
    }
    with(ComposeDestination) {
        implementation(composeDestination)
        ksp(composeDestinationPlugin)
    }
    with(Material3) {
        implementation(material3)
        implementation(window)
    }
    with(Accompanist) {
        implementation(coil)
        implementation(webview)
    }
    with(Compose) {
        implementation(util)
        implementation(composeActivity)
        implementation(composeToolingDebug)
        implementation(composeUI)
        implementation(composePaging)
    }

    implementation(Koin.koinAndroid)

    // Testing
    androidTestImplementation("androidx.test:runner:1.5.2")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.4.3")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.4.3")
    testImplementation("androidx.arch.core:core-testing:2.2.0")
    testImplementation("androidx.test:core:1.5.0")
}