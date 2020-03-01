import dependencies.Dependencies
import extensions.implementation

plugins {
    id("commons.android-library")
}

dependencies {
    implementation(project(BuildModules.Libraries.DATA))

    implementation(Dependencies.COROUTINES)
    implementation(Dependencies.JODA_TIME)
    implementation(Dependencies.JAVAX_ANNOTATION)
    implementation(Dependencies.JAVAX_INJECT)
    implementation(Dependencies.RETROFIT_CONVERTER)
    implementation(Dependencies.RETROFIT)
    //implementation(Dependencies.RETROFIT_RX_ADAPTER)
    implementation(Dependencies.OKHTTP3_LOGGING_INTERCEPTOR)
}
