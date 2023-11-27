package com.kader.newsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.kader.newsapp.R
import com.kader.newsapp.databinding.ActivityNewsBinding
import com.kader.newsapp.db.ArticleDatabase
import com.kader.newsapp.repository.NewsRepository

class NewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsBinding
    private lateinit var navController: NavController

    private val newsRepository by lazy { NewsRepository(ArticleDatabase(this)) }
    val viewModel by lazy {
        ViewModelProvider(
            this,
            NewsViewModelProviderFactory(newsRepository)
        )[NewsViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = findNavController(R.id.nav_host_fragment)
        binding.bottomNavigationView.setupWithNavController(navController)
    }
}