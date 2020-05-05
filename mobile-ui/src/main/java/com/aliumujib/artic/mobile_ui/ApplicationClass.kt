/*
 * Copyright 2020 Abdul-Mujeeb Aliu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.aliumujib.artic.mobile_ui

import android.content.Context
import com.aliumujib.artic.mobile_ui.di.DaggerApplicationComponent
import com.aliumujib.artic.di.components.CoreComponent
import com.aliumujib.artic.di.components.DaggerCoreComponent
import com.aliumujib.artic.di.modules.ContextModule
import com.google.android.play.core.splitcompat.SplitCompatApplication
import timber.log.Timber

class ApplicationClass : SplitCompatApplication() {

    lateinit var coreComponent: CoreComponent

    override fun onCreate() {
        super.onCreate()

        initCoreDependencyInjection()
        initAppDependencyInjection()

        initTimber()
    }

    /**
     * Initialize core dependency injection component.
     */
    private fun initAppDependencyInjection() {
        DaggerApplicationComponent
            .builder()
            .coreComponent(coreComponent)
            .build()
            .inject(this)
    }

    /**
     * Initialize core dependency injection component.
     */
    private fun initCoreDependencyInjection() {
        coreComponent = DaggerCoreComponent
            .builder()
            .contextModule(ContextModule(this))
            .build()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        Timber.i("%s %d", BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE)
    }



    companion object {

        /**
         * Obtain core dagger component.
         *
         * @param context The application context
         */
        @JvmStatic
        fun coreComponent(context: Context) =
            (context.applicationContext as? ApplicationClass)?.coreComponent
    }

}
