import dependencies.Dependencies
import dependencies.AnnotationProcessorsDependencies
import extensions.implementation
import extensions.kapt

plugins {
    id("commons.android-library")
}

junitJacoco {
    excludes = listOf("**/extensions/NavigationExtensions*.*")
}

android{

    viewBinding{
        isEnabled = true
    }

}

dependencies {
    implementation(Dependencies.LIFECYCLE_EXTENSIONS)
    implementation(Dependencies.LIFECYCLE_VIEWMODEL)
    implementation(Dependencies.CORE_KTX)

    implementation(Dependencies.CONSTRAIN_LAYOUT)
    implementation(Dependencies.RECYCLE_VIEW)
    implementation(Dependencies.FRAGMENT_KTX)
    implementation(Dependencies.NAVIGATION_FRAGMENT)
    implementation(Dependencies.NAVIGATION_UI)
    implementation(Dependencies.PAGING)
    implementation(Dependencies.COIL)
    implementation(Dependencies.PRO_PROGRESS_VIEWS)

    kapt(AnnotationProcessorsDependencies.DATABINDING)
}
