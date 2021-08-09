package com.decadev.autocheck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.decadev.autocheck.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val navController = findNavController(R.id.fragmentContainerView)
        binding.bottomNavView.menu.getItem(2).isEnabled = false
        binding.bottomNavView.menu.getItem(1).isEnabled = false

        binding.bottomNavView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id) {
                R.id.productFragment -> hideBottomNav()
                else -> showBottomNav()
            }
        }

    }

    private fun hideBottomNav() {
        binding.bottomNavView.visibility = View.GONE
        binding.homeFloatActionBar.visibility = View.GONE
    }

    private fun showBottomNav() {
        binding.bottomNavView.visibility = View.VISIBLE
        binding.homeFloatActionBar.visibility = View.VISIBLE
    }
}