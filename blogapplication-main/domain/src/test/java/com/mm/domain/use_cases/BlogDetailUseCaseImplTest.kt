package com.mm.domain.use_cases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mm.domain.getDummyBlog
import com.mm.domain.model.OutputResource
import com.mm.domain.repository.BlogDetailsRepository
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
class BlogDetailUseCaseImplTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var blogDetailsRepository: BlogDetailsRepository
    private lateinit var blogDetailUseCase: BlogDetailUseCase

    @Before
    fun setUp() {
        blogDetailUseCase = BlogDetailUseCaseImpl(blogDetailsRepository)
    }

    @Test
    fun `Given BlogDetail When UseCase GetBlogDetails returns Success`() = runBlocking {
        //GIVEN
        val inputFlow = flowOf(OutputResource.success(getDummyBlog()))
        Mockito.`when`(blogDetailsRepository.getBlogDetails(getDummyBlog().id))
            .thenReturn(inputFlow)
        //WHEN
        val output = blogDetailUseCase.execute(getDummyBlog().id)
        //THEN
        assert(inputFlow == output)
    }
}