import dependencies.AnnotationProcessorsDependencies
import dependencies.TestDependencies
import extensions.implementation

plugins {
    id("commons.kotlin-library")
}

dependencies {
    implementation(TestDependencies.MOCKITO)
    implementation(TestDependencies.ASSERTJ)
    implementation(TestDependencies.JUNIT)
    implementation(TestDependencies.COROUTINES_TEST)
    annotationProcessor(AnnotationProcessorsDependencies.AUTO_SERVICE)
}
