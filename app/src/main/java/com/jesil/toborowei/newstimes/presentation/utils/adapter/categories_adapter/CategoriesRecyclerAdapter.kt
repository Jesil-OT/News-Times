package com.jesil.toborowei.newstimes.presentation.utils.adapter.categories_adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jesil.toborowei.newstimes.R
import com.jesil.toborowei.newstimes.data.models.NewsArticles
import com.jesil.toborowei.newstimes.databinding.CategoriesNewsItemBinding
import com.jesil.toborowei.newstimes.presentation.utils.NewsDiffUtilCallback

class CategoriesRecyclerAdapter(
    val context: Context
): ListAdapter<NewsArticles, CategoriesRecyclerAdapter.CategoriesViewHolder>(NewsDiffUtilCallback<NewsArticles>()){

    inner class CategoriesViewHolder(private val binding: CategoriesNewsItemBinding):
        RecyclerView.ViewHolder(binding.root){

        fun bindData(newsArticles: NewsArticles) {
            binding.apply {
                Glide.with(itemView)
                    .load(newsArticles.newsUrlToImage)
                    .error(R.drawable.ic_broken_image)
                    .placeholder(R.drawable.ic_placeholder_image)
                    .into(categoriesNewsImage)

                categoriesNewsSourceName.text =
                    newsArticles.newsSource?.newsName ?: "The News Times"
                categoriesNewsTitle.text = newsArticles.newsTitle
                categoriesNewsAuthor.text =
                    if (newsArticles.newsAuthor.isNullOrEmpty()) "Top Headlines" else newsArticles.newsAuthor
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        return CategoriesViewHolder(
            CategoriesNewsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bindData(currentItem)
        }
    }
}