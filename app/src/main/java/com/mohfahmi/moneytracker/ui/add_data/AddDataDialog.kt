package com.mohfahmi.moneytracker.ui.add_data

import android.R.layout.simple_spinner_dropdown_item
import android.R.layout.simple_spinner_item
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.bottomsheetdialogfragment.viewBinding
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mohfahmi.moneytracker.R
import com.mohfahmi.moneytracker.databinding.DialogBottomSheetAddBinding
import com.mohfahmi.moneytracker.utils.DatePickerFragment
import com.mohfahmi.moneytracker.utils.DatePickerFragment.Companion.BUNDLE_DATE
import com.mohfahmi.moneytracker.utils.DatePickerFragment.Companion.REQUEST_KEY

class AddDataDialog : BottomSheetDialogFragment() {

    private val binding: DialogBottomSheetAddBinding by viewBinding()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindViews()
    }

    private fun bindViews() {
        with(binding) {
            btnCancel.setOnClickListener { findNavController().navigateUp() }

            ArrayAdapter.createFromResource(
                requireContext(),
                R.array.activity_categories,
                simple_spinner_item
            ).also { arrayAdapter ->
                arrayAdapter.setDropDownViewResource(simple_spinner_dropdown_item)
                spnCategories.adapter = arrayAdapter
            }

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

}