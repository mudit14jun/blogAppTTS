package com.mm.data.repository

import com.mm.data.remote.BlogRemoteDataSource
import com.mm.domain.model.Blogs
import com.mm.domain.model.OutputResource
import com.mm.domain.repository.BlogsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Implementation of BlogRepository
 * @param blogRemoteDataSource the object of remote data source
 */
internal class BlogsRepositoryImpl @Inject constructor(
    private val blogRemoteDataSource: BlogRemoteDataSource
) : BlogsRepository {
    override suspend fun getBlogs(): Flow<OutputResource<Blogs>> {
        return flow {
            emit(OutputResource.loading())
            val result = blogRemoteDataSource.getBlogs()
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
}


