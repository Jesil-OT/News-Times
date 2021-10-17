package com.jesil.toborowei.newstimes.presentation.utils.adapter.categories_adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jesil.toborowei.newstimes.presentation.fragments.categories.business.BusinessFragment
import com.jesil.toborowei.newstimes.presentation.fragments.categories.entertainment.EntertainmentFragment
import com.jesil.toborowei.newstimes.presentation.fragments.categories.general.GeneralFragment
import com.jesil.toborowei.newstimes.presentation.fragments.categories.health.HealthFragment
import com.jesil.toborowei.newstimes.presentation.fragments.categories.science.ScienceFragment
import com.jesil.toborowei.newstimes.presentation.fragments.categories.sports.SportsFragment
import com.jesil.toborowei.newstimes.presentation.fragments.categories.technology.TechnologyFragment

class CategoriesViewPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> BusinessFragment()
            1 -> EntertainmentFragment()
            2 -> GeneralFragment()
            3 -> HealthFragment()
            4 -> ScienceFragment()
            5 -> SportsFragment()
            6 -> TechnologyFragment()
            else -> BusinessFragment()
        }
    }

    override fun getItemCount(): Int = 7
}