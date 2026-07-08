package com.app.compose.di

import com.app.compose.util.DefaultDispatcherProvider
import com.app.compose.util.DispatchersProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DispatcherModule {

    @Binds
    @Singleton
    abstract fun bindDispatcherProvider(defaultDispatcherProvider: DefaultDispatcherProvider): DispatchersProvider
}