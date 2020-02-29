import dependencies.Dependencies
import dependencies.AnnotationProcessorsDependencies
import extensions.implementation
import extensions.kapt

plugins {
    id("commons.android-library")
}

dependencies {
    implementation(project(BuildModules.Libraries.DATA))

    implementation(Dependencies.RX_JAVA_2)
    implementation(Dependencies.JODA_TIME)
    implementation(Dependencies.JAVAX_ANNOTATION)
    implementation(Dependencies.JAVAX_INJECT)
    implementation(Dependencies.ROOM)
    implementation(Dependencies.ROOM_RX_JAVA_2)
    implementation(Dependencies.RETROFIT_CONVERTER)


    kapt(AnnotationProcessorsDependencies.ROOM)
}
