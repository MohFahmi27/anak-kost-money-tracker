package com.mohfahmi.moneytracker.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class OnBoardingViewPagerAdapter(
    private val onBoardingScreens: ArrayList<Fragment>,
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = onBoardingScreens.size

    override fun createFragment(position: Int): Fragment = onBoardingScreens[position]

}