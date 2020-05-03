package dependencies

/**
 * Project test android dependencies, makes it easy to include external binaries or
 * other library modules to build.
 */
object TestAndroidDependencies {
    const val ROBO_ELECTRIC = "org.robolectric:robolectric:${BuildDependenciesVersions.ROBOELECTRIC}"
    const val ARCH_TESTING = "android.arch.core:core-testing:${BuildDependenciesVersions.ARCH_CORE}"
    const val LIFE_CYCLE_KTX = "androidx.lifecycle:lifecycle-livedata-ktx:${BuildDependenciesVersions.LIFE_CYCLE_KTX}"
    const val ROOM_TESTING = "android.arch.persistence.room:testing:${BuildDependenciesVersions.ROOM}"
    const val LEAKCANARY = "com.squareup.leakcanary:leakcanary-android-instrumentation:${BuildDependenciesVersions.LEAKCANARY}"
    const val MOCKITO = "com.nhaarman.mockitokotlin2:mockito-kotlin:${BuildDependenciesVersions.MOCKITO}"
    const val ESPRESSO = "androidx.test.espresso:espresso-core:${BuildDependenciesVersions.ESPRESSO}"
    const val RUNNER = "androidx.test:runner:${BuildDependenciesVersions.TEST}"
    const val RULES = "androidx.test:rules:${BuildDependenciesVersions.TEST}"
    const val JUNIT = "androidx.test.ext:junit:${BuildDependenciesVersions.EXT}"
    const val FRAGMENT_TEST = "androidx.fragment:fragment-testing:${BuildDependenciesVersions.FRAGMENT_TEST}"
    const val PLAY_CORE = "com.google.android.play:core:${BuildDependenciesVersions.PLAY_CORE}"
}
