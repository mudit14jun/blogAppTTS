package com.mm.data.di

import com.mm.data.repository.BlogDetailRepositoryImpl
import com.mm.data.repository.BlogsRepositoryImpl
import com.mm.domain.repository.BlogDetailsRepository
import com.mm.domain.repository.BlogsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    internal abstract fun bindBlogRepository(repository: BlogsRepositoryImpl): BlogsRepository

    @Singleton
    @Binds
    internal abstract fun bindBlogDetailRepository(repository: BlogDetailRepositoryImpl): BlogDetailsRepository
}


