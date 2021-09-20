package com.jesil.toborowei.newstimes.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.jesil.toborowei.newstimes.R
import com.jesil.toborowei.newstimes.databinding.ActivityNewsBinding

class NewsActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private var _binding: ActivityNewsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.newsFragmentContainerView) as NavHostFragment
        navController = navHostFragment.findNavController()
        binding.apply {
            newsBottomNavigation.setupWithNavController(navController)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}