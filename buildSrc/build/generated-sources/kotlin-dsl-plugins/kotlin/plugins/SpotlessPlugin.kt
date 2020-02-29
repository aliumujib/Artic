package plugins

/**
 * Precompiled [spotless.gradle.kts][plugins.Spotless_gradle] script plugin.
 *
 * @see plugins.Spotless_gradle
 */
class SpotlessPlugin : org.gradle.api.Plugin<org.gradle.api.Project> {
    override fun apply(target: org.gradle.api.Project) {
        try {
            Class
                .forName("plugins.Spotless_gradle")
                .getDeclaredConstructor(org.gradle.api.Project::class.java, org.gradle.api.Project::class.java)
                .newInstance(target, target)
        } catch (e: java.lang.reflect.InvocationTargetException) {
            throw e.targetException
        }
    }
}
