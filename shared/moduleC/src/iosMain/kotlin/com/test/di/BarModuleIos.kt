package com.test.di

import com.test.Bar

class BarModuleIos: BarModule {
    override fun iosBar(): Bar = Bar()
}
