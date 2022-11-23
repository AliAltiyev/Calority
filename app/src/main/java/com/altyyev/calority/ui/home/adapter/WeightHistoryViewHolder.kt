package com.altyyev.calority.ui.home.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.altyyev.calority.R
import com.altyyev.calority.databinding.WeightHistoryRecyclerItemBinding
import com.altyyev.calority.domain.uimodel.WeightUiModel
import com.altyyev.calority.utils.Constants.CURRENT_DATE_FORMAT
import com.altyyev.calority.utils.toFormat

class WeightHistoryViewHolder(
    view: View,
    private val onClickWeight: ((weight: WeightUiModel) -> Unit)
) : RecyclerView.ViewHolder(view) {

    private val binding = WeightHistoryRecyclerItemBinding.bind(view)

    fun bind(weightUiModel: WeightUiModel) = with(binding) {
        itemView.setOnClickListener {
            onClickWeight.invoke(weightUiModel)
        }
        noteTxt.text = weightUiModel.note
        weightTxt.text = itemView.context.getString(R.string.kg, weightUiModel.weight)
        date.text = weightUiModel.timeStamp?.toFormat(CURRENT_DATE_FORMAT)
        emojiTxt.text = weightUiModel.emoji.toString()
    }
}