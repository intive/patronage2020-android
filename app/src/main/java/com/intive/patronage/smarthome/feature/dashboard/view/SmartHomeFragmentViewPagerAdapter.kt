package com.intive.patronage.smarthome.feature.dashboard.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.intive.patronage.smarthome.feature.home.view.HomeFragment
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class SmartHomeFragmentViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    private val numberTabsInTabLayout = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> DashboardFragment()
            else -> HomeFragment()
        }
    }

    override fun getItemCount() = numberTabsInTabLayout
}