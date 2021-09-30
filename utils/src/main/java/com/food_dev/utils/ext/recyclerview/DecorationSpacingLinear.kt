package com.food_dev.utils.ext.recyclerview

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import com.food_dev.utils.R

class DecorationSpacingLinear(context: Context?, @DimenRes dividerResource : Int) : androidx.recyclerview.widget.RecyclerView.ItemDecoration() {

    private var isCircular = false
    constructor(context: Context?, @DimenRes dividerResource : Int, isCircular: Boolean) : this(context, dividerResource){
        this.isCircular = isCircular
    }
    private val dividerSize: Int = context?.resources?.getDimensionPixelSize(dividerResource) ?: 0
    private val dividerVertical: Int = context?.resources?.getDimensionPixelSize(R.dimen.PADDING_DEXTAR) ?: 0
    private val dividerVerticalCircular: Int = context?.resources?.getDimensionPixelSize(R.dimen.PADDING_QUARTER) ?: 0

    override fun getItemOffsets(outRect: Rect, view: View, parent: androidx.recyclerview.widget.RecyclerView, state: androidx.recyclerview.widget.RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        val posIndex    = parent.getChildAdapterPosition(view)
        outRect.top     = dividerVertical
        outRect.bottom  = if (isCircular) dividerVerticalCircular else dividerVertical
        outRect.right   = dividerSize
        if (isCircular) {outRect.left = dividerSize; return}
        if (posIndex == 0) { outRect.left = dividerVertical }
    }
}