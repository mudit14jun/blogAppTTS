package com.mm.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mm.data.getDummyBlogsData
import com.mm.data.remote.BlogRemoteDataSource
import com.mm.domain.model.OutputResource
import com.mm.domain.repository.BlogsRepository
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
class BlogsRepositoryImplTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private lateinit var blogsRepository: BlogsRepository

    @Mock
    private lateinit var blogRemoteDataSource: BlogRemoteDataSource

    @Before
    fun setUp() {
        blogsRepository = BlogsRepositoryImpl(blogRemoteDataSource)
    }

    @Test
    fun `Given Blogs When getBlogsRepository returns Success`() = runBlocking {
        //GIVEN
        val givenBlogs = getDummyBlogsData()
        val givenBlogsOutput = OutputResource.success(givenBlogs)
        val inputFlow = listOf(OutputResource.loading(), OutputResource.success(givenBlogs))
        Mockito.`when`(blogRemoteDataSource.getBlogs()).thenReturn(givenBlogsOutput)
        //WHEN
        val outputFlow = blogsRepository.getBlogs().toList()
        //THEN
        assert(outputFlow.size == 2)
        assert(inputFlow[0] == outputFlow[0])
        assert(inputFlow[1] == outputFlow[1])
    }
}