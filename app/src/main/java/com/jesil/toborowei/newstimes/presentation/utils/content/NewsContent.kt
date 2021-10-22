package com.jesil.toborowei.newstimes.presentation.utils.content

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.jesil.toborowei.newstimes.R
import com.jesil.toborowei.newstimes.data.models.NewsArticles
import com.jesil.toborowei.newstimes.databinding.ContentBottomSheetBinding
import com.jesil.toborowei.newstimes.presentation.utils.OpenNewsUrl
import com.jesil.toborowei.newstimes.presentation.utils.openWebPage
import com.jesil.toborowei.newstimes.presentation.utils.shareNews

class NewsContent(private val newsArticles: NewsArticles) : BottomSheetDialogFragment(), OpenNewsUrl {
    private var _binding: ContentBottomSheetBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val TAG = "NewsContent"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ContentBottomSheetBinding.inflate(inflater, container, false)
        binding.apply {
            Glide.with(requireContext())
                .load(newsArticles.newsUrlToImage)
                .error(R.drawable.ic_broken_image)
                .placeholder(R.drawable.ic_placeholder_image)
                .into(bottomSheetNewsImage)

            bottomSheetSourceName.text =
                newsArticles.newsSource?.newsName ?: "The News Times"
            bottomSheetNewsTitle.text = newsArticles.newsTitle
            bottomSheetNewsContent.text = newsArticles.newsContent
                ?: "This News does not have a content, please visit the the news source by clicking on Read more"
            bottomSheetNewsAuthor.text =
                if (newsArticles.newsAuthor.isNullOrEmpty()) "Catergories News" else newsArticles.newsAuthor
            bottomSheetCancel.setOnClickListener {
                dismiss()
            }
            bottomSheetReadMoreUrl.setOnClickListener {
                openNewsUrl(newsArticles.newsUrl)
            }
            bottomSheetShare.setOnClickListener {
                shareNewsUrl(newsArticles.newsUrl)
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun openNewsUrl(newsUrl: String?) {
        openWebPage(newsUrl ?: "")
    }

    override fun shareNewsUrl(newsUrl: String?) {
        shareNews(newsUrl ?: "")
    }
}