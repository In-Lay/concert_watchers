package com.inlay.concertswatcher.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.inlay.concertswatcher.R
import com.inlay.concertswatcher.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.topAppBar)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        navController = navHostFragment.navController

        supportActionBar?.title = "Home"

        addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.top_app_bar, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                navController.navigate(R.id.action_main_to_searchFragment)
                supportActionBar?.title = "Search"
                menuItem.isVisible = false
                menuItem.isEnabled = false
                return true
            }
        })

        handleUIComponents()
    }

    private fun handleUIComponents() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNavigationView.setupWithNavController(navController)
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.main -> {
                    navController.popBackStack(R.id.main, false)
                    navController.navigate(R.id.main)
                    supportActionBar?.title = "Home"
                    binding.topAppBar.menu.findItem(R.id.search).isVisible = true
                }
                R.id.favourites -> {
                    navController.popBackStack(R.id.favourites, true)
                    navController.navigate(R.id.favourites)
                    supportActionBar?.title = "Favourite"
                    binding.topAppBar.menu.findItem(R.id.search).isVisible = false
                }
                R.id.login -> {
                    navController.popBackStack(R.id.login, true)
                    navController.navigate(R.id.login)
                    supportActionBar?.title = "Profile"
                    binding.topAppBar.menu.findItem(R.id.search).isVisible = false
                }
            }
            true
        }
    }
}