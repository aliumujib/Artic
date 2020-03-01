
package dependencies

/**
 * Project debug dependencies, makes it easy to include external binaries or
 * other library modules to build.
 */
object DebugDependencies {
    const val LEAKCANARY = "com.squareup.leakcanary:leakcanary-android:${BuildDependenciesVersions.LEAKCANARY}"
}
