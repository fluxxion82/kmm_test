// ktlint-disable filename
package com.test.foo

import co.touchlab.stately.isolate.IsolateState

actual object FooFighter {
    actual val foos: MutableList<Foo> = mutableListOf()

    actual val fooName = IsolateState { Name("") }

    actual fun registerFoo(foo: Foo) {
        synchronized(foos) {
            foos.add(foo)
        }
    }

    actual fun unregisterFoo(foo: Foo) {
        synchronized(foos) {
            foos.remove(foo)
        }
    }

    actual fun name(explicit: String): FooFighter {
        fooName.access { it.explicit = explicit }
        return this
    }

    actual fun performFoo(message: String) {
        fooName.access { tag ->
            println("tag: $tag")
            foos.forEach { foo ->
                println("logger: $foo")
                foo.doFoo(message)
            }
        }
    }
}
