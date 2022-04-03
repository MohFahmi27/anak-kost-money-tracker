package com.mohfahmi.moneytracker.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.mohfahmi.core.domain.models.ArticleDomain
import com.mohfahmi.moneytracker.databinding.ItemsArticleBinding

class ArticleListAdapter(private val clickListener: (ArticleDomain) -> Unit) :
    ListAdapter<ArticleDomain, ArticleListAdapter.ArticleListViewHolder>(DiffCallback) {

    class ArticleListViewHolder(
        private var binding: ItemsArticleBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(activityData: ArticleDomain) {
            with(binding) {
                imgArticle.load(activityData.image){
                    transformations(
                        listOf(RoundedCornersTransformation(8f))
                    ).build()
                }
                tvArticleTitle.text = activityData.title
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<ArticleDomain>() {
        override fun areItemsTheSame(oldItem: ArticleDomain, newItem: ArticleDomain): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: ArticleDomain, newItem: ArticleDomain): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ArticleListViewHolder(
            ItemsArticleBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ArticleListViewHolder, position: Int) {
        val articleDomain = getItem(position)
        holder.itemView.setOnClickListener {
            clickListener(articleDomain)
        }
        holder.bind(articleDomain)
    }
}