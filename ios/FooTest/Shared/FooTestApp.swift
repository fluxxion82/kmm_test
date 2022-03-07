//
//  FooTestApp.swift
//  Shared
//
//  Created by Sterling Albury on 3/7/22.
//

import SwiftUI

@main
struct FooTestApp: App {
    let persistenceController = PersistenceController.shared

    var body: some Scene {
        WindowGroup {
            ContentView()
                .environment(\.managedObjectContext, persistenceController.container.viewContext)
        }
    }
}
