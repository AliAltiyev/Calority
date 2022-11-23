package com.altyyev.calority.ui.add

import com.altyyev.calority.ui.emoji.EmojiFragment
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.altyyev.calority.R
import com.altyyev.calority.databinding.FragmentAddWeightBinding
import com.altyyev.calority.domain.uimodel.WeightUiModel
import com.altyyev.calority.utils.*
import com.altyyev.calority.utils.Constants.CURRENT_DATE_FORMAT
import com.altyyev.calority.utils.Constants.TAG_DATE_PICKER
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class AddWeightFragment : BottomSheetDialogFragment(R.layout.fragment_add_weight) {

    var emoji: String = String.EMPTY

    private val viewModel: AddWeightViewModel by viewModels()

    private var selectedDate = Date()

    private val binding by viewBinding(FragmentAddWeightBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeSingleLiveEvent()
        viewModel.fetchWeightByDate(selectedDate)
    }

    private fun initViews() = with(binding) {
        emojiButton.setOnClickListener {
            findNavController().navigate(R.id.action_addWeightFragment_to_emojiFragment)
        }
        btnSaveOrUpdateWeight.setOnClickListener {
            val weight = inputWeightTxt.text.toString()
            val note = inputNoteTxt.text.toString()
            viewModel.insertOrUpdateWeight(
                weight = weight,
                note = note,
                timeStamp = selectedDate,
                emoji
            )
        }

        prevDay.setOnClickListener {
            fetchDate(date = selectedDate.previousDay())

        }

        nextDay.setOnClickListener {
            fetchDate(date = selectedDate.nextDay())
        }

        btnSelectDate.setOnClickListener {
            val datePicker =
                MaterialDatePicker.Builder.datePicker()
                    .setTitleText(getString(R.string.select_date))
                    .setSelection(selectedDate.time)
                    .build()
            datePicker.addOnPositiveButtonClickListener { timestamp ->
                fetchDate(Date(timestamp))
            }
            datePicker.show(parentFragmentManager, TAG_DATE_PICKER)
        }
        btnSelectDate.text = selectedDate.toFormat(CURRENT_DATE_FORMAT)
    }

    private fun fetchDate(date: Date) {
        selectedDate = date
        binding.btnSelectDate.text = selectedDate.toFormat(CURRENT_DATE_FORMAT)
        viewModel.fetchWeightByDate(selectedDate)
    }


    private fun observeSingleLiveEvent() {
        lifecycleScope.launchWhenStarted {
            viewModel.eventsFlow
                .collect {
                    when (it) {
                        is AddWeightViewModel.Event.Toast -> {
                            context.showToast(R.string.empty_weight)
                        }
                        is AddWeightViewModel.Event.PopBackStack -> {
                            dismiss()
                        }
                    }
                }
        }

        lifecycleScope.launchWhenCreated {
            viewModel.uiState.collect(
                ::setUiState
            )
        }
        setFragmentResultListener(EmojiFragment.KEY_REQUEST_EMOJI) { _, bundle ->
            emoji = bundle.getString(EmojiFragment.KEY_BUNDLE_EMOJI).orEmpty()
            binding.emojiButton.text = emoji
        }

    }

    private fun setUiState(uiState: AddWeightViewModel.UiState) = with(binding) {
        val weight = uiState.currentWeight

        inputNoteTxt.setText(weight?.note.orEmpty())
        inputWeightTxt.setText(weight?.weight.orEmpty())

        if (weight != null) {
            //Update weight
            btnSaveOrUpdateWeight.run {
                setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.Spearmint
                    )
                )
                setText(R.string.Update)
                icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_update)
                setIconTintResource(R.color.white)
            }
        } else {
            //save weight
            btnSaveOrUpdateWeight.run {
                setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.Hot_Pink
                    )
                )
                icon = ContextCompat.getDrawable(requireContext(), R.drawable.icon_add)
                setIconTintResource(R.color.white)
                setText(R.string.Save)
            }
        }
        setEmojiButtonState(weight = weight)
    }

    private fun setEmojiButtonState(weight: WeightUiModel?) = with(binding) {
        if (weight == null) {
            emojiButton.text = getString(R.string.selectEmoji)
        } else {
            binding.emojiButton.text = getString(
                R.string.select_emoji_with_emoji_format,
                weight.emoji
            )
        }
    }
}