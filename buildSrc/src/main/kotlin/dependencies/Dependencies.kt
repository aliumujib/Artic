package dependencies

/**
 * Project dependencies, makes it easy to include external binaries or
 * other library modules to build.
 */
object Dependencies {
    const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${BuildDependenciesVersions.KOTLIN}"
    const val APPCOMPAT = "androidx.appcompat:appcompat:${BuildDependenciesVersions.APPCOMPAT}"
    const val MATERIAL = "com.google.android.material:material:${BuildDependenciesVersions.MATERIAL}"
    const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${BuildDependenciesVersions.COROUTINES}"
    const val COROUTINES_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${BuildDependenciesVersions.COROUTINES}"
    const val ROOM = "androidx.room:room-runtime:${BuildDependenciesVersions.ROOM}"
    const val ROOM_KTX = "androidx.room:room-ktx:${BuildDependenciesVersions.ROOM}"
    const val RX_JAVA_2 = "io.reactivex.rxjava2:rxjava:${BuildDependenciesVersions.RX_JAVA_VERSION}"
    const val RX_ANDROID  = "io.reactivex.rxjava2:rxandroid:${BuildDependenciesVersions.RX_ANDROID_VERSION}"
    const val JODA_TIME = "net.danlew:android.joda:${BuildDependenciesVersions.JODA_TIME_VERSION}"
    const val ROOM_RX_JAVA_2 = "androidx.room:room-rxjava2:${BuildDependenciesVersions.ROOM}"
    const val JAVAX_ANNOTATION = "javax.annotation:jsr250-api:${BuildDependenciesVersions.JAVAX_ANNOTATION_VERSION}"
    const val JAVAX_INJECT=  "javax.inject:javax.inject:${BuildDependenciesVersions.JAVAX_INJECT_VERSION}"
    const val RETROFIT_RX_ADAPTER = "com.squareup.retrofit2:adapter-rxjava2:${BuildDependenciesVersions.RETROFIT_RX_ADAPTER_VERSION}"
    const val OKHTTP3_LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:${BuildDependenciesVersions.OKHTTP3_LOGGING_INTERCEPTOR_VERSION}"
    const val LEAK_CANARY = "com.squareup.leakcanary:leakcanary-android:${BuildDependenciesVersions.LEAK_CANARY_VERSION}"
    const val PICASSO = "com.squareup.picasso:picasso:${BuildDependenciesVersions.PICASSO_VERSION}"
    const val MULTIDEX = "androidx.multidex:multidex:${BuildDependenciesVersions.MULTIDEX}"
    const val MATERIAL_DESIGN_SPECS = "com.androidessence:materialdesignspecs:${BuildDependenciesVersions.MATERIAL_DESIGN_SPECS}"
    const val PICASSO_TRANSFORMATIONS = "jp.wasabeef:picasso-transformations:${BuildDependenciesVersions.PICASSO_TRANSFORMATIONS}"
    const val CIRCLE_IMAGE_VIEW = "de.hdodenhof:circleimageview:${BuildDependenciesVersions.CIRCLE_IMAGE_VIEW}"
    const val STETHO = "com.facebook.stetho:stetho:${BuildDependenciesVersions.STETHO}"
    const val RXBINDING_PLATFORM = "com.jakewharton.rxbinding3:rxbinding:${BuildDependenciesVersions.RXBINDING_PLATFORM}"
    const val CARD_VIEW = "androidx.cardview:cardview:${BuildDependenciesVersions.MATERIAL}"
    const val FLOWBINDING_ANDROID = "io.github.reactivecircus.flowbinding:flowbinding-android:${BuildDependenciesVersions.FLOW_BINDING}"
    const val FLOWBINDING_APPCOMPAT = "io.github.reactivecircus.flowbinding:flowbinding-appcompat:${BuildDependenciesVersions.FLOW_BINDING}"
    const val FLOWBINDING_CORE = "io.github.reactivecircus.flowbinding:flowbinding-core:${BuildDependenciesVersions.FLOW_BINDING}"
    const val FLOWBINDING_RECYCLERVIEW = "io.github.reactivecircus.flowbinding:flowbinding-recyclerview:${BuildDependenciesVersions.FLOW_BINDING}"
    const val FLOWBINDING_SWIPEREFRESH = "io.github.reactivecircus.flowbinding:flowbinding-swiperefreshlayout:${BuildDependenciesVersions.FLOW_BINDING}"
    const val PRO_PROGRESS_VIEWS = "com.github.DamanSingh4321:ProProgressViews:v${BuildDependenciesVersions.PRO_PROGRESS_VIEWS}"


    const val RECYCLE_VIEW = "androidx.recyclerview:recyclerview:${BuildDependenciesVersions.RECYCLE_VIEW}"
    const val NAVIGATION_FRAGMENT = "androidx.navigation:navigation-fragment-ktx:${BuildDependenciesVersions.NAVIGATION}"
    const val NAVIGATION_UI = "androidx.navigation:navigation-ui-ktx:${BuildDependenciesVersions.NAVIGATION}"
    const val NAVIGATION_DFM = "androidx.navigation:navigation-dynamic-features-fragment:${BuildDependenciesVersions.NAVIGATION}"
    const val SHIMMER = "com.facebook.shimmer:shimmer:${BuildDependenciesVersions.SHIMMER}@aar"
    const val HTML_TEXT_VIEW = "org.sufficientlysecure:html-textview:${BuildDependenciesVersions.HTML_TEXTVIEW}"


    const val LIFECYCLE_EXTENSIONS = "androidx.lifecycle:lifecycle-extensions:${BuildDependenciesVersions.LIFECYCLE}"
    const val LIFECYCLE_VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:${BuildDependenciesVersions.LIFECYCLE}"
    const val CORE_KTX = "androidx.core:core-ktx:${BuildDependenciesVersions.CORE_KTX}"
    const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:${BuildDependenciesVersions.FRAGMENT_KTX}"
    const val CONSTRAIN_LAYOUT = "androidx.constraintlayout:constraintlayout:${BuildDependenciesVersions.CONSTRAIN_LAYOUT}"
    const val SWIPE_REFRESH_LAYOUT = "androidx.swiperefreshlayout:swiperefreshlayout:${BuildDependenciesVersions.SWIPE_REFRESH_LAYOUT}"
    const val PAGING = "androidx.paging:paging-runtime-ktx:${BuildDependenciesVersions.PAGING}"
    const val DAGGER = "com.google.dagger:dagger:${BuildDependenciesVersions.DAGGER}"
    const val DAGGER_ANDROID = "com.google.dagger:dagger-android-support:${BuildDependenciesVersions.DAGGER}"
    const val RX_RELAYS = "com.jakewharton.rxrelay2:rxrelay:2.1.1" //TODO delete

    const val TIMBER = "com.jakewharton.timber:timber:${BuildDependenciesVersions.TIMBER}"
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${BuildDependenciesVersions.RETROFIT}"
    const val RETROFIT_CONVERTER = "com.squareup.retrofit2:converter-gson:${BuildDependenciesVersions.RETROFIT}"
    const val LOGGING = "com.squareup.okhttp3:logging-interceptor:${BuildDependenciesVersions.LOGGING}"
    const val MOSHI = "com.squareup.moshi:moshi:${BuildDependenciesVersions.MOSHI}"
    const val MOSHI_KTX = "com.squareup.moshi:moshi-kotlin:${BuildDependenciesVersions.MOSHI}"
    const val COIL = "io.coil-kt:coil:${BuildDependenciesVersions.COIL}"
    const val CRASHLYTICS = "com.crashlytics.sdk.android:crashlytics:${BuildDependenciesVersions.CRASHLYTICS}"
    const val PLAY_CORE = "com.google.android.play:core:${BuildDependenciesVersions.PLAY_CORE}"
}
