package com.aliumujib.artic.mobile_ui.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.aliumujib.artic.mobile_ui.R
import com.aliumujib.artic.mobile_ui.utils.setupWithNavController
import com.aliumujib.artic.views.ext.slideDown
import com.aliumujib.artic.views.ext.slideUp
import com.google.android.material.bottomnavigation.BottomNavigationView


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

        currentNavController?.value?.addOnDestinationChangedListener { _, destination, _ ->

            if (destination.id == R.id.detailsFragment) {
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
