/*
 * Copyright 2020 Abdul-Mujeeb Aliu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import dependencies.AnnotationProcessorsDependencies
import dependencies.Dependencies
import dependencies.DebugDependencies
import extensions.*
import extensions.implementation
import org.gradle.kotlin.dsl.allOpen
import org.gradle.kotlin.dsl.android
import org.gradle.kotlin.dsl.debugImplementation
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.implementation
import org.gradle.kotlin.dsl.project

plugins {
    id("commons.android-library")
}

allOpen {
    // allows mocking for classes w/o directly opening them for release builds
    annotation("com.aliumujib.artic.annotations.OpenClass")
}

android {
    buildTypes.forEach {
        try {
            it.buildConfigStringField("MARVEL_API_BASE_URL", "https://gateway.marvel.com/")
//            it.buildConfigStringField("MARVEL_API_KEY_PUBLIC", getLocalProperty("marvel.key.public"))
//            it.buildConfigStringField("MARVEL_API_KEY_PRIVATE", getLocalProperty("marvel.key.private"))

            it.buildConfigBooleanField("MARVEL_DATABASE_EXPORT_SCHEMA", false)
            it.buildConfigStringField("MARVEL_DATABASE_NAME", "characters-db")
            it.buildConfigIntField("MARVEL_DATABASE_VERSION", 1)
        } catch (ignored: Exception) {
            throw InvalidUserDataException("You should define 'marvel.key.public' and " +
                    "'marvel.key.private' in local.properties. Visit 'https://developer.marvel.com' " +
                    "to obtain them.")
        }
    }
}
dependencies {

    implementation(Dependencies.KOTLIN)
    implementation(Dependencies.APPCOMPAT)
    implementation(Dependencies.MATERIAL)
    implementation(Dependencies.CONSTRAIN_LAYOUT)
    implementation(Dependencies.NAVIGATION_FRAGMENT)
    implementation(Dependencies.TIMBER)
    implementation(Dependencies.LOGGING)
    implementation(Dependencies.CRASHLYTICS)
    implementation(Dependencies.PLAY_CORE)
    implementation(Dependencies.DAGGER)
    implementation(Dependencies.DAGGER_ANDROID)
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

    implementation(project(BuildModules.Libraries.DATA))
    implementation(project(BuildModules.Libraries.DOMAIN))
    implementation(project(BuildModules.Libraries.CACHE))
    implementation(project(BuildModules.Libraries.REMOTE))

    debugImplementation(DebugDependencies.LEAKCANARY)

    kapt (AnnotationProcessorsDependencies.DAGGER_ANDROID)
    kapt (AnnotationProcessorsDependencies.DAGGER)

    addTestsDependencies()
}
