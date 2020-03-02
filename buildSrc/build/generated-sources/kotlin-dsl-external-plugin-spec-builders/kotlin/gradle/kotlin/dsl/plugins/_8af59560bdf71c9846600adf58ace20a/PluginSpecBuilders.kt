/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:Suppress(
    "unused",
    "nothing_to_inline",
    "useless_cast",
    "unchecked_cast",
    "extension_shadowed_by_member",
    "redundant_projection",
    "RemoveRedundantBackticks",
    "ObjectPropertyName"
)

/* ktlint-disable */

package gradle.kotlin.dsl.plugins._8af59560bdf71c9846600adf58ace20a

import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec


/**
 * The `android` plugin implemented by [com.android.build.gradle.AppPlugin].
 */
internal
val `PluginDependenciesSpec`.`android`: PluginDependencySpec
    get() = this.id("android")


/**
 * The `android-library` plugin implemented by [com.android.build.gradle.LibraryPlugin].
 */
internal
val `PluginDependenciesSpec`.`android-library`: PluginDependencySpec
    get() = this.id("android-library")


/**
 * The `android-reporting` plugin implemented by [com.android.build.gradle.ReportingPlugin].
 */
internal
val `PluginDependenciesSpec`.`android-reporting`: PluginDependencySpec
    get() = this.id("android-reporting")


/**
 * The `androidx` plugin group.
 */
internal
class `AndroidxPluginGroup`(internal val plugins: PluginDependenciesSpec)


/**
 * Plugin ids starting with `androidx`.
 */
internal
val `PluginDependenciesSpec`.`androidx`: `AndroidxPluginGroup`
    get() = `AndroidxPluginGroup`(this)


/**
 * The `androidx.navigation` plugin group.
 */
internal
class `AndroidxNavigationPluginGroup`(internal val plugins: PluginDependenciesSpec)


/**
 * Plugin ids starting with `androidx.navigation`.
 */
internal
val `AndroidxPluginGroup`.`navigation`: `AndroidxNavigationPluginGroup`
    get() = `AndroidxNavigationPluginGroup`(plugins)


/**
 * The `androidx.navigation.safeargs` plugin implemented by [androidx.navigation.safeargs.gradle.SafeArgsJavaPlugin].
 */
internal
val `AndroidxNavigationPluginGroup`.`safeargs`: PluginDependencySpec
    get() = plugins.id("androidx.navigation.safeargs")


/**
 * The `com` plugin group.
 */
internal
class `ComPluginGroup`(internal val plugins: PluginDependenciesSpec)


/**
 * Plugin ids starting with `com`.
 */
internal
val `PluginDependenciesSpec`.`com`: `ComPluginGroup`
    get() = `ComPluginGroup`(this)


/**
 * The `com.android` plugin group.
 */
internal
class `ComAndroidPluginGroup`(internal val plugins: PluginDependenciesSpec)


/**
 * Plugin ids starting with `com.android`.
 */
internal
val `ComPluginGroup`.`android`: `ComAndroidPluginGroup`
    get() = `ComAndroidPluginGroup`(plugins)


/**
 * The `com.android.application` plugin implemented by [com.android.build.gradle.AppPlugin].
 */
internal
val `ComAndroidPluginGroup`.`application`: PluginDependencySpec
    get() = plugins.id("com.android.application")


/**
 * The `com.android.base` plugin implemented by [com.android.build.gradle.api.AndroidBasePlugin].
 */
internal
val `ComAndroidPluginGroup`.`base`: PluginDependencySpec
    get() = plugins.id("com.android.base")


/**
 * The `com.android.debug` plugin group.
 */
internal
class `ComAndroidDebugPluginGroup`(internal val plugins: PluginDependenciesSpec)


/**
 * Plugin ids starting with `com.android.debug`.
 */
internal
val `ComAndroidPluginGroup`.`debug`: `ComAndroidDebugPluginGroup`
    get() = `ComAndroidDebugPluginGroup`(plugins)


/**
 * The `com.android.debug.structure` plugin implemented by [com.android.build.gradle.internal.plugins.StructurePlugin].
 */
internal
val `ComAndroidDebugPluginGroup`.`structure`: PluginDependencySpec
    get() = plugins.id("com.android.debug.structure")


/**
 * The `com.android.dynamic-feature` plugin implemented by [com.android.build.gradle.DynamicFeaturePlugin].
 */
internal
val `ComAndroidPluginGroup`.`dynamic-feature`: PluginDependencySpec
    get() = plugins.id("com.android.dynamic-feature")


/**
 * The `com.android.feature` plugin implemented by [com.android.build.gradle.FeaturePlugin].
 */
internal
val `ComAndroidPluginGroup`.`feature`: PluginDependencySpec
    get() = plugins.id("com.android.feature")


/**
 * The `com.android.instantapp` plugin implemented by [com.android.build.gradle.InstantAppPlugin].
 */
internal
val `ComAndroidPluginGroup`.`instantapp`: PluginDependencySpec
    get() = plugins.id("com.android.instantapp")


/**
 * The `com.android.library` plugin implemented by [com.android.build.gradle.LibraryPlugin].
 */
internal
val `ComAndroidPluginGroup`.`library`: PluginDependencySpec
    get() = plugins.id("com.android.library")


/**
 * The `com.android.lint` plugin implemented by [com.android.build.gradle.LintPlugin].
 */
internal
val `ComAndroidPluginGroup`.`lint`: PluginDependencySpec
    get() = plugins.id("com.android.lint")


/**
 * The `com.android.test` plugin implemented by [com.android.build.gradle.TestPlugin].
 */
internal
val `ComAndroidPluginGroup`.`test`: PluginDependencySpec
    get() = plugins.id("com.android.test")


/**
 * The `com.diffplug` plugin group.
 */
internal
class `ComDiffplugPluginGroup`(internal val plugins: PluginDependenciesSpec)


/**
 * Plugin ids starting with `com.diffplug`.
 */
internal
val `ComPluginGroup`.`diffplug`: `ComDiffplugPluginGroup`
    get() = `ComDiffplugPluginGroup`(plugins)


/**
 * The `com.diffplug.gradle` plugin group.
 */
internal
class `ComDiffplugGradlePluginGroup`(internal val plugins: PluginDependenciesSpec)


/**
 * Plugin ids starting with `com.diffplug.gradle`.
 */
internal
val `ComDiffplugPluginGroup`.`gradle`: `ComDiffplugGradlePluginGroup`
    get() = `ComDiffplugGradlePluginGroup`(plugins)


/**
 * The `com.diffplug.gradle.spotless` plugin implemented by [com.diffplug.gradle.spotless.SpotlessPlugin].
 */
internal
val `ComDiffplugGradlePluginGroup`.`spotless`: PluginDependencySpec
    get() = plugins.id("com.diffplug.gradle.spotless")


/**
 * The `com.github` plugin group.
 */
internal
class `ComGithubPluginGroup`(internal val plugins: PluginDependenciesSpec)


/**
 * Plugin ids starting with `com.github`.
 */
internal
val `ComPluginGroup`.`github`: `ComGithubPluginGroup`
    get() = `ComGithubPluginGroup`(plugins)


/**
 * The `com.github.ben-manes` plugin group.
 */
internal
class `ComGithubBen-manesPluginGroup`(internal val plugins: PluginDependenciesSpec)


/**
 * Plugin ids starting with `com.github.ben-manes`.
 */
internal
val `ComGithubPluginGroup`.`ben-manes`: `ComGithubBen-manesPluginGroup`
    get() = `ComGithubBen-manesPluginGroup`(plugins)


/**
 * The `com.github.ben-manes.versions` plugin implemented by [com.github.benmanes.gradle.versions.VersionsPlugin].
 */
internal
val `ComGithubBen-manesPluginGroup`.`versions`: PluginDependencySpec
    get() = plugins.id("com.github.ben-manes.versions")


/**
 * The `com.vanniktech` plugin group.
 */
internal
class `ComVanniktechPluginGroup`(internal val plugins: PluginDependenciesSpec)


/**
 * Plugin ids starting with `com.vanniktech`.
 */
internal
val `ComPluginGroup`.`vanniktech`: `ComVanniktechPluginGroup`
    get() = `ComVanniktechPluginGroup`(plugins)


/**
 * The `com.vanniktech.android` plugin group.
 */
internal
class `ComVanniktechAndroidPluginGroup`(internal val plugins: PluginDependenciesSpec)


/**
 * Plugin ids starting with `com.vanniktech.android`.
 */
internal
val `ComVanniktechPluginGroup`.`android`: `ComVanniktechAndroidPluginGroup`
    get() = `ComVanniktechAndroidPluginGroup`(plugins)


/**
 * The `com.vanniktech.android.junit` plugin group.
 */
internal
class `ComVanniktechAndroidJunitPluginGroup`(internal val plugins: PluginDependenciesSpec)


/**
 * Plugin ids starting with `com.vanniktech.android.junit`.
 */
internal
val `ComVanniktechAndroidPluginGroup`.`junit`: `ComVanniktechAndroidJunitPluginGroup`
    get() = `ComVanniktechAndroidJunitPluginGroup`(plugins)


/**
 * The `com.vanniktech.android.junit.jacoco` plugin implemented by [com.vanniktech.android.junit.jacoco.GenerationPlugin].
 */
internal
val `ComVanniktechAndroidJunitPluginGroup`.`jacoco`: PluginDependencySpec
    get() = plugins.id("com.vanniktech.android.junit.jacoco")


/**
 * The `com.vanniktech.dependency` plugin group.
 */
internal
class `ComVanniktechDependencyPluginGroup`(internal val plugins: PluginDependenciesSpec)


/**
 * Plugin ids starting with `com.vanniktech.dependency`.
 */
internal
val `ComVanniktechPluginGroup`.`dependency`: `ComVanniktechDependencyPluginGroup`
    get() = `ComVanniktechDependencyPluginGroup`(plugins)


/**
 * The `com.vanniktech.dependency.graph` plugin group.
 */
internal
class `ComVanniktechDependencyGraphPluginGroup`(internal val plugins: PluginDependenciesSpec)


/**
 * Plugin ids starting with `com.vanniktech.dependency.graph`.
 */
internal
val `ComVanniktechDependencyPluginGroup`.`graph`: `ComVanniktechDependencyGraphPluginGroup`
    get() = `ComVanniktechDependencyGraphPluginGroup`(plugins)


/**
 * The `com.vanniktech.dependency.graph.generator` plugin implemented by [com.vanniktech.dependency.graph.generator.DependencyGraphGeneratorPlugin].
 */
internal
val `ComVanniktechDependencyGraphPluginGroup`.`generator`: PluginDependencySpec
    get() = plugins.id("com.vanniktech.dependency.graph.generator")


/**
 * The `de` plugin group.
 */
internal
class `DePluginGroup`(internal val plugins: PluginDependenciesSpec)


/**
 * Plugin ids starting with `de`.
 */
internal
val `PluginDependenciesSpec`.`de`: `DePluginGroup`
    get() = `DePluginGroup`(this)


/**
 * The `de.undercouch` plugin group.
 */
internal
class `DeUndercouchPluginGroup`(internal val plugins: PluginDependenciesSpec)


/**
 * Plugin ids starting with `de.undercouch`.
 */
internal
val `DePluginGroup`.`undercouch`: `DeUndercouchPluginGroup`
    get() = `DeUndercouchPluginGroup`(plugins)


/**
 * The `de.undercouch.download` plugin implemented by [de.undercouch.gradle.tasks.download.DownloadTaskPlugin].
 */
internal
val `DeUndercouchPluginGroup`.`download`: PluginDependencySpec
    get() = plugins.id("de.undercouch.download")


/**
 * The `io` plugin group.
 */
internal
class `IoPluginGroup`(internal val plugins: PluginDependenciesSpec)


/**
 * Plugin ids starting with `io`.
 */
internal
val `PluginDependenciesSpec`.`io`: `IoPluginGroup`
    get() = `IoPluginGroup`(this)


/**
 * The `io.fabric` plugin implemented by [com.crashlytics.tools.gradle.CrashlyticsPlugin].
 */
internal
val `IoPluginGroup`.`fabric`: PluginDependencySpec
    get() = plugins.id("io.fabric")


/**
 * The `io.gitlab` plugin group.
 */
internal
class `IoGitlabPluginGroup`(internal val plugins: PluginDependenciesSpec)


/**
 * Plugin ids starting with `io.gitlab`.
 */
internal
val `IoPluginGroup`.`gitlab`: `IoGitlabPluginGroup`
    get() = `IoGitlabPluginGroup`(plugins)


/**
 * The `io.gitlab.arturbosch` plugin group.
 */
internal
class `IoGitlabArturboschPluginGroup`(internal val plugins: PluginDependenciesSpec)


/**
 * Plugin ids starting with `io.gitlab.arturbosch`.
 */
internal
val `IoGitlabPluginGroup`.`arturbosch`: `IoGitlabArturboschPluginGroup`
    get() = `IoGitlabArturboschPluginGroup`(plugins)


/**
 * The `io.gitlab.arturbosch.detekt` plugin implemented by [io.gitlab.arturbosch.detekt.DetektPlugin].
 */
internal
val `IoGitlabArturboschPluginGroup`.`detekt`: PluginDependencySpec
    get() = plugins.id("io.gitlab.arturbosch.detekt")


/**
 * The `kotlin` plugin implemented by [org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper].
 */
internal
val `PluginDependenciesSpec`.`kotlin`: PluginDependencySpec
    get() = this.id("kotlin")


/**
 * The `kotlin-allopen` plugin implemented by [org.jetbrains.kotlin.allopen.gradle.AllOpenGradleSubplugin].
 */
internal
val `PluginDependenciesSpec`.`kotlin-allopen`: PluginDependencySpec
    get() = this.id("kotlin-allopen")


/**
 * The `kotlin-android` plugin implemented by [org.jetbrains.kotlin.gradle.plugin.KotlinAndroidPluginWrapper].
 */
internal
val `PluginDependenciesSpec`.`kotlin-android`: PluginDependencySpec
    get() = this.id("kotlin-android")


/**
 * The `kotlin-android-extensions` plugin implemented by [org.jetbrains.kotlin.gradle.internal.AndroidExtensionsSubpluginIndicator].
 */
internal
val `PluginDependenciesSpec`.`kotlin-android-extensions`: PluginDependencySpec
    get() = this.id("kotlin-android-extensions")


/**
 * The `kotlin-dce-js` plugin implemented by [org.jetbrains.kotlin.gradle.plugin.KotlinJsDcePlugin].
 */
internal
val `PluginDependenciesSpec`.`kotlin-dce-js`: PluginDependencySpec
    get() = this.id("kotlin-dce-js")


/**
 * The `kotlin-kapt` plugin implemented by [org.jetbrains.kotlin.gradle.internal.Kapt3GradleSubplugin].
 */
internal
val `PluginDependenciesSpec`.`kotlin-kapt`: PluginDependencySpec
    get() = this.id("kotlin-kapt")


/**
 * The `kotlin-multiplatform` plugin implemented by [org.jetbrains.kotlin.gradle.plugin.KotlinMultiplatformPluginWrapper].
 */
internal
val `PluginDependenciesSpec`.`kotlin-multiplatform`: PluginDependencySpec
    get() = this.id("kotlin-multiplatform")


/**
 * The `kotlin-native-cocoapods` plugin implemented by [org.jetbrains.kotlin.gradle.plugin.cocoapods.KotlinCocoapodsPlugin].
 */
internal
val `PluginDependenciesSpec`.`kotlin-native-cocoapods`: PluginDependencySpec
    get() = this.id("kotlin-native-cocoapods")


/**
 * The `kotlin-platform-android` plugin implemented by [org.jetbrains.kotlin.gradle.plugin.KotlinPlatformAndroidPlugin].
 */
internal
val `PluginDependenciesSpec`.`kotlin-platform-android`: PluginDependencySpec
    get() = this.id("kotlin-platform-android")


/**
 * The `kotlin-platform-common` plugin implemented by [org.jetbrains.kotlin.gradle.plugin.KotlinPlatformCommonPlugin].
 */
internal
val `PluginDependenciesSpec`.`kotlin-platform-common`: PluginDependencySpec
    get() = this.id("kotlin-platform-common")


/**
 * The `kotlin-platform-js` plugin implemented by [org.jetbrains.kotlin.gradle.plugin.KotlinPlatformJsPlugin].
 */
internal
val `PluginDependenciesSpec`.`kotlin-platform-js`: PluginDependencySpec
    get() = this.id("kotlin-platform-js")


/**
 * The `kotlin-platform-jvm` plugin implemented by [org.jetbrains.kotlin.gradle.plugin.KotlinPlatformJvmPlugin].
 */
internal
val `PluginDependenciesSpec`.`kotlin-platform-jvm`: PluginDependencySpec
    get() = this.id("kotlin-platform-jvm")


/**
 * The `kotlin-scripting` plugin implemented by [org.jetbrains.kotlin.gradle.scripting.internal.ScriptingGradleSubplugin].
 */
internal
val `PluginDependenciesSpec`.`kotlin-scripting`: PluginDependencySpec
    get() = this.id("kotlin-scripting")


/**
 * The `kotlin-spring` plugin implemented by [org.jetbrains.kotlin.allopen.gradle.SpringGradleSubplugin].
 */
internal
val `PluginDependenciesSpec`.`kotlin-spring`: PluginDependencySpec
    get() = this.id("kotlin-spring")


/**
 * The `kotlin2js` plugin implemented by [org.jetbrains.kotlin.gradle.plugin.Kotlin2JsPluginWrapper].
 */
internal
val `PluginDependenciesSpec`.`kotlin2js`: PluginDependencySpec
    get() = this.id("kotlin2js")


/**
 * The `org` plugin group.
 */
internal
class `OrgPluginGroup`(internal val plugins: PluginDependenciesSpec)


/**
 * Plugin ids starting with `org`.
 */
internal
val `PluginDependenciesSpec`.`org`: `OrgPluginGroup`
    get() = `OrgPluginGroup`(this)


/**
 * The `org.gradle` plugin group.
 */
internal
class `OrgGradlePluginGroup`(internal val plugins: PluginDependenciesSpec)


/**
 * Plugin ids starting with `org.gradle`.
 */
internal
val `OrgPluginGroup`.`gradle`: `OrgGradlePluginGroup`
    get() = `OrgGradlePluginGroup`(plugins)


/**
 * The `org.gradle.antlr` plugin implemented by [org.gradle.api.plugins.antlr.AntlrPlugin].
 */
internal
val `OrgGradlePluginGroup`.`antlr`: PluginDependencySpec
    get() = plugins.id("org.gradle.antlr")


/**
 * The `org.gradle.application` plugin implemented by [org.gradle.api.plugins.ApplicationPlugin].
 */
internal
val `OrgGradlePluginGroup`.`application`: PluginDependencySpec
    get() = plugins.id("org.gradle.application")


/**
 * The `org.gradle.assembler` plugin implemented by [org.gradle.language.assembler.plugins.AssemblerPlugin].
 */
internal
val `OrgGradlePluginGroup`.`assembler`: PluginDependencySpec
    get() = plugins.id("org.gradle.assembler")


/**
 * The `org.gradle.assembler-lang` plugin implemented by [org.gradle.language.assembler.plugins.AssemblerLangPlugin].
 */
internal
val `OrgGradlePluginGroup`.`assembler-lang`: PluginDependencySpec
    get() = plugins.id("org.gradle.assembler-lang")


/**
 * The `org.gradle.base` plugin implemented by [org.gradle.api.plugins.BasePlugin].
 */
internal
val `OrgGradlePluginGroup`.`base`: PluginDependencySpec
    get() = plugins.id("org.gradle.base")


/**
 * The `org.gradle.binary-base` plugin implemented by [org.gradle.platform.base.plugins.BinaryBasePlugin].
 */
internal
val `OrgGradlePluginGroup`.`binary-base`: PluginDependencySpec
    get() = plugins.id("org.gradle.binary-base")


/**
 * The `org.gradle.build-dashboard` plugin implemented by [org.gradle.api.reporting.plugins.BuildDashboardPlugin].
 */
internal
val `OrgGradlePluginGroup`.`build-dashboard`: PluginDependencySpec
    get() = plugins.id("org.gradle.build-dashboard")


/**
 * The `org.gradle.build-init` plugin implemented by [org.gradle.buildinit.plugins.BuildInitPlugin].
 */
internal
val `OrgGradlePluginGroup`.`build-init`: PluginDependencySpec
    get() = plugins.id("org.gradle.build-init")


/**
 * The `org.gradle.c` plugin implemented by [org.gradle.language.c.plugins.CPlugin].
 */
internal
val `OrgGradlePluginGroup`.`c`: PluginDependencySpec
    get() = plugins.id("org.gradle.c")


/**
 * The `org.gradle.c-lang` plugin implemented by [org.gradle.language.c.plugins.CLangPlugin].
 */
internal
val `OrgGradlePluginGroup`.`c-lang`: PluginDependencySpec
    get() = plugins.id("org.gradle.c-lang")


/**
 * The `org.gradle.checkstyle` plugin implemented by [org.gradle.api.plugins.quality.CheckstylePlugin].
 */
internal
val `OrgGradlePluginGroup`.`checkstyle`: PluginDependencySpec
    get() = plugins.id("org.gradle.checkstyle")


/**
 * The `org.gradle.clang-compiler` plugin implemented by [org.gradle.nativeplatform.toolchain.plugins.ClangCompilerPlugin].
 */
internal
val `OrgGradlePluginGroup`.`clang-compiler`: PluginDependencySpec
    get() = plugins.id("org.gradle.clang-compiler")


/**
 * The `org.gradle.codenarc` plugin implemented by [org.gradle.api.plugins.quality.CodeNarcPlugin].
 */
internal
val `OrgGradlePluginGroup`.`codenarc`: PluginDependencySpec
    get() = plugins.id("org.gradle.codenarc")


/**
 * The `org.gradle.coffeescript-base` plugin implemented by [org.gradle.plugins.javascript.coffeescript.CoffeeScriptBasePlugin].
 */
internal
val `OrgGradlePluginGroup`.`coffeescript-base`: PluginDependencySpec
    get() = plugins.id("org.gradle.coffeescript-base")


/**
 * The `org.gradle.component-base` plugin implemented by [org.gradle.platform.base.plugins.ComponentBasePlugin].
 */
internal
val `OrgGradlePluginGroup`.`component-base`: PluginDependencySpec
    get() = plugins.id("org.gradle.component-base")


/**
 * The `org.gradle.component-model-base` plugin implemented by [org.gradle.language.base.plugins.ComponentModelBasePlugin].
 */
internal
val `OrgGradlePluginGroup`.`component-model-base`: PluginDependencySpec
    get() = plugins.id("org.gradle.component-model-base")


/**
 * The `org.gradle.cpp` plugin implemented by [org.gradle.language.cpp.plugins.CppPlugin].
 */
internal
val `OrgGradlePluginGroup`.`cpp`: PluginDependencySpec
    get() = plugins.id("org.gradle.cpp")


/**
 * The `org.gradle.cpp-application` plugin implemented by [org.gradle.language.cpp.plugins.CppApplicationPlugin].
 */
internal
val `OrgGradlePluginGroup`.`cpp-application`: PluginDependencySpec
    get() = plugins.id("org.gradle.cpp-application")


/**
 * The `org.gradle.cpp-lang` plugin implemented by [org.gradle.language.cpp.plugins.CppLangPlugin].
 */
internal
val `OrgGradlePluginGroup`.`cpp-lang`: PluginDependencySpec
    get() = plugins.id("org.gradle.cpp-lang")


/**
 * The `org.gradle.cpp-library` plugin implemented by [org.gradle.language.cpp.plugins.CppLibraryPlugin].
 */
internal
val `OrgGradlePluginGroup`.`cpp-library`: PluginDependencySpec
    get() = plugins.id("org.gradle.cpp-library")


/**
 * The `org.gradle.cpp-unit-test` plugin implemented by [org.gradle.nativeplatform.test.cpp.plugins.CppUnitTestPlugin].
 */
internal
val `OrgGradlePluginGroup`.`cpp-unit-test`: PluginDependencySpec
    get() = plugins.id("org.gradle.cpp-unit-test")


/**
 * The `org.gradle.cunit` plugin implemented by [org.gradle.nativeplatform.test.cunit.plugins.CUnitConventionPlugin].
 */
internal
val `OrgGradlePluginGroup`.`cunit`: PluginDependencySpec
    get() = plugins.id("org.gradle.cunit")


/**
 * The `org.gradle.cunit-test-suite` plugin implemented by [org.gradle.nativeplatform.test.cunit.plugins.CUnitPlugin].
 */
internal
val `OrgGradlePluginGroup`.`cunit-test-suite`: PluginDependencySpec
    get() = plugins.id("org.gradle.cunit-test-suite")


/**
 * The `org.gradle.distribution` plugin implemented by [org.gradle.api.distribution.plugins.DistributionPlugin].
 */
internal
val `OrgGradlePluginGroup`.`distribution`: PluginDependencySpec
    get() = plugins.id("org.gradle.distribution")


/**
 * The `org.gradle.ear` plugin implemented by [org.gradle.plugins.ear.EarPlugin].
 */
internal
val `OrgGradlePluginGroup`.`ear`: PluginDependencySpec
    get() = plugins.id("org.gradle.ear")


/**
 * The `org.gradle.eclipse` plugin implemented by [org.gradle.plugins.ide.eclipse.EclipsePlugin].
 */
internal
val `OrgGradlePluginGroup`.`eclipse`: PluginDependencySpec
    get() = plugins.id("org.gradle.eclipse")


/**
 * The `org.gradle.eclipse-wtp` plugin implemented by [org.gradle.plugins.ide.eclipse.EclipseWtpPlugin].
 */
internal
val `OrgGradlePluginGroup`.`eclipse-wtp`: PluginDependencySpec
    get() = plugins.id("org.gradle.eclipse-wtp")


/**
 * The `org.gradle.envjs` plugin implemented by [org.gradle.plugins.javascript.envjs.EnvJsPlugin].
 */
internal
val `OrgGradlePluginGroup`.`envjs`: PluginDependencySpec
    get() = plugins.id("org.gradle.envjs")


/**
 * The `org.gradle.gcc-compiler` plugin implemented by [org.gradle.nativeplatform.toolchain.plugins.GccCompilerPlugin].
 */
internal
val `OrgGradlePluginGroup`.`gcc-compiler`: PluginDependencySpec
    get() = plugins.id("org.gradle.gcc-compiler")


/**
 * The `org.gradle.google-test` plugin implemented by [org.gradle.nativeplatform.test.googletest.plugins.GoogleTestConventionPlugin].
 */
internal
val `OrgGradlePluginGroup`.`google-test`: PluginDependencySpec
    get() = plugins.id("org.gradle.google-test")


/**
 * The `org.gradle.google-test-test-suite` plugin implemented by [org.gradle.nativeplatform.test.googletest.plugins.GoogleTestPlugin].
 */
internal
val `OrgGradlePluginGroup`.`google-test-test-suite`: PluginDependencySpec
    get() = plugins.id("org.gradle.google-test-test-suite")


/**
 * The `org.gradle.groovy` plugin implemented by [org.gradle.api.plugins.GroovyPlugin].
 */
internal
val `OrgGradlePluginGroup`.`groovy`: PluginDependencySpec
    get() = plugins.id("org.gradle.groovy")


/**
 * The `org.gradle.groovy-base` plugin implemented by [org.gradle.api.plugins.GroovyBasePlugin].
 */
internal
val `OrgGradlePluginGroup`.`groovy-base`: PluginDependencySpec
    get() = plugins.id("org.gradle.groovy-base")


/**
 * The `org.gradle.help-tasks` plugin implemented by [org.gradle.api.plugins.HelpTasksPlugin].
 */
internal
val `OrgGradlePluginGroup`.`help-tasks`: PluginDependencySpec
    get() = plugins.id("org.gradle.help-tasks")


/**
 * The `org.gradle.idea` plugin implemented by [org.gradle.plugins.ide.idea.IdeaPlugin].
 */
internal
val `OrgGradlePluginGroup`.`idea`: PluginDependencySpec
    get() = plugins.id("org.gradle.idea")


/**
 * The `org.gradle.ivy-publish` plugin implemented by [org.gradle.api.publish.ivy.plugins.IvyPublishPlugin].
 */
internal
val `OrgGradlePluginGroup`.`ivy-publish`: PluginDependencySpec
    get() = plugins.id("org.gradle.ivy-publish")


/**
 * The `org.gradle.jacoco` plugin implemented by [org.gradle.testing.jacoco.plugins.JacocoPlugin].
 */
internal
val `OrgGradlePluginGroup`.`jacoco`: PluginDependencySpec
    get() = plugins.id("org.gradle.jacoco")


/**
 * The `org.gradle.java` plugin implemented by [org.gradle.api.plugins.JavaPlugin].
 */
internal
val `OrgGradlePluginGroup`.`java`: PluginDependencySpec
    get() = plugins.id("org.gradle.java")


/**
 * The `org.gradle.java-base` plugin implemented by [org.gradle.api.plugins.JavaBasePlugin].
 */
internal
val `OrgGradlePluginGroup`.`java-base`: PluginDependencySpec
    get() = plugins.id("org.gradle.java-base")


/**
 * The `org.gradle.java-gradle-plugin` plugin implemented by [org.gradle.plugin.devel.plugins.JavaGradlePluginPlugin].
 */
internal
val `OrgGradlePluginGroup`.`java-gradle-plugin`: PluginDependencySpec
    get() = plugins.id("org.gradle.java-gradle-plugin")


/**
 * The `org.gradle.java-lang` plugin implemented by [org.gradle.language.java.plugins.JavaLanguagePlugin].
 */
internal
val `OrgGradlePluginGroup`.`java-lang`: PluginDependencySpec
    get() = plugins.id("org.gradle.java-lang")


/**
 * The `org.gradle.java-library` plugin implemented by [org.gradle.api.plugins.JavaLibraryPlugin].
 */
internal
val `OrgGradlePluginGroup`.`java-library`: PluginDependencySpec
    get() = plugins.id("org.gradle.java-library")


/**
 * The `org.gradle.java-library-distribution` plugin implemented by [org.gradle.api.plugins.JavaLibraryDistributionPlugin].
 */
internal
val `OrgGradlePluginGroup`.`java-library-distribution`: PluginDependencySpec
    get() = plugins.id("org.gradle.java-library-distribution")


/**
 * The `org.gradle.java-platform` plugin implemented by [org.gradle.api.plugins.JavaPlatformPlugin].
 */
internal
val `OrgGradlePluginGroup`.`java-platform`: PluginDependencySpec
    get() = plugins.id("org.gradle.java-platform")


/**
 * The `org.gradle.java-test-fixtures` plugin implemented by [org.gradle.api.plugins.JavaTestFixturesPlugin].
 */
internal
val `OrgGradlePluginGroup`.`java-test-fixtures`: PluginDependencySpec
    get() = plugins.id("org.gradle.java-test-fixtures")


/**
 * The `org.gradle.javascript-base` plugin implemented by [org.gradle.plugins.javascript.base.JavaScriptBasePlugin].
 */
internal
val `OrgGradlePluginGroup`.`javascript-base`: PluginDependencySpec
    get() = plugins.id("org.gradle.javascript-base")


/**
 * The `org.gradle.jshint` plugin implemented by [org.gradle.plugins.javascript.jshint.JsHintPlugin].
 */
internal
val `OrgGradlePluginGroup`.`jshint`: PluginDependencySpec
    get() = plugins.id("org.gradle.jshint")


/**
 * The `org.gradle.junit-test-suite` plugin implemented by [org.gradle.jvm.plugins.JUnitTestSuitePlugin].
 */
internal
val `OrgGradlePluginGroup`.`junit-test-suite`: PluginDependencySpec
    get() = plugins.id("org.gradle.junit-test-suite")


/**
 * The `org.gradle.jvm-component` plugin implemented by [org.gradle.jvm.plugins.JvmComponentPlugin].
 */
internal
val `OrgGradlePluginGroup`.`jvm-component`: PluginDependencySpec
    get() = plugins.id("org.gradle.jvm-component")


/**
 * The `org.gradle.jvm-resources` plugin implemented by [org.gradle.language.jvm.plugins.JvmResourcesPlugin].
 */
internal
val `OrgGradlePluginGroup`.`jvm-resources`: PluginDependencySpec
    get() = plugins.id("org.gradle.jvm-resources")


/**
 * The `org.gradle.language-base` plugin implemented by [org.gradle.language.base.plugins.LanguageBasePlugin].
 */
internal
val `OrgGradlePluginGroup`.`language-base`: PluginDependencySpec
    get() = plugins.id("org.gradle.language-base")


/**
 * The `org.gradle.lifecycle-base` plugin implemented by [org.gradle.language.base.plugins.LifecycleBasePlugin].
 */
internal
val `OrgGradlePluginGroup`.`lifecycle-base`: PluginDependencySpec
    get() = plugins.id("org.gradle.lifecycle-base")


/**
 * The `org.gradle.maven` plugin implemented by [org.gradle.api.plugins.MavenPlugin].
 */
internal
val `OrgGradlePluginGroup`.`maven`: PluginDependencySpec
    get() = plugins.id("org.gradle.maven")


/**
 * The `org.gradle.maven-publish` plugin implemented by [org.gradle.api.publish.maven.plugins.MavenPublishPlugin].
 */
internal
val `OrgGradlePluginGroup`.`maven-publish`: PluginDependencySpec
    get() = plugins.id("org.gradle.maven-publish")


/**
 * The `org.gradle.microsoft-visual-cpp-compiler` plugin implemented by [org.gradle.nativeplatform.toolchain.plugins.MicrosoftVisualCppCompilerPlugin].
 */
internal
val `OrgGradlePluginGroup`.`microsoft-visual-cpp-compiler`: PluginDependencySpec
    get() = plugins.id("org.gradle.microsoft-visual-cpp-compiler")


/**
 * The `org.gradle.native-component` plugin implemented by [org.gradle.nativeplatform.plugins.NativeComponentPlugin].
 */
internal
val `OrgGradlePluginGroup`.`native-component`: PluginDependencySpec
    get() = plugins.id("org.gradle.native-component")


/**
 * The `org.gradle.native-component-model` plugin implemented by [org.gradle.nativeplatform.plugins.NativeComponentModelPlugin].
 */
internal
val `OrgGradlePluginGroup`.`native-component-model`: PluginDependencySpec
    get() = plugins.id("org.gradle.native-component-model")


/**
 * The `org.gradle.objective-c` plugin implemented by [org.gradle.language.objectivec.plugins.ObjectiveCPlugin].
 */
internal
val `OrgGradlePluginGroup`.`objective-c`: PluginDependencySpec
    get() = plugins.id("org.gradle.objective-c")


/**
 * The `org.gradle.objective-c-lang` plugin implemented by [org.gradle.language.objectivec.plugins.ObjectiveCLangPlugin].
 */
internal
val `OrgGradlePluginGroup`.`objective-c-lang`: PluginDependencySpec
    get() = plugins.id("org.gradle.objective-c-lang")


/**
 * The `org.gradle.objective-cpp` plugin implemented by [org.gradle.language.objectivecpp.plugins.ObjectiveCppPlugin].
 */
internal
val `OrgGradlePluginGroup`.`objective-cpp`: PluginDependencySpec
    get() = plugins.id("org.gradle.objective-cpp")


/**
 * The `org.gradle.objective-cpp-lang` plugin implemented by [org.gradle.language.objectivecpp.plugins.ObjectiveCppLangPlugin].
 */
internal
val `OrgGradlePluginGroup`.`objective-cpp-lang`: PluginDependencySpec
    get() = plugins.id("org.gradle.objective-cpp-lang")


/**
 * The `org.gradle.play` plugin implemented by [org.gradle.play.plugins.PlayPlugin].
 */
internal
val `OrgGradlePluginGroup`.`play`: PluginDependencySpec
    get() = plugins.id("org.gradle.play")


/**
 * The `org.gradle.play-application` plugin implemented by [org.gradle.play.plugins.PlayApplicationPlugin].
 */
internal
val `OrgGradlePluginGroup`.`play-application`: PluginDependencySpec
    get() = plugins.id("org.gradle.play-application")


/**
 * The `org.gradle.play-coffeescript` plugin implemented by [org.gradle.play.plugins.PlayCoffeeScriptPlugin].
 */
internal
val `OrgGradlePluginGroup`.`play-coffeescript`: PluginDependencySpec
    get() = plugins.id("org.gradle.play-coffeescript")


/**
 * The `org.gradle.play-ide` plugin implemented by [org.gradle.play.plugins.ide.PlayIdePlugin].
 */
internal
val `OrgGradlePluginGroup`.`play-ide`: PluginDependencySpec
    get() = plugins.id("org.gradle.play-ide")


/**
 * The `org.gradle.play-javascript` plugin implemented by [org.gradle.play.plugins.PlayJavaScriptPlugin].
 */
internal
val `OrgGradlePluginGroup`.`play-javascript`: PluginDependencySpec
    get() = plugins.id("org.gradle.play-javascript")


/**
 * The `org.gradle.pmd` plugin implemented by [org.gradle.api.plugins.quality.PmdPlugin].
 */
internal
val `OrgGradlePluginGroup`.`pmd`: PluginDependencySpec
    get() = plugins.id("org.gradle.pmd")


/**
 * The `org.gradle.project-report` plugin implemented by [org.gradle.api.plugins.ProjectReportsPlugin].
 */
internal
val `OrgGradlePluginGroup`.`project-report`: PluginDependencySpec
    get() = plugins.id("org.gradle.project-report")


/**
 * The `org.gradle.project-reports` plugin implemented by [org.gradle.api.plugins.ProjectReportsPlugin].
 */
internal
val `OrgGradlePluginGroup`.`project-reports`: PluginDependencySpec
    get() = plugins.id("org.gradle.project-reports")


/**
 * The `org.gradle.publishing` plugin implemented by [org.gradle.api.publish.plugins.PublishingPlugin].
 */
internal
val `OrgGradlePluginGroup`.`publishing`: PluginDependencySpec
    get() = plugins.id("org.gradle.publishing")


/**
 * The `org.gradle.reporting-base` plugin implemented by [org.gradle.api.plugins.ReportingBasePlugin].
 */
internal
val `OrgGradlePluginGroup`.`reporting-base`: PluginDependencySpec
    get() = plugins.id("org.gradle.reporting-base")


/**
 * The `org.gradle.rhino` plugin implemented by [org.gradle.plugins.javascript.rhino.RhinoPlugin].
 */
internal
val `OrgGradlePluginGroup`.`rhino`: PluginDependencySpec
    get() = plugins.id("org.gradle.rhino")


/**
 * The `org.gradle.scala` plugin implemented by [org.gradle.api.plugins.scala.ScalaPlugin].
 */
internal
val `OrgGradlePluginGroup`.`scala`: PluginDependencySpec
    get() = plugins.id("org.gradle.scala")


/**
 * The `org.gradle.scala-base` plugin implemented by [org.gradle.api.plugins.scala.ScalaBasePlugin].
 */
internal
val `OrgGradlePluginGroup`.`scala-base`: PluginDependencySpec
    get() = plugins.id("org.gradle.scala-base")


/**
 * The `org.gradle.scala-lang` plugin implemented by [org.gradle.language.scala.plugins.ScalaLanguagePlugin].
 */
internal
val `OrgGradlePluginGroup`.`scala-lang`: PluginDependencySpec
    get() = plugins.id("org.gradle.scala-lang")


/**
 * The `org.gradle.signing` plugin implemented by [org.gradle.plugins.signing.SigningPlugin].
 */
internal
val `OrgGradlePluginGroup`.`signing`: PluginDependencySpec
    get() = plugins.id("org.gradle.signing")


/**
 * The `org.gradle.standard-tool-chains` plugin implemented by [org.gradle.nativeplatform.toolchain.internal.plugins.StandardToolChainsPlugin].
 */
internal
val `OrgGradlePluginGroup`.`standard-tool-chains`: PluginDependencySpec
    get() = plugins.id("org.gradle.standard-tool-chains")


/**
 * The `org.gradle.swift-application` plugin implemented by [org.gradle.language.swift.plugins.SwiftApplicationPlugin].
 */
internal
val `OrgGradlePluginGroup`.`swift-application`: PluginDependencySpec
    get() = plugins.id("org.gradle.swift-application")


/**
 * The `org.gradle.swift-library` plugin implemented by [org.gradle.language.swift.plugins.SwiftLibraryPlugin].
 */
internal
val `OrgGradlePluginGroup`.`swift-library`: PluginDependencySpec
    get() = plugins.id("org.gradle.swift-library")


/**
 * The `org.gradle.swiftpm-export` plugin implemented by [org.gradle.swiftpm.plugins.SwiftPackageManagerExportPlugin].
 */
internal
val `OrgGradlePluginGroup`.`swiftpm-export`: PluginDependencySpec
    get() = plugins.id("org.gradle.swiftpm-export")


/**
 * The `org.gradle.visual-studio` plugin implemented by [org.gradle.ide.visualstudio.plugins.VisualStudioPlugin].
 */
internal
val `OrgGradlePluginGroup`.`visual-studio`: PluginDependencySpec
    get() = plugins.id("org.gradle.visual-studio")


/**
 * The `org.gradle.war` plugin implemented by [org.gradle.api.plugins.WarPlugin].
 */
internal
val `OrgGradlePluginGroup`.`war`: PluginDependencySpec
    get() = plugins.id("org.gradle.war")


/**
 * The `org.gradle.windows-resource-script` plugin implemented by [org.gradle.language.rc.plugins.WindowsResourceScriptPlugin].
 */
internal
val `OrgGradlePluginGroup`.`windows-resource-script`: PluginDependencySpec
    get() = plugins.id("org.gradle.windows-resource-script")


/**
 * The `org.gradle.windows-resources` plugin implemented by [org.gradle.language.rc.plugins.WindowsResourcesPlugin].
 */
internal
val `OrgGradlePluginGroup`.`windows-resources`: PluginDependencySpec
    get() = plugins.id("org.gradle.windows-resources")


/**
 * The `org.gradle.wrapper` plugin implemented by [org.gradle.buildinit.plugins.WrapperPlugin].
 */
internal
val `OrgGradlePluginGroup`.`wrapper`: PluginDependencySpec
    get() = plugins.id("org.gradle.wrapper")


/**
 * The `org.gradle.xcode` plugin implemented by [org.gradle.ide.xcode.plugins.XcodePlugin].
 */
internal
val `OrgGradlePluginGroup`.`xcode`: PluginDependencySpec
    get() = plugins.id("org.gradle.xcode")


/**
 * The `org.gradle.xctest` plugin implemented by [org.gradle.nativeplatform.test.xctest.plugins.XCTestConventionPlugin].
 */
internal
val `OrgGradlePluginGroup`.`xctest`: PluginDependencySpec
    get() = plugins.id("org.gradle.xctest")


/**
 * The `org.jetbrains` plugin group.
 */
internal
class `OrgJetbrainsPluginGroup`(internal val plugins: PluginDependenciesSpec)


/**
 * Plugin ids starting with `org.jetbrains`.
 */
internal
val `OrgPluginGroup`.`jetbrains`: `OrgJetbrainsPluginGroup`
    get() = `OrgJetbrainsPluginGroup`(plugins)


/**
 * The `org.jetbrains.dokka` plugin implemented by [org.jetbrains.dokka.gradle.DokkaPlugin].
 */
internal
val `OrgJetbrainsPluginGroup`.`dokka`: PluginDependencySpec
    get() = plugins.id("org.jetbrains.dokka")


/**
 * The `org.jetbrains.kotlin` plugin group.
 */
internal
class `OrgJetbrainsKotlinPluginGroup`(internal val plugins: PluginDependenciesSpec)


/**
 * Plugin ids starting with `org.jetbrains.kotlin`.
 */
internal
val `OrgJetbrainsPluginGroup`.`kotlin`: `OrgJetbrainsKotlinPluginGroup`
    get() = `OrgJetbrainsKotlinPluginGroup`(plugins)


/**
 * The `org.jetbrains.kotlin.android` plugin implemented by [org.jetbrains.kotlin.gradle.plugin.KotlinAndroidPluginWrapper].
 */
internal
val `OrgJetbrainsKotlinPluginGroup`.`android`: PluginDependencySpec
    get() = plugins.id("org.jetbrains.kotlin.android")


/**
 * The `org.jetbrains.kotlin.js` plugin implemented by [org.jetbrains.kotlin.gradle.plugin.KotlinJsPluginWrapper].
 */
internal
val `OrgJetbrainsKotlinPluginGroup`.`js`: PluginDependencySpec
    get() = plugins.id("org.jetbrains.kotlin.js")


/**
 * The `org.jetbrains.kotlin.jvm` plugin implemented by [org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper].
 */
internal
val `OrgJetbrainsKotlinPluginGroup`.`jvm`: PluginDependencySpec
    get() = plugins.id("org.jetbrains.kotlin.jvm")


/**
 * The `org.jetbrains.kotlin.kapt` plugin implemented by [org.jetbrains.kotlin.gradle.internal.Kapt3GradleSubplugin].
 */
internal
val `OrgJetbrainsKotlinPluginGroup`.`kapt`: PluginDependencySpec
    get() = plugins.id("org.jetbrains.kotlin.kapt")


/**
 * The `org.jetbrains.kotlin.multiplatform` plugin implemented by [org.jetbrains.kotlin.gradle.plugin.KotlinMultiplatformPluginWrapper].
 */
internal
val `OrgJetbrainsKotlinPluginGroup`.`multiplatform`: PluginDependencySpec
    get() = plugins.id("org.jetbrains.kotlin.multiplatform")


/**
 * The `org.jetbrains.kotlin.native` plugin group.
 */
internal
class `OrgJetbrainsKotlinNativePluginGroup`(internal val plugins: PluginDependenciesSpec)


/**
 * Plugin ids starting with `org.jetbrains.kotlin.native`.
 */
internal
val `OrgJetbrainsKotlinPluginGroup`.`native`: `OrgJetbrainsKotlinNativePluginGroup`
    get() = `OrgJetbrainsKotlinNativePluginGroup`(plugins)


/**
 * The `org.jetbrains.kotlin.native.cocoapods` plugin implemented by [org.jetbrains.kotlin.gradle.plugin.cocoapods.KotlinCocoapodsPlugin].
 */
internal
val `OrgJetbrainsKotlinNativePluginGroup`.`cocoapods`: PluginDependencySpec
    get() = plugins.id("org.jetbrains.kotlin.native.cocoapods")


/**
 * The `org.jetbrains.kotlin.platform` plugin group.
 */
internal
class `OrgJetbrainsKotlinPlatformPluginGroup`(internal val plugins: PluginDependenciesSpec)


/**
 * Plugin ids starting with `org.jetbrains.kotlin.platform`.
 */
internal
val `OrgJetbrainsKotlinPluginGroup`.`platform`: `OrgJetbrainsKotlinPlatformPluginGroup`
    get() = `OrgJetbrainsKotlinPlatformPluginGroup`(plugins)


/**
 * The `org.jetbrains.kotlin.platform.android` plugin implemented by [org.jetbrains.kotlin.gradle.plugin.KotlinPlatformAndroidPlugin].
 */
internal
val `OrgJetbrainsKotlinPlatformPluginGroup`.`android`: PluginDependencySpec
    get() = plugins.id("org.jetbrains.kotlin.platform.android")


/**
 * The `org.jetbrains.kotlin.platform.common` plugin implemented by [org.jetbrains.kotlin.gradle.plugin.KotlinPlatformCommonPlugin].
 */
internal
val `OrgJetbrainsKotlinPlatformPluginGroup`.`common`: PluginDependencySpec
    get() = plugins.id("org.jetbrains.kotlin.platform.common")


/**
 * The `org.jetbrains.kotlin.platform.js` plugin implemented by [org.jetbrains.kotlin.gradle.plugin.KotlinPlatformJsPlugin].
 */
internal
val `OrgJetbrainsKotlinPlatformPluginGroup`.`js`: PluginDependencySpec
    get() = plugins.id("org.jetbrains.kotlin.platform.js")


/**
 * The `org.jetbrains.kotlin.platform.jvm` plugin implemented by [org.jetbrains.kotlin.gradle.plugin.KotlinPlatformJvmPlugin].
 */
internal
val `OrgJetbrainsKotlinPlatformPluginGroup`.`jvm`: PluginDependencySpec
    get() = plugins.id("org.jetbrains.kotlin.platform.jvm")


/**
 * The `org.jetbrains.kotlin.plugin` plugin group.
 */
internal
class `OrgJetbrainsKotlinPluginPluginGroup`(internal val plugins: PluginDependenciesSpec)


/**
 * Plugin ids starting with `org.jetbrains.kotlin.plugin`.
 */
internal
val `OrgJetbrainsKotlinPluginGroup`.`plugin`: `OrgJetbrainsKotlinPluginPluginGroup`
    get() = `OrgJetbrainsKotlinPluginPluginGroup`(plugins)


/**
 * The `org.jetbrains.kotlin.plugin.allopen` plugin implemented by [org.jetbrains.kotlin.allopen.gradle.AllOpenGradleSubplugin].
 */
internal
val `OrgJetbrainsKotlinPluginPluginGroup`.`allopen`: PluginDependencySpec
    get() = plugins.id("org.jetbrains.kotlin.plugin.allopen")


/**
 * The `org.jetbrains.kotlin.plugin.scripting` plugin implemented by [org.jetbrains.kotlin.gradle.scripting.internal.ScriptingGradleSubplugin].
 */
internal
val `OrgJetbrainsKotlinPluginPluginGroup`.`scripting`: PluginDependencySpec
    get() = plugins.id("org.jetbrains.kotlin.plugin.scripting")


/**
 * The `org.jetbrains.kotlin.plugin.spring` plugin implemented by [org.jetbrains.kotlin.allopen.gradle.SpringGradleSubplugin].
 */
internal
val `OrgJetbrainsKotlinPluginPluginGroup`.`spring`: PluginDependencySpec
    get() = plugins.id("org.jetbrains.kotlin.plugin.spring")
