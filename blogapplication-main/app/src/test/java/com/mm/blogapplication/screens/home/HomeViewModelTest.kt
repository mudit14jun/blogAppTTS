package com.mm.blogapplication.screens.home

import com.mm.blogapplication.BaseViewModelTest
import com.mm.blogapplication.getDummyBlogList
import com.mm.blogapplication.runBlockingMainTest
import com.mm.domain.use_cases.BlogsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest : BaseViewModelTest() {

    @Mock
    private lateinit var blogsUseCase: BlogsUseCase
    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setUp() {
        homeViewModel = HomeViewModel(blogsUseCase)
    }

    @Test
    fun `Given BlogDetail when getBlogDetail should return Success`() = runBlockingMainTest {
        //GIVEN
        val blogDetail = getDummyBlogList()
        //WHEN
        Mockito.doReturn(blogDetail).`when`(blogsUseCase).execute()
        homeViewModel.getBlogs()
        //THEN
        assert(getDummyBlogList() == homeViewModel.homeState)
    }
}