plugins {
    // https://proandroiddev.com/stop-using-gradle-buildsrc-use-composite-builds-instead-3c38ac7a2ab3
    id("com.mercandalli.build_src.const")
}

buildscript {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }

    dependencies {
        classpath("com.android.tools.build:gradle:8.3.2")
        classpath(kotlin("gradle-plugin", version = "1.9.23"))
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")

        // JetPack Compose - Mobile
        // https://developer.android.com/jetpack/androidx/releases/compose-kotlin#pre-release_kotlin_compatibility
        // https://androidx.dev/storage/compose-compiler/repository
        // https://github.com/jimgoog/ComposeAppUsingPrereleaseComposeCompiler#project-configuration
        // https://github.com/jimgoog/ComposeAppUsingPrereleaseComposeCompiler/blob/main/settings.gradle#L14
        maven(url = "https://androidx.dev/storage/compose-compiler/repository/")
    }
}

subprojects {
    // KtLint - Static code analysis
    // https://github.com/pinterest/ktlint/releases
    val ktlint by configurations.creating

    dependencies {
        // KtLint - Static code analysis
        // https://github.com/pinterest/ktlint/releases
        ktlint("com.pinterest.ktlint:ktlint-cli:1.2.1")
    }

    // KtLint - Static code analysis
    // https://github.com/pinterest/ktlint/releases
    tasks.register<JavaExec>("ktlint") {
        group = "verification"
        description = "Check Kotlin code style."
        classpath = ktlint
        mainClass.set("com.pinterest.ktlint.Main")
        args("src/**/*.kt")
    }

    // KtLint - Static code format
    // https://github.com/pinterest/ktlint/releases
    tasks.register<JavaExec>("ktformat") {
        group = "verification"
        description = "Check Kotlin code style."
        classpath = ktlint
        mainClass.set("com.pinterest.ktlint.Main")
        args("-F", "src/**/*.kt")
    }
}
