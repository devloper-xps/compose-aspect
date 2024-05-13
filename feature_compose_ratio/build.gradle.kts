import com.mercandalli.build_src.main.Const

plugins {
    id("com.android.library")
    kotlin("android")
    id("maven-publish")
}

android {
    namespace = "com.mercandalli.android.sdk.compose_ratio"
    compileSdk = Const.compileSdkVersion

    defaultConfig {
        minSdk = 21 // Compose require min API 21
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

    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
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

    // Jetpack Compose
    buildFeatures {
        compose = true
    }
    composeOptions {
        // https://developer.android.com/jetpack/androidx/releases/compose-kotlin#pre-release_kotlin_compatibility
        // https://github.com/jimgoog/ComposeAppUsingPrereleaseComposeCompiler#project-configuration
        kotlinCompilerExtensionVersion = "1.5.11"
    }
}

dependencies {

    // JetPack Compose
    // https://developer.android.com/jetpack/androidx/releases/compose-kotlin#pre-release_kotlin_compatibility
    implementation("androidx.compose.ui:ui:1.6.6")
    implementation("androidx.compose.ui:ui-tooling-preview:1.6.6")
    debugImplementation("androidx.compose.ui:ui-tooling:1.6.6")

    // https://developer.android.com/jetpack/androidx/releases/compose-material3
    implementation("androidx.compose.material3:material3:1.2.1")
}

publishing {
    publications {
        create<MavenPublication>("release") {
            groupId = "com.mercandalli.android.sdk"
            artifactId = "compose_ratio"
            version = Const.featureComposeRatioVersionName

            afterEvaluate { from(components["release"]) }
        }
    }
}
