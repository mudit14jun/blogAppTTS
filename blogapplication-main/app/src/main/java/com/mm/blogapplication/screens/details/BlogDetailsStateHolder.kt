package com.mm.blogapplication.screens.details

import com.mm.domain.model.Blog

data class BlogDetailsStateHolder(
    val isLoading: Boolean = false,
    val data: Blog? = null,
    val error: String = ""
)