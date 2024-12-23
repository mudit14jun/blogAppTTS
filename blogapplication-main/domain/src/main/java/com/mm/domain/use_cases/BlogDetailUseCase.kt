package com.mm.domain.use_cases

import com.mm.domain.model.Blog
import com.mm.domain.model.OutputResource
import kotlinx.coroutines.flow.Flow

/**
 * UseCase Method to fetch the Blog Detail from Data Layer
 */
interface BlogDetailUseCase {
    suspend fun execute(id: String): Flow<OutputResource<Blog>>
}