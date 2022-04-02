package com.mohfahmi.moneytracker.ui.profile

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.work.Data
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.mohfahmi.moneytracker.R
import com.mohfahmi.moneytracker.databinding.FragmentProfileBinding
import com.mohfahmi.moneytracker.notification.NotificationWorker
import com.mohfahmi.moneytracker.ui.profile.ProfileFragmentDirections.actionProfileFragmentToEditNameDialog2
import com.mohfahmi.moneytracker.view_models.ProfileViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val binding: FragmentProfileBinding by viewBinding()
    private val viewModel: ProfileViewModel by viewModel()
    private val workManager by lazy {
        WorkManager.getInstance(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initiateView()
    }

    private fun initiateView() {
        with(binding) {
            viewModel.getUserName.observe(viewLifecycleOwner) {
                tvHelloName.text = it
            }
            viewModel.getNotificationState.observe(viewLifecycleOwner) { notificationState ->
                swNotification.isChecked = notificationState
            }
            swNotification.setOnCheckedChangeListener { _, notificationState ->
                viewModel.setNotificationState(notificationState)
                if (notificationState) {
                    val dataInputWorker = Data.Builder()
                        .putBoolean(NotificationWorker.NOTIFICATION_STATE, notificationState)
                        .putString(NotificationWorker.USERNAME, tvHelloName.text.toString())
                        .build()
                    val request = PeriodicWorkRequest.Builder(
                        NotificationWorker::class.java,
                        1,
                        TimeUnit.DAYS
                    )
                        .addTag(getString(R.string.work_tag))
                        .setInputData(dataInputWorker)
                        .build()
                    workManager.enqueueUniquePeriodicWork(
                        getString(R.string.work_tag),
                        ExistingPeriodicWorkPolicy.KEEP,
                        request
                    )
                } else {
                    workManager.cancelAllWorkByTag(getString(R.string.work_tag))
                }
            }
            view3.setOnClickListener {
                findNavController().navigate(
                    actionProfileFragmentToEditNameDialog2()
                )
            }
            view2.setOnClickListener {
                swNotification.isChecked = !false
            }
        }
    }

}