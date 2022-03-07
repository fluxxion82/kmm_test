package com.test.foo.console.ios.di

import com.test.foo.Foo
import com.test.foo.FooInitializer

interface ConsoleFooModule {
    fun iosConsoleFoo(): Foo
    fun fooInitializer(): FooInitializer
}
