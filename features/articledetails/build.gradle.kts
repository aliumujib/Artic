import extensions.implementation
import dependencies.Dependencies

plugins {
    id("commons.android-dynamic-feature")
}


dependencies{
    implementation (project(BuildModules.Features.ARTICLES))
    implementation (Dependencies.HTML_TEXT_VIEW)

    testImplementation (project(BuildModules.Features.ARTICLES))
    androidTestImplementation (project(BuildModules.Features.ARTICLES))

}