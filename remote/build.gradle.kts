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
    implementation(Dependencies.RETROFIT_CONVERTER)
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.RETROFIT_RX_ADAPTER)
    implementation(Dependencies.OKHTTP3_LOGGING_INTERCEPTOR)
}
