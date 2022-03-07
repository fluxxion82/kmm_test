//
//  ContentView.swift
//  Shared
//
//  Created by Sterling Albury on 3/7/22.
//

import SwiftUI
import CoreData

struct ContentView: View {
    @Environment(\.managedObjectContext) private var viewContext

    var component: TestComponent = TestComponent()
    
    let _presenter: ContentPresenter
    
    init() {
        print("content body body")
        _presenter = ContentPresenter(
            fooInit: component.fooModule.fooInitializer(),
            bar: component.barModule.iosBar()
        )
    }

    var body: some View {
        VStack(alignment: .leading) {
            Text("Test")
                .padding(.bottom)
        
            Button {
                print("init clicked")
                _presenter.onClickInit()
            } label: {
                Text("Init")
            }.contentShape(Rectangle())
                .padding(.bottom)
            
            Button {
                print("do bar clicked")
                _presenter.doBarStuff()
            } label: {
                Text("Do bar")
            }.contentShape(Rectangle())
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView().environment(\.managedObjectContext, PersistenceController.preview.container.viewContext)
    }
}
