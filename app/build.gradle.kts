import com.mercandalli.build_src.main.Const

plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.mercandalli.android.apps.compose_ratio"
    compileSdk = Const.compileSdkVersion

    defaultConfig {
        applicationId = "com.mercandalli.android.apps.compose_ratio"
        minSdk = Const.minSdkVersion
        targetSdk = Const.targetSdkVersion
        versionCode = Const.featureComposeRatioVersionCode
        versionName = Const.featureComposeRatioVersionName
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    sourceSets {
        getByName("main") {
            res.setSrcDirs(
                mutableListOf(
                    "src/main/res/application_icon",
                    "src/main/res/theme"
                )
            )
        }
    }

    // https://developer.android.com/studio/write/java8-support
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.11"
    }
}

dependencies {
    // implementation("com.github.Mercandj:compose-aspect-ratio-reference:1.00.02")
    implementation(project(":feature_compose_ratio"))

    // AndroidX
    implementation("androidx.annotation:annotation:1.7.1")
    implementation("androidx.appcompat:appcompat:1.6.1")

    // JetPack Compose
    implementation("androidx.compose.ui:ui:1.6.6")
    debugImplementation("androidx.compose.ui:ui-tooling:1.6.6")
    implementation("androidx.compose.ui:ui-tooling-preview:1.6.6")
    implementation("androidx.compose.foundation:foundation:1.6.6")
    implementation("androidx.activity:activity-compose:1.9.0")
    implementation("androidx.compose.material3:material3:1.2.1")
}
