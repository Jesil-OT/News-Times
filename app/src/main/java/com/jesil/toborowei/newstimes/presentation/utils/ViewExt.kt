package com.jesil.toborowei.newstimes.presentation.utils

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.jesil.toborowei.newstimes.R

fun View.hideView() {
    visibility = View.GONE
}

fun View.showView() {
    visibility = View.VISIBLE
}

fun Fragment.openWebPage(newsUrl: String){
    val builder: CustomTabsIntent.Builder = CustomTabsIntent.Builder()
    val customTabsIntent = builder.build()
    builder.apply{
        setToolbarColor(ContextCompat.getColor(requireContext(), R.color.white))
        setShowTitle(true)
        setMenuVisibility(true)
    }
    customTabsIntent.launchUrl(requireContext(), Uri.parse(newsUrl))
}

fun Fragment.shareNews(item: String) {
    val shareIntent = Intent(Intent.ACTION_SEND)
    shareIntent.putExtra(
        Intent.EXTRA_TEXT, item
    )
    shareIntent.type = "text/plain"
    startActivity(shareIntent)
}