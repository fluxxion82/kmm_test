import Foundation
import moduleC

class TestBarModule : BarModule {
    lazy var module: BarModuleIos = BarModuleIos()
    
    func iosBar() -> Bar {
        module.iosBar()
    }
}
