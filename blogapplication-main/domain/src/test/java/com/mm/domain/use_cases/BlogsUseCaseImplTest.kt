package com.mm.domain.use_cases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mm.domain.getDummyBlogsData
import com.mm.domain.model.OutputResource
import com.mm.domain.repository.BlogsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
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
class BlogsUseCaseImplTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var blogsRepository: BlogsRepository
    private lateinit var blogsUseCase: BlogsUseCase

    @Before
    fun setUp() {
        blogsUseCase = BlogsUseCaseImpl(blogsRepository)
    }

    @Test
    fun `Given Blogs When UseCase getBlogs returns Success`() = runBlocking {
        //GIVEN
        val inputFlow = flowOf(OutputResource.success(getDummyBlogsData()))
        Mockito.`when`(blogsRepository.getBlogs()).thenReturn(inputFlow)
        //WHEN
        val output = blogsUseCase.execute()
        //THEN
        assert(inputFlow == output)
    }
}