import Foundation

protocol Presenter: AnyObject {
    func configureView()
    func start()
    func stop()
}

extension Presenter {
    func configureView() { }
    func start() { }
    func stop() { }
}
