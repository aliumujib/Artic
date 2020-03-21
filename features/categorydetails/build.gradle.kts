import extensions.implementation

plugins {
    id("commons.android-dynamic-feature")
}


dependencies{
    implementation(project(BuildModules.Features.ARTICLES))
}