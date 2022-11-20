package com.altyyev.calority.ui.add

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.altyyev.calority.R
import com.altyyev.calority.databinding.FragmentAddWeightBinding
import com.altyyev.calority.utils.toFormat
import com.altyyev.calority.utils.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

const val CURRENT_DATE_FORMAT = "dd MMM yyyy"
const val TAG_DATE_PICKER = "Tag_Date_Picker"

@AndroidEntryPoint
class AddWeightFragment : BottomSheetDialogFragment(R.layout.fragment_add_weight) {

    private val viewModel: AddWeightViewModel by viewModels()

    private var selectedDate = Date()

    private val binding by viewBinding(FragmentAddWeightBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeSingleLiveEvent()
    }

    private fun initViews() = with(binding) {
        btnSelectDate.setOnClickListener {
            val datePicker =
                MaterialDatePicker.Builder.datePicker()
                    .setTitleText(getString(R.string.select_date))
                    .setSelection(selectedDate.time)
                    .build()
            datePicker.addOnPositiveButtonClickListener { timestamp ->
                selectedDate = Date(timestamp)
                btnSelectDate.text = selectedDate.toFormat(CURRENT_DATE_FORMAT)
            }
            datePicker.show(parentFragmentManager, TAG_DATE_PICKER)
        }
        btnSelectDate.text = selectedDate.toFormat(CURRENT_DATE_FORMAT)
        btnSaveWeight.isClickable = false
        btnSaveWeight.setOnClickListener {
            val weight = inputWeightTxt.text.toString()
            val note = inputNoteTxt.text.toString()
            viewModel.insertWeight(weight = weight, note = note, timeStamp = selectedDate)
        }
    }

    private fun observeSingleLiveEvent() {
        lifecycleScope.launchWhenStarted {
            viewModel.eventsFlow
                .collect {
                    when (it) {
                        is AddWeightViewModel.Event.Toast -> {
                            Toast.makeText(context, R.string.empty_weight, Toast.LENGTH_SHORT)
                                .show()
                        }
                        is AddWeightViewModel.Event.PopBackStack -> {
                            dismiss()
                        }
                    }
                }
        }
    }

}