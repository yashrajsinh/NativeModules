package com.nativemodules.namesender;

import android.app.Activity;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.modules.core.DeviceEventManagerModule;

public class NameModule extends ReactContextBaseJavaModule {

    // Static so NameActivity can reach it
    static @Nullable Promise pendingPromise = null;

    public NameModule(ReactApplicationContext context) {
        super(context);
    }

    @NonNull
    @Override
    public String getName() {
        return "NameModule";
    }

    @ReactMethod
    public void openNameScreen(Promise promise) {
        // Cancel any previous pending promise
        if (pendingPromise != null) {
            pendingPromise.reject("CANCELLED", "A new openNameScreen call was made");
            pendingPromise = null;
        }

        pendingPromise = promise;

        Intent intent = new Intent(getReactApplicationContext(), NameActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getReactApplicationContext().startActivity(intent);
    }

    // Called by NameActivity when the user submits
    static void resolvePending(String name) {
        if (pendingPromise != null) {
            pendingPromise.resolve(name);
            pendingPromise = null;
        }
    }
}