package com.gemi_droid.linkdev_workshop.view.adapter

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.gemi_droid.linkdev_workshop.R
import com.gemi_droid.linkdev_workshop.model.network.models.NewsModel
import com.gemi_droid.linkdev_workshop.model.utilits.TimeFormat
import com.gemi_droid.linkdev_workshop.view.ui.NewsDetailsActivity
import com.gemi_droid.linkdev_workshop.view.ui.NewsDetailsFragment
import com.gemi_droid.linkdev_workshop.view.ui.NewsListActivity
import com.gemi_droid.linkdev_workshop.view.ui.NewsListFragment
import com.squareup.picasso.Picasso

class NewsListAdapter() : RecyclerView.Adapter<NewsListAdapter.NewsHolder>() {

    private lateinit var newsList: List<NewsModel>
    private lateinit var context: NewsListActivity
    private var twoPane: Boolean = false

    constructor(context: NewsListActivity
                , newsList: List<NewsModel>, twoPane: Boolean) : this() {
        this.newsList = newsList
        this.context = context
        this.twoPane = twoPane
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val rootView: View = LayoutInflater.from(parent.context).inflate(R.layout.news_row, parent, false)
        return NewsHolder(rootView)
    }

    override fun getItemCount(): Int {
        Log.d("List in Adapter", "" + newsList.size)
        return newsList.size
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {

        val newsObject: NewsModel = newsList.get(position)

        holder.tvAuthor.setText("By " + newsObject.newsAuthor)
        holder.tvTitle.setText(newsObject.newsTitle)
        holder.tvDate.setText(TimeFormat.getCurrentTimeFormat(newsObject.newsDate))
        Picasso.with(context).load(newsObject.newsImageUrl).placeholder(R.drawable.placeholder).into(holder.imgUrl)


        holder.itemView.setOnClickListener {
            newsObject.newsDate = TimeFormat.getCurrentTimeFormat(newsObject.newsDate)
            if (twoPane) {
                val fragment = NewsDetailsFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable(NewsDetailsFragment.NEWS_OBJECT, newsObject)
                    }
                }
                context.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.news_details, fragment)
                        .commit()
            } else {
                val intent = Intent(it.context, NewsDetailsActivity::class.java).apply {
                    putExtra(NewsDetailsFragment.NEWS_OBJECT, newsObject)
                }
                it.context.startActivity(intent)
            }
        }
    }

    class NewsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvAuthor: TextView = itemView.findViewById(R.id.txt_news_author)
        val tvTitle: TextView = itemView.findViewById(R.id.txt_news_title)
        val tvDate: TextView = itemView.findViewById(R.id.txt_news_date)
        val imgUrl: ImageView = itemView.findViewById(R.id.img_news_url)
    }
}