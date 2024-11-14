package com.mm.domain.repository

import com.mm.domain.model.Blog
import com.mm.domain.model.OutputResource
import kotlinx.coroutines.flow.Flow

interface BlogDetailsRepository {
    suspend fun getBlogDetails(id: String): Flow<OutputResource<Blog>>
}