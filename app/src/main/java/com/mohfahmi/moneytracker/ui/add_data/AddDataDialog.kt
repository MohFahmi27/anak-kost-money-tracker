package com.mohfahmi.moneytracker.ui.add_data

import android.R.layout.simple_spinner_dropdown_item
import android.R.layout.simple_spinner_item
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.bottomsheetdialogfragment.viewBinding
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mohfahmi.core.domain.models.ActivityDomain
import com.mohfahmi.moneytracker.R
import com.mohfahmi.moneytracker.databinding.DialogBottomSheetAddBinding
import com.mohfahmi.moneytracker.utils.DatePickerFragment
import com.mohfahmi.moneytracker.utils.DatePickerFragment.Companion.BUNDLE_DATE
import com.mohfahmi.moneytracker.utils.DatePickerFragment.Companion.REQUEST_KEY
import com.mohfahmi.moneytracker.view_models.AddDataViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddDataDialog : BottomSheetDialogFragment(), AdapterView.OnItemSelectedListener {

    private val binding: DialogBottomSheetAddBinding by viewBinding()
    private val viewModel: AddDataViewModel by viewModel()
    private val args: AddDataDialogArgs by navArgs()
    private lateinit var activityDomain: ActivityDomain
    private var isExpenses: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializedViews()
        if (args.id == 0L) addBindViews() else editBindViews()
    }

    private fun initializedViews() {
        with(binding) {
            ArrayAdapter.createFromResource(
                requireContext(),
                R.array.activity_categories,
                simple_spinner_item
            ).also { arrayAdapter ->
                arrayAdapter.setDropDownViewResource(simple_spinner_dropdown_item)
                spnCategories.adapter = arrayAdapter
            }
            spnCategories.onItemSelectedListener = this@AddDataDialog

            btnDate.setOnClickListener {
                requireActivity().supportFragmentManager.setFragmentResultListener(
                    REQUEST_KEY, viewLifecycleOwner
                ) { resultKey, bundle ->
                    if (resultKey == REQUEST_KEY) {
                        btnDate.text = bundle.getString(BUNDLE_DATE)
                    }
                }

                DatePickerFragment().show(
                    requireActivity().supportFragmentManager,
                    DatePickerFragment.TAG
                )
            }
        }
    }

    private fun editBindViews() {
        with(binding) {
            textView.text = getString(R.string.edit_activity)
            viewModel.detailActivity(args.id).observe(viewLifecycleOwner) {
                activityDomain = it
                tieActivityName.setText(it.name, TextView.BufferType.SPANNABLE)
                tieAmount.setText(it.amount.toString(), TextView.BufferType.SPANNABLE)
                spnCategories.setSelection(if (it.isExpense) 0 else 1)
                btnDate.text = it.date
            }
            btnAdd.text = getString(R.string.save)
            btnCancel.text = getString(R.string.delete)

            btnAdd.setOnClickListener {
                viewModel.update(
                    args.id,
                    tieActivityName.text.toString(),
                    tieAmount.text.toString().toLong(),
                    btnDate.text.toString(),
                    isExpenses
                )
                findNavController().navigateUp()
            }

            btnCancel.setOnClickListener {
                viewModel.delete(activityDomain)
                findNavController().navigateUp()
            }
        }
    }

    private fun addBindViews() {
        with(binding) {
            btnCancel.setOnClickListener { findNavController().navigateUp() }
            btnAdd.setOnClickListener {
                val activityDomain = ActivityDomain(
                    name = tieActivityName.text.toString(),
                    date = btnDate.text.toString(),
                    isExpense = isExpenses,
                    amount = tieAmount.text.toString().toLong()
                )
                viewModel.insert(activityDomain)
                findNavController().navigateUp()
            }
        }

    }

    override fun onItemSelected(parent: AdapterView<*>, p1: View?, pos: Int, p3: Long) {
        isExpenses = parent.selectedItemPosition == 0
    }

    override fun onNothingSelected(p0: AdapterView<*>?) = Unit
}