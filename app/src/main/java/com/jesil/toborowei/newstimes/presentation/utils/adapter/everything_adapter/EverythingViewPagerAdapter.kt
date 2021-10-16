package com.jesil.toborowei.newstimes.presentation.utils.adapter.everything_adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.jesil.toborowei.newstimes.R
import com.jesil.toborowei.newstimes.data.models.NewsArticles
import com.jesil.toborowei.newstimes.databinding.EverythingNewsViewPagerItemLayoutBinding
import com.jesil.toborowei.newstimes.presentation.utils.OpenNewsUrl

class EverythingViewPagerAdapter(
    private val context: Context,
    private val newsArticlesItems: List<NewsArticles>,
    private val newsUrl: OpenNewsUrl
) : PagerAdapter() {
    override fun getCount() = newsArticlesItems.size

    override fun isViewFromObject(view: View, `object`: Any) = view == `object`

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding = EverythingNewsViewPagerItemLayoutBinding.inflate(
            LayoutInflater.from(context),
            container,
            false
        )

        binding.apply {
            Glide.with(context)
                .load(newsArticlesItems[position].newsUrlToImage)
                .placeholder(R.drawable.ic_placeholder_image)
                .error(R.drawable.ic_broken_image)
                .into(everythingNewsViewPagerNewsImage)
            everythingNewsViewPagerNewsTitle.text =
                if (newsArticlesItems[position].newsTitle.isNullOrEmpty())
                    "This News title does not exist, click on the item to open the full news"
                else newsArticlesItems[position].newsTitle
            everythingNewsViewPagerNewsAuthor.text =
                if (newsArticlesItems[position].newsAuthor.isNullOrEmpty()) "Top Headlines" else newsArticlesItems[position].newsAuthor
            container.addView(binding.root, 0)

            everythingNewsViewPagerNewsCardView.setOnClickListener {
                newsUrl.openNewsUrl(newsArticlesItems[position].newsUrl)
            }
        }

        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        return container.removeView(`object` as View)
    }
}