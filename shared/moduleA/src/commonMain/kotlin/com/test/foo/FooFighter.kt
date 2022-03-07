package com.test.foo

import co.touchlab.stately.isolate.IsolateState

@OptIn(ExperimentalStdlibApi::class)
expect object FooFighter {
    val foos: MutableList<Foo>

    val fooName: IsolateState<Name>

    fun registerFoo(foo: Foo)

    fun unregisterFoo(foo: Foo)

    fun name(explicit: String): FooFighter

    fun performFoo(message: String)
}

data class Name(var explicit: String?)
