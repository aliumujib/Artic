package plugins

/**
 * Precompiled [dokka.gradle.kts][plugins.Dokka_gradle] script plugin.
 *
 * @see plugins.Dokka_gradle
 */
class DokkaPlugin : org.gradle.api.Plugin<org.gradle.api.Project> {
    override fun apply(target: org.gradle.api.Project) {
        try {
            Class
                .forName("plugins.Dokka_gradle")
                .getDeclaredConstructor(org.gradle.api.Project::class.java, org.gradle.api.Project::class.java)
                .newInstance(target, target)
        } catch (e: java.lang.reflect.InvocationTargetException) {
            throw e.targetException
        }
    }
}
