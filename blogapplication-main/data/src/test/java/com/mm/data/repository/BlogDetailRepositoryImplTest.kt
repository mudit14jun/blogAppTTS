package com.mm.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mm.data.getDummyBlog
import com.mm.data.remote.BlogDetailRemoteDataSource
import com.mm.domain.model.OutputResource
import com.mm.domain.repository.BlogDetailsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class BlogDetailRepositoryImplTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private lateinit var blogDetailsRepository: BlogDetailsRepository

    @Mock
    private lateinit var blogDetailRemoteDataSource: BlogDetailRemoteDataSource

    @Before
    fun setUp() {
        blogDetailsRepository = BlogDetailRepositoryImpl(blogDetailRemoteDataSource)
    }

    @Test
    fun `Given Blogs When GetBlogDetails returns Success`() = runBlocking {
        //GIVEN
        val givenBlogs = getDummyBlog()
        val givenBlogsOutput = OutputResource.success(givenBlogs)
        val inputFlow = listOf(OutputResource.loading(), OutputResource.success(givenBlogs))
        Mockito.`when`(blogDetailRemoteDataSource.getBlogDetails(getDummyBlog().id))
            .thenReturn(givenBlogsOutput)
        //WHEN
        val outputFlow = blogDetailsRepository.getBlogDetails(getDummyBlog().id).toList()
        //THEN
        assert(outputFlow.size == 2)
        assert(inputFlow[0] == outputFlow[0])
        assert(inputFlow[1] == outputFlow[1])
    }


}