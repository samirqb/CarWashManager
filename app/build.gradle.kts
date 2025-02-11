plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

    alias(libs.plugins.compose.compiler)
    kotlin("plugin.serialization") version "2.1.0"
}

android {
    namespace = "samirqb.carwashmanager.app"
    compileSdk = 35

    defaultConfig {
        applicationId = "samirqb.carwashmanager.app"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.androidx.compiler)
    implementation(libs.lifecycle.viewmodel.compose)

    // N A V E G A C I O N
    implementation(libs.androidx.navigation.compose)
    // JSON serialization
    implementation(libs.kotlinx.serialization.json)


    /** implementacion de libreria uicomponents */
    /** inicio */


    implementation(project(":caja"))
    implementation(project(":helpers"))
    implementation(project(":personas"))
    implementation(project(":vehiculos"))
    implementation(project(":ofertas"))
    implementation(project(":ventas"))
    implementation(project(":transacciones"))
    implementation(project(":rrhh"))
    implementation(project(":pagos"))
    //releaseImplementation(project(":uicomponentes"))
    //debugImplementation(project(":uicomponentes"))
    //androidTestImplementation(project(":uicomponentes"))
    /** fin */

}