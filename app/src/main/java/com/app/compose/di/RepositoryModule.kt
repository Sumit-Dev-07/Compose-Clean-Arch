package com.app.compose.di

import com.app.compose.domain.repository.AuthRepository
import com.app.compose.domain.repository.AuthRepositoryImpl
import com.app.compose.domain.repository.CategoryRepository
import com.app.compose.domain.repository.CategoryRepositoryImpl
import com.app.compose.domain.repository.ProductRepository
import com.app.compose.domain.repository.ProductRepositoryImpl
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
    abstract fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    @Singleton
    @Binds
    abstract fun bindProductRepository(impl: ProductRepositoryImpl): ProductRepository

    @Singleton
    @Binds
    abstract fun bindCategoryRepository(impl: CategoryRepositoryImpl): CategoryRepository
}