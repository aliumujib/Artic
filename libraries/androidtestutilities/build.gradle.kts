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
