//
//  iOSDeviceInfoModule.swift
//  NativeModules
//
//  Created by Yashrajsinh Jadeja on 2026-03-29.
//

import Foundation
import UIKit

@objc(iOSDeviceInfoModule)
class iOSDeviceInfoModule: NSObject{
  
  @objc
  func openDeviceScreen(){
    DispatchQueue.main.async {
      let storyboard = UIStoryboard(name: "iOSDeviceInfo", bundle: nil)
      let vc = storyboard.instantiateViewController(identifier: "iOSDeviceInfo")
      
      if let scene = UIApplication.shared.connectedScenes.first as? UIWindowScene,
         let rootVC = scene.windows.first?.rootViewController {
          rootVC.present(vc, animated: true, completion: nil)
      }
    }
  }
  @objc
    static func requiresMainQueueSetup() -> Bool {
      return true
    }
}
