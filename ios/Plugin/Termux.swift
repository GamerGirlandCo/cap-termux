import Foundation

@objc public class Termux: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
}
