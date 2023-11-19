package com.kader.newsapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kader.newsapp.R
import com.kader.newsapp.databinding.ItemListArticleBinding
import com.kader.newsapp.model.Article

class NewsAdapter(private var onItemClickListener: ((Article) -> Unit)) :
    RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    lateinit var contextAdapter: Context

    inner class ArticleViewHolder(var binding: ItemListArticleBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, diffCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val inflater = ItemListArticleBinding.inflate(
            LayoutInflater.from(parent.context), parent,
            false
        )
        contextAdapter = parent.context
        return ArticleViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.binding.apply {
            Glide.with(contextAdapter).load(article.urlToImage).into(ivArticleImage)
            tvSource.text = article.source.name
            tvDescription.text = article.description
            tvTitle.text = article.title
            tvPublishedAt.text = article.publishedAt

            root.setOnClickListener {
                Log.i("asdf", "assd")

                onItemClickListener?.let { it(article) }

            }

        }
    }

}