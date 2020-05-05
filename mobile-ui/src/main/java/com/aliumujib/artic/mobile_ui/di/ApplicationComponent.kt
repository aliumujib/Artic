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
package com.aliumujib.artic.mobile_ui.di


import com.aliumujib.artic.mobile_ui.ApplicationClass
import com.aliumujib.artic.di.components.CoreComponent
import com.aliumujib.artic.di.scopes.AppScope
import dagger.Component

@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class]
)
interface ApplicationComponent {


    /**
     * Inject dependencies on application.
     *
     * @param application The sample application.
     */
    fun inject(application: ApplicationClass)

}
