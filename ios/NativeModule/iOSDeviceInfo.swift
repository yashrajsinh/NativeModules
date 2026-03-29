//
//  iOSDeviceInfo.swift
//  NativeModules
//
//  Created by Yashrajsinh Jadeja on 2026-03-29.
//

import UIKit

class iOSDeviceInfo: UIViewController {
  
    @IBOutlet weak var lblName: UILabel!
    @IBOutlet weak var lblModel: UILabel!
    @IBOutlet weak var lblOS: UILabel!
    @IBOutlet weak var lblVersion: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        //assign relevant info to our labels
        let device = UIDevice.current
        let model = device.model
        let name = device.name
        let os = device.systemName
        let version = device.systemVersion
        
        lblName.text = name
        lblModel.text = model
        lblOS.text = os
        lblVersion.text = version
    }
    
}
