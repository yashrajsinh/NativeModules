package com.nativemodules

import com.facebook.react.ReactActivity
import com.facebook.react.ReactActivityDelegate
import com.facebook.react.defaults.DefaultNewArchitectureEntryPoint.fabricEnabled
import com.facebook.react.defaults.DefaultReactActivityDelegate
import android.os.Bundle
import android.content.Intent

class MainActivity : ReactActivity() {

    override fun getMainComponentName(): String = "NativeModules"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(null)

        openDeviceInfoScreen()
    }

    fun openDeviceInfoScreen() {
        val intent = Intent(this, DeviceInfoActivity::class.java)
        startActivity(intent)
    }

    override fun createReactActivityDelegate(): ReactActivityDelegate =
        DefaultReactActivityDelegate(this, mainComponentName, fabricEnabled)
}