package com.mm.data.repository

import com.mm.data.remote.BlogDetailRemoteDataSource
import com.mm.domain.model.Blog
import com.mm.domain.model.OutputResource
import com.mm.domain.repository.BlogDetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Implementation of BlogDetailRepository
 * @param blogDetailRemoteDataSource the object of remote data source
 */
internal class BlogDetailRepositoryImpl @Inject constructor(
    private val blogDetailRemoteDataSource: BlogDetailRemoteDataSource
) : BlogDetailsRepository {

    override suspend fun getBlogDetails(id: String): Flow<OutputResource<Blog>> {
        return flow {
            emit(OutputResource.loading())
            val result = blogDetailRemoteDataSource.getBlogDetails(id)
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
}