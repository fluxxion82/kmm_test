package com.test.foo.console.android.di

import com.test.foo.console.ConsoleFoo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ConsoleLoggingModule {

    @Provides
    fun androidConsoleLogger() = ConsoleFoo()
}
