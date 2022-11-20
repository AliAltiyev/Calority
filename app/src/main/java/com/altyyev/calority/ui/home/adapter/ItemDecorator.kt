package com.altyyev.calority.ui.home.adapter

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.altyyev.calority.utils.dpToPx

private const val ITEM_HORIZONTAL_NARGIN = 16
private const val ITEM_VERTICAL_MARGIN = 8

class ItemDecorator(context: Context) : RecyclerView.ItemDecoration() {


    private val marginHorizontal by lazy {
        ITEM_HORIZONTAL_NARGIN.dpToPx(context)
    }

    private val marginVertical by lazy {
        ITEM_VERTICAL_MARGIN.dpToPx(context)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.top = marginVertical
        outRect.bottom = marginVertical
        outRect.right = marginHorizontal
        outRect.left = marginHorizontal

    }

}