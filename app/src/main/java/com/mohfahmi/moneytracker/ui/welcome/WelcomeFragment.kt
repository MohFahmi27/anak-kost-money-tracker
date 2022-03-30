package com.mohfahmi.moneytracker.ui.welcome

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.mohfahmi.moneytracker.R
import com.mohfahmi.moneytracker.databinding.FragmentWelcomeBinding
import com.mohfahmi.moneytracker.ui.MainActivity
import com.mohfahmi.moneytracker.view_models.OnBoardingViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class WelcomeFragment : Fragment(R.layout.fragment_welcome) {

    private val binding: FragmentWelcomeBinding by viewBinding()
    private val viewModel: OnBoardingViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindViews()
    }

    private fun bindViews() {
        with(binding) {
            btnLetsGo.setOnClickListener {
                if (tieNameWelcome.text.toString().isNotEmpty()) {
                    viewModel.saveUserName(tieNameWelcome.text.toString())
                    requireActivity().startActivity(
                        Intent(
                            requireContext(),
                            MainActivity::class.java
                        )
                    )
                    requireActivity().finish()
                } else {
                    Toast.makeText(requireContext(), "Input Your Name First", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

}