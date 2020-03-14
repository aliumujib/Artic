import extensions.implementation
import dependencies.Dependencies

plugins {
    id("commons.android-dynamic-feature")
}


dependencies{
    implementation (project(BuildModules.Features.ARTICLES))
    implementation (Dependencies.HTML_TEXT_VIEW)
}