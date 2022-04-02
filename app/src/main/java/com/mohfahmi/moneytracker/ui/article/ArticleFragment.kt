package com.mohfahmi.moneytracker.ui.article

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import com.mohfahmi.moneytracker.R
import com.mohfahmi.moneytracker.databinding.FragmentArticleBinding
import com.mohfahmi.moneytracker.view_models.ArticleViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.roundToInt

class ArticleFragment : Fragment(R.layout.fragment_article) {
    private val binding: FragmentArticleBinding by viewBinding()
    private val viewModel: ArticleViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        populateViews()
    }

    private fun populateViews() {
        with(binding) {
            viewModel.getPercentageIncome.observe(viewLifecycleOwner) {
                tvIncomePercentage.text = getString(R.string.money_percentage_income, it)
            }
            viewModel.getPercentageExpense.observe(viewLifecycleOwner) {
                tvExpensesPercentage.text = getString(R.string.money_percentage_expense, it)
                pieChart.progress = it.roundToInt()
            }
        }
    }
}