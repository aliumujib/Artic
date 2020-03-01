
package utils

import org.gradle.api.Project
import java.io.File
import java.lang.Exception

private const val FABRIC_PROPERTIES_FILE_NAME = "app/fabric.properties"
private const val FABRIC_API_KEY_PROPERTY_NAME = "fabric.key"
private const val FABRIC_API_SECRET_PROPERTY_NAME = "fabric.secret"

/**
 * Util to create `fabric.properties` file using the declared values on
 * `$projectRoot/local.properties` by the following key names: `fabric.key` and `fabric.secret`.
 *
 * @param project the project reference
 */
fun createFabricProperties(project: Project) {
    val fabricPropertiesFile = project.rootProject.file(FABRIC_PROPERTIES_FILE_NAME)
    try {
        val fabricApiKey = getLocalProperty(FABRIC_API_KEY_PROPERTY_NAME, project)
        val fabricApiSecret = getLocalProperty(FABRIC_API_SECRET_PROPERTY_NAME, project)

        if (!fabricPropertiesFile.exists()) {
            fabricPropertiesFile
                .printWriter()
                .use { output ->
                    output.println("apiKey=$fabricApiKey")
                    output.println("apiSecret=$fabricApiSecret")
                }
        }
    } catch (e: Exception) {
        throw NoSuchFileException(
            file = fabricPropertiesFile,
            reason = "You should define 'fabric.key' and 'fabric.secret' in local.properties. Visit 'https://fabric.io' to obtain them."
        )
    }
}
