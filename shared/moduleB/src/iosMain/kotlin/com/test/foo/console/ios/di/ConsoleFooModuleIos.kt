package com.test.foo.console.ios.di

import com.test.foo.FooInitializer
import com.test.foo.console.ConsoleFoo

class ConsoleFooModuleIos : ConsoleFooModule {
    override fun iosConsoleFoo() = ConsoleFoo()
    override fun fooInitializer(): FooInitializer = FooInitializer(setOf(iosConsoleFoo()))
}
