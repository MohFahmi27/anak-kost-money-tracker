package com.mohfahmi.moneytracker.ui.article

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import com.mohfahmi.moneytracker.R
import com.mohfahmi.moneytracker.databinding.FragmentArticleBinding

class ArticleFragment : Fragment(R.layout.fragment_article) {
    private val binding: FragmentArticleBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}