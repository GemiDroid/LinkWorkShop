package com.gemi_droid.linkdev_workshop.view.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import com.gemi_droid.linkdev_workshop.R
import com.gemi_droid.linkdev_workshop.model.network.models.NewsModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_news_details.*
import kotlinx.android.synthetic.main.news_detail_fragment.*
import kotlinx.android.synthetic.main.news_detail_fragment.view.*
import kotlinx.android.synthetic.main.news_row.view.*

class NewsDetailsFragment : Fragment() {

    private var newsObject: NewsModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(NEWS_OBJECT)) {
                newsObject = arguments!![NEWS_OBJECT] as NewsModel?
              //  activity?.toolbar?.title = newsObject?.newsTitle
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.news_detail_fragment, container, false)

        newsObject?.let {
            rootView.txt_news_details_title.text = newsObject?.newsTitle
            rootView.txt_news_details_author.text = "By " + newsObject?.newsAuthor
            rootView.txt_news_details_date.text = newsObject?.newsDate
            rootView.txt_news_details_description.text = newsObject?.newsDescription
            Picasso.with(activity).load(newsObject?.newsImageUrl).placeholder(R.drawable.placeholder).into(rootView.img_news_details_url)


            rootView.btn_open_web?.setOnClickListener {
                openArticlePath(newsObject?.newsUrl)
            }
        }

        return rootView
    }


    private fun openArticlePath(newsUrl: String?) {

        var uri_article = Uri.parse(newsUrl)
        var intent_browse_article = Intent(Intent.ACTION_VIEW, uri_article)
        activity?.startActivity(intent_browse_article)
    }

    companion object {
        const val NEWS_OBJECT: String = "news_object"
    }
}
