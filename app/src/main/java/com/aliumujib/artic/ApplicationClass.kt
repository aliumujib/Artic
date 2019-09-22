package com.aliumujib.artic

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import androidx.multidex.MultiDexApplication
import com.aliumujib.starwars.ui.inject.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import timber.log.Timber
import javax.inject.Inject

class ApplicationClass : MultiDexApplication(), HasActivityInjector , HasSupportFragmentInjector{

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>


    override fun activityInjector(): AndroidInjector<Activity> {
        return activityInjector
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentInjector
    }

    override fun onCreate() {
        super.onCreate()

        DaggerApplicationComponent
            .builder()
            .application(this)
            .build()
            .inject(this)

        initTimber()

    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }

}