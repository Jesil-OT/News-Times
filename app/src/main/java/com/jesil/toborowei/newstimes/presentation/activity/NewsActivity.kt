package com.jesil.toborowei.newstimes.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Transition
import android.view.Gravity
import android.view.View
import androidx.core.view.isVisible
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
        }

        supportFragmentManager.registerFragmentLifecycleCallbacks(object : FragmentManager.FragmentLifecycleCallbacks() {
            override fun onFragmentViewCreated(
                fm: FragmentManager,
                f: Fragment,
                v: View,
                savedInstanceState: Bundle?
            ) {
                TransitionManager.beginDelayedTransition(binding.root, Slide(Gravity.BOTTOM).excludeTarget(R.id.newsFragmentContainerView, true))
                when(f){
                    is HeadlinesFragment -> {binding.newsBottomNavigation.visibility = View.GONE}
                    else -> {binding.newsBottomNavigation.visibility = View.VISIBLE}
                }
            }
        }, true)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}