package com.mohfahmi.moneytracker.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.bottomsheetdialogfragment.viewBinding
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mohfahmi.moneytracker.databinding.DialogBottomSheetEditProfileBinding
import com.mohfahmi.moneytracker.view_models.EditNameViewModel
import com.shashank.sony.fancytoastlib.FancyToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditNameDialog : BottomSheetDialogFragment() {

    private val binding: DialogBottomSheetEditProfileBinding by viewBinding()
    private val viewModel: EditNameViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initiateViews()
    }

    private fun initiateViews() {
        with(binding) {
            btnSave.setOnClickListener {
                if (tieNameWelcome.text.toString().isNotEmpty()) {
                    viewModel.edtName(tieNameWelcome.text.toString())
                    findNavController().navigateUp()
                } else {
                    FancyToast.makeText(
                        requireContext(),
                        "Input Cannot be Empty",
                        FancyToast.LENGTH_SHORT,
                        FancyToast.DEFAULT,
                        false
                    ).show()
                }
            }
        }
    }
}