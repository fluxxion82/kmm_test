package com.test.foo.console

import com.test.foo.Foo
import android.util.Log as AndroidLog

actual class ConsoleFoo : Foo {

    actual override fun doFoo(
        priority: Foo.Priority,
        explicitTag: String?,
        inferredTag: String,
        message: String?,
        throwable: Throwable?,
        properties: Map<String, String>?
    ) {
        val tagToLog = explicitTag ?: inferredTag
        val messageToLog = message ?: throwable?.message.orEmpty()

        when (priority) {
            Foo.Priority.VERBOSE -> {
                throwable?.let {
                    AndroidLog.v(tagToLog, messageToLog, it)
                } ?: AndroidLog.v(tagToLog, messageToLog)
            }
            Foo.Priority.INFO -> {
                throwable?.let {
                    AndroidLog.i(tagToLog, messageToLog, it)
                } ?: AndroidLog.i(tagToLog, messageToLog)
            }
            Foo.Priority.DEBUG -> {
                throwable?.let {
                    AndroidLog.d(tagToLog, messageToLog, it)
                } ?: AndroidLog.d(tagToLog, messageToLog)
            }
            Foo.Priority.WARNING -> {
                throwable?.let {
                    if (message == null) {
                        AndroidLog.w(tagToLog, it)
                    } else {
                        AndroidLog.w(tagToLog, messageToLog, it)
                    }
                } ?: AndroidLog.w(tagToLog, messageToLog)
            }
            Foo.Priority.ERROR -> {
                throwable?.let {
                    AndroidLog.e(tagToLog, messageToLog, it)
                } ?: AndroidLog.e(tagToLog, messageToLog)
            }
            Foo.Priority.WTF -> {
                throwable?.let {
                    if (message == null) {
                        AndroidLog.wtf(tagToLog, it)
                    } else {
                        AndroidLog.wtf(tagToLog, messageToLog, it)
                    }
                } ?: AndroidLog.wtf(tagToLog, messageToLog)
            }
        }.let { }
    }
}
