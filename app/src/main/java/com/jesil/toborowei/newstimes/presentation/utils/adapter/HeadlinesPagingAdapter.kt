package com.jesil.toborowei.newstimes.presentation.utils.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jesil.toborowei.newstimes.R
import com.jesil.toborowei.newstimes.data.models.NewsArticles
import com.jesil.toborowei.newstimes.databinding.HeadlinesItemLayoutBinding

class HeadlinesPagingAdapter() : PagingDataAdapter<NewsArticles, HeadlinesPagingAdapter.HeadlinesViewHolder>(HeadlinesDiffutil()) {

    class HeadlinesViewHolder(private val binding: HeadlinesItemLayoutBinding) :
            RecyclerView.ViewHolder(binding.root){

                fun bindData(newsArticles: NewsArticles){
                    binding.apply {
                        Glide.with(itemView)
                            .load(newsArticles.newsUrlToImage)
                            .error(R.drawable.ic_broken_image)
                            .into(headlinesItemNewsImage)

                        headlinesItemNewsSourceName.text = newsArticles.newsSource?.newsName ?: "The News Times"
                        headlinesItemNewsTitle.text = newsArticles.newsTitle
                        headlinesItemNewsContent.text = newsArticles.newsContent ?: "This News does not have a content, please visit the the news source by clicking on Read more"
                        headlinesItemNewsAuthor.text = newsArticles.newsAuthor ?: "Headlines"
                    }
                }
            }

    class HeadlinesDiffutil : DiffUtil.ItemCallback<NewsArticles>(){
        override fun areItemsTheSame(oldItem: NewsArticles, newItem: NewsArticles): Boolean {
            return oldItem.newsContent == newItem.newsContent
        }

        override fun areContentsTheSame(oldItem: NewsArticles, newItem: NewsArticles): Boolean {
            return oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: HeadlinesViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null){
            holder.bindData(currentItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeadlinesViewHolder {
        return HeadlinesViewHolder(HeadlinesItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }


}