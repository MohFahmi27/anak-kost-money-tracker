package com.mohfahmi.moneytracker.ui.splash

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.mohfahmi.moneytracker.R
import com.mohfahmi.moneytracker.databinding.FragmentSplashBinding
import com.mohfahmi.moneytracker.ui.splash.SplashFragmentDirections.actionSplashFragmentToOnBoardingFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashFragment : Fragment(R.layout.fragment_splash) {

    private val binding: FragmentSplashBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            delay(3000L)
            withContext(Dispatchers.Main) {
                findNavController().navigate(actionSplashFragmentToOnBoardingFragment())
            }
        }

    }
}