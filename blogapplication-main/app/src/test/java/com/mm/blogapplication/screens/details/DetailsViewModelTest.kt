package com.mm.blogapplication.screens.details

import androidx.lifecycle.SavedStateHandle
import com.mm.blogapplication.BaseViewModelTest
import com.mm.blogapplication.getDummyBlog
import com.mm.blogapplication.runBlockingMainTest
import com.mm.domain.use_cases.BlogDetailUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailsViewModelTest : BaseViewModelTest() {

    @Mock
    private lateinit var blogDetailUseCase: BlogDetailUseCase

    @Mock
    private lateinit var savedStateHandle: SavedStateHandle
    private lateinit var detailsViewModel: DetailsViewModel

    @Before
    fun setUp() {
        detailsViewModel = DetailsViewModel(blogDetailUseCase, savedStateHandle)
    }

    @Test
    fun `Given BlogDetail when getBlogDetail should return Success`() = runBlockingMainTest {
        //GIVEN
        val blogDetail = getDummyBlog()
        //WHEN
        Mockito.doReturn(blogDetail).`when`(blogDetailUseCase).execute(blogDetail.id)
        detailsViewModel.getBlogDetails(getDummyBlog().id)
        //THEN
        assert(getDummyBlog() == detailsViewModel.details.value.data)
    }
}