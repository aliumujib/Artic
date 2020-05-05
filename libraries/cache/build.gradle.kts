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
import dependencies.Dependencies
import dependencies.AnnotationProcessorsDependencies
import extensions.implementation
import extensions.kapt
import dependencies.TestAndroidDependencies
import dependencies.TestDependencies

plugins {
    id("commons.android-library")
}

dependencies {
    implementation(project(BuildModules.Libraries.DATA))

    implementation(Dependencies.COROUTINES)
    implementation(Dependencies.JODA_TIME)
    implementation(Dependencies.JAVAX_ANNOTATION)
    implementation(Dependencies.JAVAX_INJECT)
    implementation(Dependencies.ROOM)
    implementation(Dependencies.ROOM_KTX)
    implementation(Dependencies.RETROFIT_CONVERTER)

    kapt(AnnotationProcessorsDependencies.ROOM)

    testImplementation(TestAndroidDependencies.ROBO_ELECTRIC)
    testImplementation(TestAndroidDependencies.ARCH_TESTING)
    testImplementation(TestAndroidDependencies.ROOM_TESTING)
    testImplementation(TestAndroidDependencies.RUNNER)
    testImplementation(TestAndroidDependencies.RULES)
    testImplementation (TestDependencies.KONVEYOR)
    testImplementation (TestDependencies.JUNIT)
    testImplementation (TestDependencies.MOCKK)
    testImplementation(TestDependencies.TRUTH)
    testImplementation(project(BuildModules.Libraries.TEST_UTILS))

}
