import Foundation
import moduleB

class TestFooModule : ConsoleFooModule {
    lazy var module: ConsoleFooModuleIos = ConsoleFooModuleIos()
    
    func fooInitializer() -> FooInitializer {
        module.fooInitializer()
    }
    
    func iosConsoleFoo() -> ModuleAFoo {
        module.iosConsoleFoo()
    }
}
