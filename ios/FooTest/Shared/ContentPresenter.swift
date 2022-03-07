import Foundation
import UIKit
import SwiftCoroutine
import moduleB
import moduleC

protocol ContentPresenting: Presenter {
    func onClickInit()
    func doBarStuff()
}

final class ContentPresenter: ContentPresenting {
    var fooInit: FooInitializer
    var bar: Bar
    
    init(fooInit: FooInitializer, bar: Bar) {
        self.fooInit = fooInit
        self.bar = bar
    }
    
    func onClickInit() {
        DispatchQueue.main.startCoroutine {
            print("init application")

            self.fooInit.initialize { (kotlinUnit, error) in

            }
        }
    }
    
    func doBarStuff() {
        bar.doBar()
    }
}
