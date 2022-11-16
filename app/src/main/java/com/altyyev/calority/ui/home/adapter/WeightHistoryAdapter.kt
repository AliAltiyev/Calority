package com.altyyev.calority.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.altyyev.calority.R
import com.altyyev.calority.base.BaseAdapter
import com.altyyev.calority.domain.uimodel.WeightUiModel

class WeightHistoryAdapter(private val onClick: ((weight: WeightUiModel) -> Unit)) :
    BaseAdapter<WeightUiModel>(
        itemsSame = { old, new -> old.uid == new.uid },
        contentSame = { old, new -> new == old }
    ) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.weight_history_recycler_item, parent, false)
        return WeightHistoryViewHolder(
            view,
            onClick
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is WeightHistoryViewHolder -> {
                holder.bind(getItem(position))
            }
        }
    }
}