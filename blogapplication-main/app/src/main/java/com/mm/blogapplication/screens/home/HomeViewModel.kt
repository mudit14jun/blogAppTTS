package com.mm.blogapplication.screens.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mm.domain.use_cases.BlogsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val blogsUseCase: BlogsUseCase) : ViewModel() {

    val homeState = mutableStateOf(HomeState())

    init {
        getBlogs()
    }

    /**
     * Method to fetch the blogs data.
     */
    fun getBlogs() {
        viewModelScope.launch {
            blogsUseCase.execute().collectIndexed { _, value ->
                homeState.value = HomeState(data = value.data)
            }
        }
    }
}