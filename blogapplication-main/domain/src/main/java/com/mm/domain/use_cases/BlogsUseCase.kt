package com.mm.domain.use_cases

import com.mm.domain.model.Blogs
import com.mm.domain.model.OutputResource
import kotlinx.coroutines.flow.Flow

interface BlogsUseCase {
    /**
     * UseCase Method to fetch the Blogs from Data Layer
     */
    suspend fun execute(): Flow<OutputResource<Blogs>>
}