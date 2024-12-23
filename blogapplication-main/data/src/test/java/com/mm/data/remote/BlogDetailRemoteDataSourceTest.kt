package com.mm.data.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mm.data.getDummyBlog
import com.mm.data.network.ApiService
import com.mm.data.network.Constant
import com.mm.domain.model.OutputResource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.exceptions.base.MockitoException
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response
import retrofit2.Retrofit

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class BlogDetailRemoteDataSourceTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var apiService: ApiService

    @Mock
    lateinit var retrofit: Retrofit

    private lateinit var blogDetailRemoteDataSource: BlogDetailRemoteDataSource


    @Before
    fun setUp() {
        blogDetailRemoteDataSource = BlogDetailRemoteDataSource(apiService, retrofit)
    }

    @Test
    fun `Given BlogDetail When getBlogDetails returns Success`() = runBlocking {
        //GIVEN
        val dummyBlog = OutputResource.success(getDummyBlog())
        Mockito.`when`(apiService.getBlogDetails(Constant.APP_ID, getDummyBlog().id))
            .thenReturn(Response.success(dummyBlog.data))
        //WHEN
        val fetchedBlog = blogDetailRemoteDataSource.getBlogDetails(getDummyBlog().id)
        //THEN
        assert(fetchedBlog == dummyBlog)
    }

    @Test
    fun `Given BlogDetail When getBlogDetails returns Error`() = runBlocking {
        //GIVEN
        val mockitoException = MockitoException("Unknown Error")
        Mockito.`when`(apiService.getBlogDetails(Constant.APP_ID, getDummyBlog().id))
            .thenThrow(mockitoException)
        //WHEN
        val fetchedBlogs = blogDetailRemoteDataSource.getBlogDetails(getDummyBlog().id)
        //THEN
        assert(fetchedBlogs.message == "Unknown Error")
    }

    @Test
    fun `Given Blogs When getBlogs returns Server Error`() = runBlocking {
        //GIVEN
        Mockito.`when`(apiService.getBlogDetails(Constant.APP_ID, getDummyBlog().id))
            .thenReturn(Response.error(500, "".toResponseBody()))
        //WHEN
        val fetchedBlogs = blogDetailRemoteDataSource.getBlogDetails(getDummyBlog().id)
        //THEN
        assert(fetchedBlogs.message == "Unknown Error")
    }
}