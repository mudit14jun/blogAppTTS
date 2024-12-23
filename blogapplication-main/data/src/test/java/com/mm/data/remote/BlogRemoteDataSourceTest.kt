package com.mm.data.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mm.data.getDummyBlogsData
import com.mm.data.network.ApiService
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
class BlogRemoteDataSourceTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var apiService: ApiService

    @Mock
    lateinit var retrofit: Retrofit

    private lateinit var blogRemoteDataSource: BlogRemoteDataSource

    @Before
    fun setUp() {
        blogRemoteDataSource = BlogRemoteDataSource(apiService, retrofit)
    }

    @Test
    fun `Given Blogs When getBlogs returns Success`() = runBlocking {
        //GIVEN
        val dummyBlogList = getDummyBlogsData()
        Mockito.`when`(apiService.getBlogs()).thenReturn(Response.success(dummyBlogList))
        //WHEN
        val fetchedBlogs = blogRemoteDataSource.getBlogs()
        //THEN
        assert(fetchedBlogs.data?.data?.size == dummyBlogList.data?.size)
    }

    @Test
    fun `Given Blogs When getBlogs returns Error`() = runBlocking {
        //GIVEN
        val mockitoException = MockitoException("Unknown Error")
        Mockito.`when`(apiService.getBlogs()).thenThrow(mockitoException)
        //WHEN
        val fetchedBlogs = blogRemoteDataSource.getBlogs()
        //THEN
        assert(fetchedBlogs.message == "Unknown Error")
    }

    @Test
    fun `Given Blogs When getBlogs returns Server Error`() = runBlocking {
        //GIVEN
        Mockito.`when`(apiService.getBlogs())
            .thenReturn(Response.error(500, "".toResponseBody()))
        //WHEN
        val fetchedBlogs = blogRemoteDataSource.getBlogs()
        //THEN
        assert(fetchedBlogs.message == "Unknown Error")
    }
}