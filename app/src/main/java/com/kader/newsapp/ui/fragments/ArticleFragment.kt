package com.kader.newsapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.kader.newsapp.R
import com.kader.newsapp.databinding.FragmentArticleBinding
import com.kader.newsapp.databinding.FragmentBreakingNewsBinding
import com.kader.newsapp.databinding.FragmentSearchNewsBinding
import com.kader.newsapp.model.Article
import com.kader.newsapp.ui.NewsActivity
import com.kader.newsapp.ui.NewsViewModel

class ArticleFragment : Fragment(R.layout.fragment_article) {

    private lateinit var binding: FragmentArticleBinding
    lateinit var viewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArticleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as NewsActivity).viewModel

        val clickedArticle = arguments?.getSerializable("article") as? Article

        if (clickedArticle != null) {
            Log.i("tag123", "Article URL: ${clickedArticle.url}")
            binding.webView.apply {
                webViewClient = WebViewClient()
                loadUrl(clickedArticle.url)
            }
        } else {
            Log.e("ArticleFragment", "Article is null. Unable to load URL.")
            binding.webView.apply {
                webViewClient = WebViewClient()
                loadUrl("about:blank")
            }
        }
    }
}