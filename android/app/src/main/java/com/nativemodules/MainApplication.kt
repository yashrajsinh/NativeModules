package com.nativemodules

import android.app.Application
import com.facebook.react.PackageList
import com.facebook.react.ReactApplication
import com.facebook.react.ReactHost
import com.facebook.react.ReactNativeApplicationEntryPoint.loadReactNative
import com.facebook.react.defaults.DefaultReactHost.getDefaultReactHost

// custom native module
import com.nativemodules.deviceinfo.DeviceModule
import com.nativemodules.imageviewer.ImageUrlModule

// React Native classes needed for manual package
import com.facebook.react.ReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.uimanager.ViewManager

class MainApplication : Application(), ReactApplication {

  override val reactHost: ReactHost by lazy {
    getDefaultReactHost(
      context = applicationContext,
      packageList = PackageList(this).packages.apply {
        //image module
        // add custom package here
        add(object : ReactPackage {
          override fun createNativeModules(reactContext: ReactApplicationContext): List<NativeModule> {
            // adding custom module here
            return listOf(DeviceModule(reactContext),
              ImageUrlModule(reactContext)
            )
          }

          override fun createViewManagers(reactContext: ReactApplicationContext): List<ViewManager<*, *>> {
            return emptyList()
          }
        })
      },
    )
  }

  override fun onCreate() {
    super.onCreate()
    loadReactNative(this)
  }
}