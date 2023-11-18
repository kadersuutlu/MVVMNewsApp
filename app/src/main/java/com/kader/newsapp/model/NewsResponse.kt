package com.kader.newsapp.model

import com.kader.newsapp.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)