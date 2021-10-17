package com.jesil.toborowei.newstimes.presentation.utils.adapter.everything_adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.FragmentManager
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jesil.toborowei.newstimes.R
import com.jesil.toborowei.newstimes.data.models.NewsArticles
import com.jesil.toborowei.newstimes.databinding.NewsItemLayoutBinding
import com.jesil.toborowei.newstimes.presentation.utils.NewsDiffUtilCallback
import com.jesil.toborowei.newstimes.presentation.utils.OpenNewsUrl

class EverythingPagingAdapter(
    private val context: Context,
    private val newsUrl: OpenNewsUrl,
) : PagingDataAdapter<NewsArticles, EverythingPagingAdapter.EverythingViewHolder>(NewsDiffUtilCallback<NewsArticles>()){

    inner class EverythingViewHolder(private val binding: NewsItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(newsArticles: NewsArticles) {
            binding.apply {
                Glide.with(itemView)
                    .load(newsArticles.newsUrlToImage)
                    .error(R.drawable.ic_broken_image)
                    .placeholder(R.drawable.ic_placeholder_image)
                    .into(itemNewsImage)

                itemNewsSourceName.text =
                    newsArticles.newsSource?.newsName ?: "The News Times"
                itemNewsTitle.text = newsArticles.newsTitle
                itemNewsContent.text = newsArticles.newsContent
                    ?: "This News does not have a content, please visit the the news source by clicking on Read more"
                itemNewsAuthor.text =
                    if (newsArticles.newsAuthor.isNullOrEmpty()) "Top Headlines" else newsArticles.newsAuthor
                itemReadMoreUrl.setOnClickListener {
                    Log.d("HeadlinesPagingAdapter", "bindData: ${newsArticles.newsUrl}")
                    newsUrl.openNewsUrl(newsArticles.newsUrl)
                }

                itemOptionsMenu.setOnClickListener {
                    PopupMenu(context, binding.itemOptionsMenu, 2).also { popupMenu ->
                        popupMenu.apply {
                            menuInflater.inflate(R.menu.bookmark_menu, popupMenu.menu)
                            show()
                            setOnMenuItemClickListener {
                                when (it.itemId) {
                                    R.id.add_to_bookmark -> {
                                        true
                                    }
                                    R.id.share_news_link->{
                                        newsUrl.shareNewsUrl(newsArticles.newsUrl)
                                        true
                                    }
                                    else -> {

                                        false
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onBindViewHolder(holder: EverythingViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bindData(currentItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EverythingViewHolder {
        return EverythingViewHolder(
            NewsItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

}