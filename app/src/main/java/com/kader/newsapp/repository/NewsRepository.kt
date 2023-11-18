package com.kader.newsapp.repository

import com.kader.newsapp.api.RetrofitInstance
import com.kader.newsapp.db.ArticleDatabase

class NewsRepository(
    val db:ArticleDatabase
) {
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)
}