plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin(KotlinPlugins.serialization) version "1.7.10"
    id(KotlinPlugins.parcelize)
}

kotlin {
    android()
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    kotlin.targets.withType(org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget::class.java) {

        // export correct artifact to use all classes of library directly from Swift

        binaries.withType(org.jetbrains.kotlin.gradle.plugin.mpp.Framework::class.java).all {
            export("dev.icerock.moko:mvvm-core:0.13.1")
        }

        binaries.all {
            binaryOptions["memoryModel"] = "experimental"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
/*
                with(Compose) {
                    implementation(composeUI)
                    implementation(composeMaterial)
                    implementation(composeActivity)
                    implementation(util)
                }
*/
                with(Ktor) {
                    implementation(clientCore)
                    implementation(clientJson)
                    implementation(clientLogging)
                    implementation(clientSerialization)
                    implementation(contentNegotiation)
                    implementation(json)
                }
                with(Koin) {
                    implementation(koin)
                }
                with(Kotlinx) {
                    implementation(serializationCore)
                    implementation(datetime)
                }
                with(Moko) {
                    api(mokoMVVMCore)
                }
                with(Coroutines) {
                    implementation(coroutines)
                }
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Ktor.clientAndroid)
                implementation(Koin.koinAndroid)

            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1")
                with(Ktor) {
                    implementation(clientMock)
                    implementation(contentNegotiation)
                }
            }
        }
        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependencies {
                implementation(Ktor.clientIos)
            }
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
        all {
            languageSettings.optIn("-Xopt-in=kotlin.RequiresOptIn")
        }
    }
}

android {
    namespace = "de.olegrom.starwars"
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    compileSdk = 33
    defaultConfig {
        minSdk = 21
        targetSdk = 33
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}
