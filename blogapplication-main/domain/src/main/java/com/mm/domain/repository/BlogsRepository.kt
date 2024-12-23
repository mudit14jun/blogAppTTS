package com.mm.domain.repository

import com.mm.domain.model.Blogs
import com.mm.domain.model.OutputResource
import kotlinx.coroutines.flow.Flow

interface BlogsRepository {
    suspend fun getBlogs(): Flow<OutputResource<Blogs>>
}