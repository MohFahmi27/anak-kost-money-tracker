package com.mohfahmi.moneytracker.ui.home

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.mohfahmi.moneytracker.R
import com.mohfahmi.moneytracker.adapters.ActivityListAdapter
import com.mohfahmi.moneytracker.databinding.FragmentHomeBinding
import com.mohfahmi.moneytracker.ui.home.HomeFragmentDirections.actionHomeFragmentToAddDataDialog
import com.mohfahmi.moneytracker.view_models.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding: FragmentHomeBinding by viewBinding()
    private val viewModel: HomeViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindViews()
    }

    private fun bindViews() {
        with(binding) {
            viewModel.getUserName.observe(viewLifecycleOwner) { userName ->
                tvHelloName.text = resources.getString(R.string.home, userName)
            }
            viewModel.getExpenses.observe(viewLifecycleOwner) { expenses ->
                tvExpensesMain.text = getString(R.string.rp_placeholder, expenses)
            }
            viewModel.getIncome.observe(viewLifecycleOwner) { income ->
                tvIncomeMain.text = getString(R.string.rp_placeholder, income)
            }
            val adapter = ActivityListAdapter { activityDomain ->
                findNavController().navigate(
                    actionHomeFragmentToAddDataDialog(
                        activityDomain.id
                    )
                )
            }

            viewModel.getAllActivityData.observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
            rvLatestActivity.adapter = adapter
            fabAdd.setOnClickListener {
                findNavController().navigate(actionHomeFragmentToAddDataDialog(0L))
            }
        }
    }
}