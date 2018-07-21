package com.gemi_droid.linkdev_workshop.view.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.gemi_droid.linkdev_workshop.R
import com.gemi_droid.linkdev_workshop.model.network.models.NewsModel
import kotlinx.android.synthetic.main.app_tool_bar.*

class NewsDetailsActivity :AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null) {
            val fragment = NewsDetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(NewsDetailsFragment.NEWS_OBJECT,
                            intent.getParcelableExtra<NewsModel>(NewsDetailsFragment.NEWS_OBJECT))
                }
            }
            supportFragmentManager.beginTransaction()
                    .add(R.id.container_details, fragment)
                    .commit()
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }
     override fun onOptionsItemSelected(item: MenuItem) =
                when (item.itemId) {
                    android.R.id.home -> {
                        navigateUpTo(Intent(this, NewsListActivity::class.java))
                        true
                    }
                    else -> super.onOptionsItemSelected(item)
                }
    }
