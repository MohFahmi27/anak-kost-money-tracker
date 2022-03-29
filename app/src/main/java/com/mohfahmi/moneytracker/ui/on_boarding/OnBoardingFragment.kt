package com.mohfahmi.moneytracker.ui.on_boarding

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.mohfahmi.moneytracker.R
import com.mohfahmi.moneytracker.adapters.ViewPagerAdapter
import com.mohfahmi.moneytracker.databinding.FragmentOnBoardingBinding
import com.mohfahmi.moneytracker.ui.MainActivity
import com.mohfahmi.moneytracker.ui.on_boarding.screen_one.ScreenOneFragment
import com.mohfahmi.moneytracker.ui.on_boarding.screen_two.ScreenTwoFragment
import com.mohfahmi.moneytracker.view_models.OnBoardingViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class OnBoardingFragment : Fragment(R.layout.fragment_on_boarding) {

    private val binding: FragmentOnBoardingBinding by viewBinding()
    private val viewModel: OnBoardingViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindViews()
    }

    private fun bindViews() {
        with(binding) {
            val viewPagerAdapter = ViewPagerAdapter(
                arrayListOf(ScreenOneFragment(), ScreenTwoFragment()),
                requireActivity().supportFragmentManager,
                lifecycle
            )
            vpOnboarding.adapter = viewPagerAdapter
            dotsIndicator.setViewPager2(vpOnboarding)

            tvNext.setOnClickListener { vpOnboarding.currentItem += 1 }
            btnGetStarted.setOnClickListener {
                viewModel.saveState(true)
                requireActivity().startActivity(Intent(requireContext(), MainActivity::class.java))
                requireActivity().finish()
            }

            viewPagerCallback()

        }
    }

    private fun viewPagerCallback() {
        binding.vpOnboarding.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                if (position == binding.vpOnboarding.size) {
                    binding.btnGetStarted.visibility = View.VISIBLE
                    binding.tvNext.visibility = View.INVISIBLE
                } else {
                    binding.tvNext.visibility = View.VISIBLE
                    binding.btnGetStarted.visibility = View.INVISIBLE
                }
            }
        })

    }

}