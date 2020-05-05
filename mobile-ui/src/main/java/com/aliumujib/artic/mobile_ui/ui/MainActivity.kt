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
package com.aliumujib.artic.mobile_ui.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.ui.setupActionBarWithNavController
import com.aliumujib.artic.mobile_ui.R
import com.aliumujib.artic.mobile_ui.utils.setupWithNavController
import com.aliumujib.artic.views.ext.slideDown
import com.aliumujib.artic.views.ext.slideUp
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber


class MainActivity : AppCompatActivity() {

    private var currentNavController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        } // Else, need to wait for onRestoreInstanceState

        setUpNavDestinationChangeListener()
    }

    private fun setUpNavDestinationChangeListener() {
        val destinationChangeListener = DestinationChangeListener()
        currentNavController?.observe(this) {
            it.addOnDestinationChangedListener(destinationChangeListener)
        }
    }

    inner class DestinationChangeListener() : NavController.OnDestinationChangedListener {
        override fun onDestinationChanged(controller: NavController, destination: NavDestination, arguments: Bundle?) {
            findViewById<Toolbar>(R.id.toolbar).title = destination.label
            findViewById<CollapsingToolbarLayout>(R.id.collasping_toolbar).isTitleEnabled = false;
            findViewById<CollapsingToolbarLayout>(R.id.collasping_toolbar).title = destination.label
            if (destination.id == R.id.detailsFragment) {
                findViewById<AppBarLayout>(R.id.app_bar).setExpanded(false)
                hideBottomTabs()
            } else {
                showBottomTabs()
            }
        }
    }


    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setupBottomNavigationBar()
    }

    private fun setupBottomNavigationBar() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)

        val navGraphIds = listOf(
            R.navigation.nav_home,
            R.navigation.nav_category,
            R.navigation.nav_bookmarks,
            R.navigation.nav_settings
        )


        val controller = bottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.mainHostFragment,
            intent = intent
        )



        controller.observe(this, Observer { navController ->
            setupActionBarWithNavController(navController)
        })

        currentNavController = controller
    }


    private fun hideBottomTabs() {
        if (findViewById<BottomNavigationView>(R.id.bottom_nav).visibility == View.VISIBLE) {
            findViewById<BottomNavigationView>(R.id.bottom_nav).slideDown()
        }
    }

    private fun showBottomTabs() {
        if (findViewById<BottomNavigationView>(R.id.bottom_nav).visibility != View.VISIBLE) {
            findViewById<BottomNavigationView>(R.id.bottom_nav).slideUp()
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }
}
