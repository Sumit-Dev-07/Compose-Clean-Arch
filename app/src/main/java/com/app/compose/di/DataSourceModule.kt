package com.app.compose.di

import com.app.compose.data.datasource.AuthDataSource
import com.app.compose.data.datasource.AuthDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Singleton
    @Binds
    abstract fun bindAuthDataSource(impl: AuthDataSourceImpl): AuthDataSource
}