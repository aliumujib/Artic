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
import dependencies.TestDependencies
import extensions.implementation

plugins {
    id("commons.kotlin-library")
}

dependencies {

    implementation(project(BuildModules.Libraries.DOMAIN))

    implementation(Dependencies.COROUTINES)
    implementation(Dependencies.JODA_TIME)
    implementation(Dependencies.JAVAX_INJECT)
    implementation(Dependencies.JAVAX_ANNOTATION)

    testImplementation (TestDependencies.KONVEYOR)
    testImplementation (TestDependencies.JUNIT)
    testImplementation (TestDependencies.MOCKK)
    testImplementation(TestDependencies.TRUTH)
    testImplementation(project(BuildModules.Libraries.TEST_UTILS))
}
