//
//  TestComponent.swift
//  FooTest (iOS)
//
//  Created by Sterling Albury on 3/7/22.
//

import Foundation

class TestComponent {
    lazy var fooModule = TestFooModule()
    lazy var barModule = TestBarModule()
    lazy var consoleFoo = fooModule.iosConsoleFoo()
    lazy var fooList = [consoleFoo]
}
