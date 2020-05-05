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
import dependencies.TestAndroidDependencies
import dependencies.TestDependencies

plugins {
    id("commons.android-library")
}

dependencies {
    implementation(TestAndroidDependencies.LIFE_CYCLE_KTX)

    testImplementation(project(BuildModules.Libraries.TEST_UTILS))
    testImplementation(TestAndroidDependencies.LIFE_CYCLE_KTX)
    testImplementation(TestAndroidDependencies.ROBO_ELECTRIC)
    testImplementation(TestAndroidDependencies.ARCH_TESTING)
    testImplementation(TestAndroidDependencies.ROOM_TESTING)
    testImplementation(TestAndroidDependencies.RUNNER)
    testImplementation(TestAndroidDependencies.RULES)
    testImplementation (TestDependencies.KONVEYOR)
    testImplementation (TestDependencies.JUNIT)
    testImplementation (TestDependencies.MOCKK)
    testImplementation(TestDependencies.TRUTH)
}
