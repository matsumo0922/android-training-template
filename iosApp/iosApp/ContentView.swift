//
//  ContentView.swift
//  iosApp
//
//  Created by 松本 大智 on 2024/08/27.
//

import SwiftUI
import ComposeApp

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        initTools()
        return ApplicationKt.MainViewController()
    }
    
    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
    
    private func initTools() {
        InitHelperKt.doInitKoin()
        InitHelperKt.doInitNapier()
    }
}

struct ContentView: View {
    var body: some View {
        ComposeView()
            .ignoresSafeArea(edges: .all)
            .ignoresSafeArea(.keyboard)
    }
}
