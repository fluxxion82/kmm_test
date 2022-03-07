// ktlint-disable filename
package com.test.foo

import co.touchlab.stately.isolate.IsolateState
import platform.Foundation.NSThread.Companion.isMainThread

@OptIn(ExperimentalStdlibApi::class)
actual object FooFighter {
    actual val foos: MutableList<Foo> = mutableListOf()

    actual val fooName = IsolateState { Name("") }

    init {
        println("is experimental: ${isExperimentalMM()}")
    }

    actual fun registerFoo(foo: Foo) {
        println("register: Is main thread $isMainThread")
        foos.add(foo)
        println("finish adding foo: $foos")
        println("end register")
    }

    actual fun unregisterFoo(foo: Foo) {
        // foos.remove(foo)
    }

    actual fun name(explicit: String): FooFighter {
        fooName.access { it.explicit = explicit }
        return this
    }

    actual fun performFoo(message: String) {
        println("perform Foo - Is main thread $isMainThread")
        println("foo list: $foos")
        foos.forEach {
            println("foo in list: $it")
        }

        fooName.access { name ->
            println("name: $name, do foo, main thread $isMainThread")
            foos.forEach { foo ->
                foo.doFoo(message)
            }
        }
    }
}
