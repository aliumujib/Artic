
package extensions

import com.android.build.gradle.internal.dsl.BuildType

/**
 * Extension to adds a new string field to the generated BuildConfig class.
 *
 * @param name the name of the field
 * @param value the string value of the field
 */
fun BuildType.buildConfigStringField(name: String, value: String) {
    this.buildConfigField("String", name, "\"$value\"")
}

/**
 * Extension to adds a new integer field to the generated BuildConfig class.
 *
 * @param name the name of the field
 * @param value the int value of the field
 */
fun BuildType.buildConfigIntField(name: String, value: Int) {
    this.buildConfigField("int", name, value.toString())
}

/**
 * Extension to adds a new boolean field to the generated BuildConfig class.
 *
 * @param name the name of the field
 * @param value the boolean value of the field
 */
fun BuildType.buildConfigBooleanField(name: String, value: Boolean) {
    this.buildConfigField("boolean", name, value.toString())
}
