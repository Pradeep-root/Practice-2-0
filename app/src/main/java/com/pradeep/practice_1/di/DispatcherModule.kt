package com.pradeep.practice_1.di

import com.pradeep.practice_1.core.dispatcher.DispatcherProvider
import com.pradeep.practice_1.core.dispatcher.DispatcherProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {

    @Provides
    @Singleton
    fun providesDispatcherProvider(impl: DispatcherProviderImpl): DispatcherProvider = impl
}