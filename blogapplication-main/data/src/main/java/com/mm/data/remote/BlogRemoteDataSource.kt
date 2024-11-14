package com.mm.data.remote

import com.mm.data.BaseRemoteDataSource
import com.mm.data.network.ApiService
import com.mm.domain.model.Blogs
import com.mm.domain.model.OutputResource
import retrofit2.Retrofit
import javax.inject.Inject

class BlogRemoteDataSource @Inject constructor(
    private val apiService: ApiService, retrofit: Retrofit
) : BaseRemoteDataSource(retrofit) {
    suspend fun getBlogs(): OutputResource<Blogs> {
        return getResponse(
            request = { apiService.getBlogs() },
            defaultErrorMessage = "Error fetching Blogs"
        )
    }
}