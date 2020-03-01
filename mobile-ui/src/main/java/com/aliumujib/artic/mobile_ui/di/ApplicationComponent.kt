package com.aliumujib.artic.mobile_ui.di


import com.aliumujib.artic.mobile_ui.ApplicationClass
import com.aliumujib.artic.ui.inject.components.CoreComponent
import com.aliumujib.artic.ui.inject.scopes.AppScope
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