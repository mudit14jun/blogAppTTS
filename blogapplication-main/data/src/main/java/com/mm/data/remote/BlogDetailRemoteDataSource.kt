package com.mm.data.remote

import com.mm.data.BaseRemoteDataSource
import com.mm.data.network.ApiService
import com.mm.domain.model.Blog
import com.mm.domain.model.OutputResource
import retrofit2.Retrofit
import javax.inject.Inject

class BlogDetailRemoteDataSource @Inject constructor(
    private val apiService: ApiService, retrofit: Retrofit
) : BaseRemoteDataSource(retrofit) {
    suspend fun getBlogDetails(id: String): OutputResource<Blog> {
        return getResponse(
            request = { apiService.getBlogDetails(id = id) },
            defaultErrorMessage = "Error fetching Blogs"
        )
    }
}