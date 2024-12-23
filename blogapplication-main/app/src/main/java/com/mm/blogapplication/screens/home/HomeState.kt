package com.mm.blogapplication.screens.home

import com.mm.domain.model.Blogs

data class HomeState(
    val isLoading: Boolean = false,
    val data: Blogs? = null,
    val error: String = ""
)
