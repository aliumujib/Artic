package commons

/**
 * Precompiled [android-library.gradle.kts][commons.Android_library_gradle] script plugin.
 *
 * @see commons.Android_library_gradle
 */
class AndroidLibraryPlugin : org.gradle.api.Plugin<org.gradle.api.Project> {
    override fun apply(target: org.gradle.api.Project) {
        try {
            Class
                .forName("commons.Android_library_gradle")
                .getDeclaredConstructor(org.gradle.api.Project::class.java, org.gradle.api.Project::class.java)
                .newInstance(target, target)
        } catch (e: java.lang.reflect.InvocationTargetException) {
            throw e.targetException
        }
    }
}
