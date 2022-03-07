package com.test

import com.test.foo.FooFighter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Bar {
    fun doBar() {
        println("do bar")
        FooFighter.name("Bar")
        FooFighter.performFoo("doing bar stuff")

        GlobalScope.launch {
            doMoreBar()
        }
    }

    suspend fun doMoreBar() {
        println("do more bar")
        FooFighter.name("More Bar")
        withContext(Dispatchers.Default) {
            FooFighter.performFoo("doing more bar stuff")
        }
    }
}
