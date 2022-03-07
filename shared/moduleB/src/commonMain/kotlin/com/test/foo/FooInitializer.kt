package com.test.foo

import kotlin.jvm.JvmSuppressWildcards

class FooInitializer constructor(
    private val foos: Set<@JvmSuppressWildcards Foo>
) {
    suspend fun initialize() {
        println("init foo set")
        foos.forEach {
            FooFighter.registerFoo(it)
        }
    }
}
