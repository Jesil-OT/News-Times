package com.jesil.toborowei.newstimes.presentation.utils.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.jesil.toborowei.newstimes.R
import com.jesil.toborowei.newstimes.data.models.NewsArticles

class HeadlinesViewPagerAdapter(
    private val context: Context,
    private val newsArticlesItems: List<NewsArticles>
) : PagerAdapter() {

    private lateinit var layoutInflater: LayoutInflater

    override fun getCount(): Int = newsArticlesItems.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.headlines_view_pager_item_layout, container, false)

        val contentImageView = view.findViewById<ImageView>(R.id.headlines_view_pager_news_image_view)
        val contentTitle = view.findViewById<TextView>(R.id.headlines_view_pager_news_title)
        val contentAuthor = view.findViewById<TextView>(R.id.headlines_view_pager_news_author)


        Glide.with(view.context)
            .load(newsArticlesItems[position].newsUrlToImage)
            .error(R.drawable.ic_broken_image)
            .into(contentImageView)
        contentTitle.text = newsArticlesItems[position].newsTitle
        contentAuthor.text = newsArticlesItems[position].newsAuthor ?: "Headlines"
        container.addView(view, 0)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
       return container.removeView(`object` as View)
    }
}