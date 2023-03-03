package com.inlay.concertswatcher.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.view.MenuProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.inlay.concertswatcher.R
import com.inlay.concertswatcher.databinding.ActivityMainBinding
import com.inlay.login.di.getOrCreateLoginScope
import com.inlay.login.di.getOrCreateProfileScope
import com.inlay.login.presentation.viewModel.LoginViewModel
import com.inlay.login.presentation.viewModel.profile.ProfileViewModel
import org.koin.androidx.scope.ScopeActivity

class MainActivity : ScopeActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    private val loginViewModel: LoginViewModel = getOrCreateLoginScope().get()
    private val profileViewModel: ProfileViewModel = getOrCreateProfileScope().get()

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

        val currentUser = Firebase.auth.currentUser

        if (currentUser != null) {
            handleUIComponents(true)
        } else handleUIComponents()

        loginViewModel.userStateFlag.observe(this) {
            handleUIComponents(it)
            navController.navigate(R.id.profile)
        }

        profileViewModel.userProfileStateFlag.observe(this) {
            handleUIComponents(it)
            navController.navigate(R.id.login)
        }
    }

    private fun handleUIComponents(isUserLogged: Boolean = false) {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNavigationView.setupWithNavController(navController)
        bottomNavigationView.setOnItemSelectedListener {

            when (it.itemId) {
                R.id.main -> {
                    if (!it.isChecked) {
                        navController.popBackStack(R.id.main, false)
                        navController.navigate(R.id.main)
                        supportActionBar?.title = "Home"
                        binding.topAppBar.menu.findItem(R.id.search).isVisible = true
                    }
                }
                R.id.favourites -> {
                    if (!it.isChecked) {
                        navController.popBackStack(R.id.favourites, true)
                        navController.navigate(R.id.favourites)
                        supportActionBar?.title = "Favourite"
                        binding.topAppBar.menu.findItem(R.id.search).isVisible = false
                    }
                }
                R.id.login -> {
                    if (!it.isChecked) {
                        if (isUserLogged) {
                            navController.popBackStack(R.id.profile, true)
                            navController.navigate(R.id.profile)
                            supportActionBar?.title = "Profile"
                        } else {
                            navController.popBackStack(R.id.login, true)
                            navController.navigate(R.id.login)
                            supportActionBar?.title = "Login"
                        }
                        binding.topAppBar.menu.findItem(R.id.search).isVisible = false
                    }
                }
            }
            true
        }
    }
}