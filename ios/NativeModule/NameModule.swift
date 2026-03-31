//
//  NameModule.swift
//  NativeModules
//
//  Created by Yashraj on 31/03/26.
//

import Foundation
import React


@objc(NameModule)
class NameModule: RCTEventEmitter {
  
  //share name module
   @objc static var shared: NameModule?
  
  override init() {
    super.init()
    NameModule.shared = self  // Store reference on init
  }
  
  // Required
  override func supportedEvents() -> [String]! {
    return ["onNameSelected"]
  }
  
  // Exposed function to JS
   @objc func openNameScreen() {
     DispatchQueue.main.async {
       let rootVC = UIApplication.shared.delegate?.window??.rootViewController
       let vc = NameViewController()
       vc.modalPresentationStyle = .fullScreen
       rootVC?.present(vc, animated: true, completion: nil)
     }
   }

   // Required
   @objc override static func requiresMainQueueSetup() -> Bool {
     return true
   }
  
}
