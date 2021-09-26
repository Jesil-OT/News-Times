package com.jesil.toborowei.newstimes.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.transition.Slide
import androidx.transition.TransitionManager
import com.jesil.toborowei.newstimes.R
import com.jesil.toborowei.newstimes.databinding.ActivityNewsBinding
import com.jesil.toborowei.newstimes.presentation.fragments.headlines.HeadlinesFragment
import com.jesil.toborowei.newstimes.presentation.utils.hideView
import com.jesil.toborowei.newstimes.presentation.utils.showView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private var _binding: ActivityNewsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.headerSection.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.newsFragmentContainerView) as NavHostFragment
        navController = navHostFragment.findNavController()
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.home, R.id.categories, R.id.search, R.id.bookmarks)
        )
        binding.apply {
            headerSection.root.setupWithNavController(navController, appBarConfiguration)
            newsBottomNavigation.setupWithNavController(navController)
            newsBottomNavigation.setOnItemReselectedListener {  }
            newsBottomNavigation.getOrCreateBadge(R.id.home).clearNumber()
        }
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when(destination.id){
                R.id.headlinesFragment -> hideBottomNavigation()
                else -> showBottomNavigation()
            }
        }
    }

    private fun showBottomNavigation() = with(binding){
        newsBottomNavigation.showView()

    }

    private fun hideBottomNavigation() = with(binding){
        TransitionManager.beginDelayedTransition(binding.root, Slide(Gravity.BOTTOM).excludeTarget(R.id.newsFragmentContainerView, true))
        newsBottomNavigation.hideView()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

