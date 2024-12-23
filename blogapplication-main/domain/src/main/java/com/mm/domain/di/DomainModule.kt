package com.mm.domain.di

import com.mm.domain.use_cases.BlogDetailUseCase
import com.mm.domain.use_cases.BlogDetailUseCaseImpl
import com.mm.domain.use_cases.BlogsUseCase
import com.mm.domain.use_cases.BlogsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class DomainModule {
    @Binds
    @Singleton
    internal abstract fun bindBlogsUseCase(useCaseImpl: BlogsUseCaseImpl): BlogsUseCase

    @Binds
    @Singleton
    internal abstract fun bindBlogDetailUseCase(useCaseImpl: BlogDetailUseCaseImpl): BlogDetailUseCase
}
