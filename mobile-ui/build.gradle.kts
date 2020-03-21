import dependencies.AnnotationProcessorsDependencies
import dependencies.DebugDependencies
import dependencies.Dependencies
import extensions.addTestsDependencies
import extensions.debugImplementation
import extensions.implementation
import extensions.kapt

plugins {
    id(BuildPlugins.ANDROID_APPLICATION)
    id(BuildPlugins.KOTLIN_ANDROID)
    id(BuildPlugins.KOTLIN_ANDROID_EXTENSIONS)
    id(BuildPlugins.KOTLIN_KAPT)
    id(BuildPlugins.KOTLIN_ALLOPEN)
    id(BuildPlugins.NAVIGATION_SAFE_ARGS)
    id(BuildPlugins.JACOCO)
    id(BuildPlugins.GRAPH_GENERATOR)
    //id(BuildPlugins.FABRIC)
}


android {
    compileSdkVersion(BuildAndroidConfig.COMPILE_SDK_VERSION)
    defaultConfig {
        applicationId = BuildAndroidConfig.APPLICATION_ID
        minSdkVersion(BuildAndroidConfig.MIN_SDK_VERSION)
        targetSdkVersion(BuildAndroidConfig.TARGET_SDK_VERSION)
        buildToolsVersion(BuildAndroidConfig.BUILD_TOOLS_VERSION)

        versionCode = BuildAndroidConfig.VERSION_CODE
        versionName = BuildAndroidConfig.VERSION_NAME

        vectorDrawables.useSupportLibrary = BuildAndroidConfig.SUPPORT_LIBRARY_VECTOR_DRAWABLES
        testInstrumentationRunner = BuildAndroidConfig.TEST_INSTRUMENTATION_RUNNER
        testInstrumentationRunnerArguments =
            BuildAndroidConfig.TEST_INSTRUMENTATION_RUNNER_ARGUMENTS
    }


    buildTypes {

        getByName(BuildType.DEBUG) {
            applicationIdSuffix = BuildTypeDebug.applicationIdSuffix
            versionNameSuffix = BuildTypeDebug.versionNameSuffix
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
            isTestCoverageEnabled = BuildTypeDebug.isTestCoverageEnabled
        }
    }

    dynamicFeatures = mutableSetOf(
        BuildModules.Features.ARTICLES,
        BuildModules.Features.ARTICLE_DETAILS,
        BuildModules.Features.ABOUT,
        BuildModules.Features.BOOKMARKS,
        BuildModules.Features.CATEGORIES,
        BuildModules.Features.SEARCH,
        BuildModules.Features.SETTINGS,
        BuildModules.Features.AUTHORIZATION,
        BuildModules.Features.CATEGORY_DETAILS
    )

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    viewBinding {
        isEnabled = true
    }

    androidExtensions {
        isExperimental = true
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}


dependencies {

    api("androidx.legacy:legacy-support-v4:1.0.0")
    api("androidx.legacy:legacy-support-v4:1.0.0")
    api("androidx.core:core-ktx:1.2.0")
    implementation(project(BuildModules.CORE))
    implementation(project(BuildModules.Commons.VIEWS))

    implementation(Dependencies.KOTLIN)
    implementation(Dependencies.APPCOMPAT)
    implementation(Dependencies.MATERIAL)
    implementation(Dependencies.CONSTRAIN_LAYOUT)
    implementation(Dependencies.NAVIGATION_FRAGMENT)
    implementation(Dependencies.NAVIGATION_DFM)
    implementation(Dependencies.TIMBER)
    implementation(Dependencies.LOGGING)
    implementation(Dependencies.CRASHLYTICS)
    implementation(Dependencies.PLAY_CORE)
    implementation(Dependencies.DAGGER)
    implementation(Dependencies.CORE_KTX)
    implementation(Dependencies.RX_JAVA_2)
    implementation(Dependencies.RX_ANDROID)
    implementation(Dependencies.LEAK_CANARY)
    implementation(Dependencies.CONSTRAIN_LAYOUT)
    implementation(Dependencies.PICASSO)
    implementation(Dependencies.MULTIDEX)
    implementation(Dependencies.MATERIAL_DESIGN_SPECS)
    implementation(Dependencies.JODA_TIME)
    implementation(Dependencies.LIFECYCLE_EXTENSIONS)
    implementation(Dependencies.PICASSO_TRANSFORMATIONS)
    implementation(Dependencies.CIRCLE_IMAGE_VIEW)
    implementation(Dependencies.STETHO)
    implementation(Dependencies.RXBINDING_PLATFORM)
    implementation(Dependencies.TIMBER)
    implementation(Dependencies.JAVAX_ANNOTATION)
    implementation(Dependencies.ROOM)
    implementation(Dependencies.RX_RELAYS)
    implementation(Dependencies.NAVIGATION_UI)
    testImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.test.ext:junit:1.1.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")
    androidTestImplementation("androidx.annotation:annotation:1.1.0")

    debugImplementation(DebugDependencies.LEAKCANARY)

    kapt(AnnotationProcessorsDependencies.DAGGER)

    addTestsDependencies()
}
