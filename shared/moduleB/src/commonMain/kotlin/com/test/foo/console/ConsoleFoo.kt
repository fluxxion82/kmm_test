package com.test.foo.console

import com.test.foo.Foo

expect class ConsoleFoo : Foo {
    override fun doFoo(stringParam: String)
}
