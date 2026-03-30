import Foundation
import UIKit
import React

@objc(NativeImageModule)
class NativeImageModule: NSObject {

    @objc
    func showImage(_ imageURL: String) {
        DispatchQueue.main.async {
            guard let window = UIApplication.shared.connectedScenes
                    .compactMap({ $0 as? UIWindowScene })
                    .flatMap({ $0.windows })
                    .first(where: { $0.isKeyWindow }) else {
                print("No key window found")
                return
            }

            let fullScreenView = UIView(frame: window.bounds)
            fullScreenView.backgroundColor = UIColor.black.withAlphaComponent(0.8)
            fullScreenView.autoresizingMask = [.flexibleWidth, .flexibleHeight]


            let imageView = UIImageView()
            imageView.contentMode = .scaleAspectFit
            imageView.translatesAutoresizingMaskIntoConstraints = false
            fullScreenView.addSubview(imageView)

            NSLayoutConstraint.activate([
           
                imageView.centerXAnchor.constraint(equalTo: fullScreenView.centerXAnchor),
                imageView.centerYAnchor.constraint(equalTo: fullScreenView.centerYAnchor),
                imageView.widthAnchor.constraint(equalTo: fullScreenView.widthAnchor, multiplier: 0.9),
                imageView.heightAnchor.constraint(equalTo: fullScreenView.heightAnchor, multiplier: 0.8),
            ])

            if let url = URL(string: imageURL) {
                URLSession.shared.dataTask(with: url) { data, _, error in
                    guard let data = data, error == nil, let img = UIImage(data: data) else {
                        print("Image load error: \(error?.localizedDescription ?? "unknown")")
                      
                        return
                    }
                    DispatchQueue.main.async {
                        imageView.image = img
                    }
                }.resume()
            }

            let tap = UITapGestureRecognizer(target: self, action: #selector(self.dismissView(_:)))
            fullScreenView.addGestureRecognizer(tap)
            fullScreenView.tag = 9999
            window.addSubview(fullScreenView)
        }
    }

    @objc func dismissView(_ sender: UITapGestureRecognizer) {
        sender.view?.removeFromSuperview()
    }
}
