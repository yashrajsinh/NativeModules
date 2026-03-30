package com.nativemodules.deviceinfo;

import android.app.Activity;
import android.content.Intent;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class DeviceModule extends ReactContextBaseJavaModule {

    public DeviceModule(ReactApplicationContext reactContext){
        super(reactContext);
    }

    @NonNull
    @Override
    public String getName() {
        return "DeviceModule";
    }

    @ReactMethod
    public void openDeviceInfoScreen(){
        Activity currentActivity = getCurrentActivity();
        if(currentActivity!= null){
            Intent intent = new Intent(currentActivity, DeviceInfoActivity.class);
            currentActivity.startActivity(intent);
        }
    }
}
