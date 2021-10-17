package com.jesil.toborowei.newstimes.presentation.utils.adapter.headlines_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jesil.toborowei.newstimes.databinding.NewsErrorHeaderFooterLayoutBinding

class NewsErrorHeaderFooterAdapter (private val retry: () -> Unit)
    : LoadStateAdapter<NewsErrorHeaderFooterAdapter.NewsErrorHeaderFooterViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): NewsErrorHeaderFooterViewHolder {
        return NewsErrorHeaderFooterViewHolder(
            NewsErrorHeaderFooterLayoutBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: NewsErrorHeaderFooterViewHolder, loadState: LoadState) {
        holder.bind(loadState = loadState)
    }

    inner class  NewsErrorHeaderFooterViewHolder(
        private val binding: NewsErrorHeaderFooterLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.newsErrorHeaderFooterRetry.setOnClickListener {
                retry.invoke()
            }
        }

        fun bind(loadState: LoadState) {
            binding.apply {
                newsErrorHeaderFooterProgressBar.isVisible = loadState is LoadState.Loading
                newsErrorHeaderFooterGroup.isVisible = loadState !is LoadState.Loading
            }
        }
    }
}