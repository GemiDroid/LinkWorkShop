package com.gemi_droid.linkdev_workshop.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.gemi_droid.linkdev_workshop.R
import com.gemi_droid.linkdev_workshop.model.network.models.NewsModel
import com.gemi_droid.linkdev_workshop.view.adapter.NewsListAdapter
import com.gemi_droid.linkdev_workshop.view_model.NewsViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_news_list.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.news_detail_fragment.*
import kotlinx.android.synthetic.main.news_detail_fragment.view.*

class NewsListFragment : Fragment() {


    private var twoPane: Boolean = false
    private lateinit var newsViewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val rootView: View = inflater.inflate(R.layout.fragment_news_list, container, false)

        initViewModel()

        observeOnNewsList()
        twoPane = arguments!!.getBoolean(IS_TWO_PANE)

        return rootView
    }

    private fun observeOnNewsList() {
        newsViewModel.observeOnNews().observe(this, Observer {
            if (it != null) {
                setupRecyclerView(rec_news, it)
            } else {
                Snackbar.make(nav_view, getString(R.string.no_internet), Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun initViewModel() {
        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
        newsViewModel.getNews()
    }

    private fun setupRecyclerView(recyclerView: RecyclerView, newsList: List<NewsModel>) {
        recyclerView.adapter = NewsListAdapter(activity as NewsListActivity, newsList, twoPane)
    }

    companion object {
        const val IS_TWO_PANE: String = "is_two_pane"
    }

}