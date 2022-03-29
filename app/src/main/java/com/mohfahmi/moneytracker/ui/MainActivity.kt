package com.mohfahmi.moneytracker.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.viewbinding.library.activity.viewBinding
import androidx.appcompat.app.AppCompatActivity
import com.mohfahmi.moneytracker.adapters.ViewPagerAdapter
import com.mohfahmi.moneytracker.databinding.ActivityMainBinding
import com.mohfahmi.moneytracker.ui.article.ArticleFragment
import com.mohfahmi.moneytracker.ui.home.HomeFragment
import com.mohfahmi.moneytracker.ui.profile.ProfileFragment

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindViews()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun bindViews() {
        with(binding) {
            vpMain.isUserInputEnabled = false
            vpMain.adapter = ViewPagerAdapter(
                arrayListOf(HomeFragment(), ArticleFragment(), ProfileFragment()),
                supportFragmentManager,
                lifecycle
            )

            btnHome.setOnClick { vpMain.currentItem = 0 }
            btnArticle.setOnClick { vpMain.currentItem = 1 }
            btnUser.setOnClick { vpMain.currentItem = 2 }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun View.setOnClick(clickEvent: () -> Unit) {
        this.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                clickEvent.invoke()
            }
            false
        }
    }
}