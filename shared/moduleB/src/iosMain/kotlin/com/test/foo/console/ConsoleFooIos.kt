package com.test.foo.console

import com.test.foo.Foo

actual class ConsoleFoo : Foo {
    actual override fun doFoo(stringParam: String) {
        println("console Foo: do foo: $stringParam")
    }
}
