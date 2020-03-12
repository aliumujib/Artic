import dependencies.TestDependencies
import dependencies.AnnotationProcessorsDependencies
import extensions.implementation
import extensions.testImplementation

plugins {
    id("commons.android-library")
}

dependencies {
    implementation(TestDependencies.MOCKITO)
    implementation(TestDependencies.ASSERTJ)
    implementation(TestDependencies.ROBOELECTRIC)
    implementation(TestDependencies.ROOM)
    implementation(TestDependencies.CORE)
    implementation(TestDependencies.ARCH_CORE)
    implementation(TestDependencies.RULES)
    implementation(TestDependencies.RUNNER)
    implementation(TestDependencies.FRAGMENT_TEST)
    implementation(TestDependencies.EXT)
    implementation(TestDependencies.MOCK_WEB_SERVER)

    annotationProcessor(AnnotationProcessorsDependencies.AUTO_SERVICE)

    implementation(TestDependencies.COROUTINES_TEST)
}
