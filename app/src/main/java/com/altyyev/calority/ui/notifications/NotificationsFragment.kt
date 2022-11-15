package com.altyyev.calority.ui.notifications

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.altyyev.calority.R
import com.altyyev.calority.databinding.FragmentNotificationsBinding
import com.altyyev.calority.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationsFragment : Fragment(R.layout.fragment_notifications) {

    val binding by viewBinding(FragmentNotificationsBinding::bind)

    val viewModel: NotificationsViewModel by viewModels()

}