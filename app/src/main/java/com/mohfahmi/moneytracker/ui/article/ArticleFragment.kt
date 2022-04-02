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
                tvIncomePercentage.text = getString(R.string.money_percentage_income, it ?: 0f)
            }
            viewModel.getPercentageExpense.observe(viewLifecycleOwner) { expenseResult ->
                tvExpensesPercentage.text =
                    getString(R.string.money_percentage_expense, expenseResult ?: 0f)
                statusUser(expenseResult.roundToInt())
                pieChart.progress = expenseResult.roundToInt()
            }
        }
    }

    private fun statusUser(expense: Int) {
        with(binding) {
            if (expense > 50) {
                tvStatus.text = getString(R.string.yout_did_what)
                tvDescription.text = getString(R.string.text_description_failed)
                imgStatus.setImageResource(R.drawable.ic_expense_large)
            } else {
                tvStatus.text = getString(R.string.you_did_a_good_job)
                tvDescription.text = getString(R.string.text_description_success)
                imgStatus.setImageResource(R.drawable.ic_expense_lower)
            }
        }
    }
}