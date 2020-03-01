
/**
 * Configuration of all gradle build plugins
 */
object BuildPlugins {
    const val ANDROID_APPLICATION = "com.android.application"
    const val ANDROID_DYNAMIC_FEATURE = "com.android.dynamic-feature"
    const val ANDROID_LIBRARY = "com.android.library"
    const val KOTLIN_LIBRARY = "com.android.library"

    const val KOTLIN_ANDROID = "kotlin-android"
    const val KOTLIN_ANDROID_EXTENSIONS = "kotlin-android-extensions"
    const val KOTLIN_KAPT = "kotlin-kapt"
    const val KOTLIN_ALLOPEN = "kotlin-allopen"

    const val NAVIGATION_SAFE_ARGS = "androidx.navigation.safeargs.kotlin"
    const val JACOCO = "com.vanniktech.android.junit.jacoco"
    const val GRAPH_GENERATOR = "com.vanniktech.dependency.graph.generator"
    const val FABRIC = "io.fabric"

    const val DETEKT = "plugins.detekt"
    const val DOKKA = "plugins.dokka"
    const val GIT_HOOKS = "plugins.git-hooks"
    const val KTLINT = "plugins.ktlint"
    const val SPOTLESS = "plugins.spotless"
    const val UPDATE_DEPENDENCIES = "plugins.update-dependencies"
}
