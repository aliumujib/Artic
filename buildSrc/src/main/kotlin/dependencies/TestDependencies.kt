
package dependencies

/**
 * Project test dependencies, makes it easy to include external binaries or
 * other library modules to build.
 */
object TestDependencies {
    const val JUNIT = "junit:junit:${BuildDependenciesVersions.JUNIT}"
    const val MOCKITO = "com.nhaarman.mockitokotlin2:mockito-kotlin:${BuildDependenciesVersions.MOCKITO}"
    const val MOCKK = "io.mockk:mockk:${BuildDependenciesVersions.MOCKK}"
    const val ASSERTJ = "org.assertj:assertj-core:${BuildDependenciesVersions.ASSERTJ}"
    const val ROBOELECTRIC = "org.robolectric:robolectric:${BuildDependenciesVersions.ROBOELECTRIC}"
    const val ROOM = "androidx.room:room-testing:${BuildDependenciesVersions.ROOM}"
    const val EXT = "androidx.test.ext:junit:${BuildDependenciesVersions.EXT}"
    const val CORE = "androidx.test:core:${BuildDependenciesVersions.TEST}"
    const val RUNNER = "androidx.test:runner:${BuildDependenciesVersions.TEST}"
    const val RULES = "androidx.test:rules:${BuildDependenciesVersions.TEST}"
    const val COROUTINES_TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${BuildDependenciesVersions.COROUTINES}"
    const val ARCH_CORE = "androidx.arch.core:core-testing:${BuildDependenciesVersions.ARCH_CORE}"
    const val FRAGMENT_TEST = "androidx.fragment:fragment-testing:${BuildDependenciesVersions.FRAGMENT_TEST}"
    const val MOCK_WEB_SERVER = "com.squareup.okhttp3:mockwebserver:${BuildDependenciesVersions.MOCK_WEB_SERVER}"
    const val KONVEYOR = "com.github.vacxe:konveyor:${BuildDependenciesVersions.KONVEYOR_VERSION}"
    const val TRUTH = "com.google.truth:truth:${BuildDependenciesVersions.TRUTH}"
}
