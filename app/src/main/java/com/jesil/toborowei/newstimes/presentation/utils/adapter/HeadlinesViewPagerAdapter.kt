package com.jesil.toborowei.newstimes.presentation.utils.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.jesil.toborowei.newstimes.R
import com.jesil.toborowei.newstimes.data.models.NewsArticles
import com.jesil.toborowei.newstimes.databinding.HeadlinesViewPagerItemLayoutBinding

class HeadlinesViewPagerAdapter(
    private val context: Context,
    private val newsArticlesItems: List<NewsArticles>
) : PagerAdapter() {

    override fun getCount(): Int = newsArticlesItems.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding = HeadlinesViewPagerItemLayoutBinding.inflate(LayoutInflater.from(context), container, false)

        binding.apply {
            Glide.with(context)
                .load(newsArticlesItems[position].newsUrlToImage)
                .placeholder(R.drawable.ic_placeholder_image)
                .error(R.drawable.ic_broken_image)
                .into(headlinesViewPagerNewsImageView)
            headlinesViewPagerNewsTitle.text = newsArticlesItems[position].newsTitle
            headlinesViewPagerNewsAuthor.text =
                if (newsArticlesItems[position].newsAuthor.isNullOrEmpty()) "Top Headlines" else newsArticlesItems[position].newsAuthor
            container.addView(binding.root, 0)

            root.setOnClickListener {
                context.startActivity(Intent(Intent.ACTION_VIEW).setData(Uri.parse(newsArticlesItems[position].newsUrl)))
            }
        }

        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
       return container.removeView(`object` as View)
    }
}