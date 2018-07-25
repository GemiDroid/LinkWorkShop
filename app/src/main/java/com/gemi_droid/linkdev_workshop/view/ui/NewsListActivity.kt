package com.gemi_droid.linkdev_workshop.view.ui

import android.os.Bundle
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.gemi_droid.linkdev_workshop.R
import kotlinx.android.synthetic.main.activity_news_list.*
import kotlinx.android.synthetic.main.app_tool_bar.*

class NewsListActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var twoPane: Boolean = false
    val smallestWidth: Int = 600

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)
        setSupportActionBar(toolbar)

        if (resources.configuration.smallestScreenWidthDp >= smallestWidth)
            twoPane = true

        val fragment = NewsListFragment().apply {
            arguments = Bundle().apply {
                putBoolean(NewsListFragment.IS_TWO_PANE, twoPane)
            }
        }
        if (twoPane) {

            supportFragmentManager.beginTransaction().add(R.id.news_list, fragment).commit()
        } else {
            supportFragmentManager.beginTransaction().replace(R.id.frame_news_list, fragment).commit()
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            val toggle = ActionBarDrawerToggle(
                    this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
            drawer_layout.addDrawerListener(toggle)
            toggle.syncState()
            nav_view.setNavigationItemSelectedListener(this)
        }

    }


    override fun onBackPressed() {
        if (drawer_layout != null) {
            if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
                drawer_layout.closeDrawer(GravityCompat.START)
            } else {
                super.onBackPressed()
            }
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()
        item.isChecked = true
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}

