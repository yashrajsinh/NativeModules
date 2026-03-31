//
//  NameViewController.swift
//  NativeModules
//
//  Created by Yashraj on 31/03/26.
//

import Foundation

class NameViewController : UIViewController {

  let textField = UITextField()
  let button = UIButton(type: .system)

  override func viewDidLoad() {
    super.viewDidLoad()

    view.backgroundColor = .white

    textField.placeholder = "Enter your name"
    textField.borderStyle = .roundedRect
    textField.translatesAutoresizingMaskIntoConstraints = false

    button.setTitle("Submit", for: .normal)
    button.translatesAutoresizingMaskIntoConstraints = false
    button.addTarget(self, action: #selector(submitTapped), for: .touchUpInside)

    view.addSubview(textField)
    view.addSubview(button)

    NSLayoutConstraint.activate([
      textField.centerXAnchor.constraint(equalTo: view.centerXAnchor),
      textField.centerYAnchor.constraint(equalTo: view.centerYAnchor),
      textField.widthAnchor.constraint(equalToConstant: 250),

      button.topAnchor.constraint(equalTo: textField.bottomAnchor, constant: 20),
      button.centerXAnchor.constraint(equalTo: view.centerXAnchor)
    ])
  }

  @objc func submitTapped() {
    let name = textField.text ?? ""

    // Use the shared instance instead of bridge
    NameModule.shared?.sendEvent(withName: "onNameSelected", body: name)

    dismiss(animated: true, completion: nil)
  }
}
