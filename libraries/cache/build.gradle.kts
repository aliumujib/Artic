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
