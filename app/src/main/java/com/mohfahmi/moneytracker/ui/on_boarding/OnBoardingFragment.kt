package com.mohfahmi.moneytracker.ui.on_boarding

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.mohfahmi.moneytracker.R
import com.mohfahmi.moneytracker.adapters.OnBoardingViewPagerAdapter
import com.mohfahmi.moneytracker.databinding.FragmentOnBoardingBinding
import com.mohfahmi.moneytracker.ui.on_boarding.screen_one.ScreenOneFragment
import com.mohfahmi.moneytracker.ui.on_boarding.screen_two.ScreenTwoFragment

class OnBoardingFragment : Fragment(R.layout.fragment_on_boarding) {

    private val binding: FragmentOnBoardingBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindViews()
    }

    private fun bindViews() {
        with(binding) {
            val viewPagerAdapter = OnBoardingViewPagerAdapter(
                arrayListOf(ScreenOneFragment(), ScreenTwoFragment()),
                requireActivity().supportFragmentManager,
                lifecycle
            )
            vpOnboarding.adapter = viewPagerAdapter
            dotsIndicator.setViewPager2(vpOnboarding)

            tvNext.setOnClickListener { vpOnboarding.currentItem += 1 }

            vpOnboarding.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                    if (position == vpOnboarding.size) {
                        btnGetStarted.visibility = View.VISIBLE
                        tvNext.visibility = View.INVISIBLE
                    } else {
                        tvNext.visibility = View.VISIBLE
                        btnGetStarted.visibility = View.INVISIBLE
                    }
                }
            })
        }
    }

}