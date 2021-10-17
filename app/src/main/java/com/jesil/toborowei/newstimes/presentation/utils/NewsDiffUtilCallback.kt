package com.jesil.toborowei.newstimes.presentation.utils

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.jesil.toborowei.newstimes.data.models.NewsArticles

class NewsDiffUtilCallback<T> : DiffUtil.ItemCallback<T>() {
     override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
         return oldItem == newItem
     }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
         return oldItem == newItem
     }
}

