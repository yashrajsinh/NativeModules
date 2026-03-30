package com.nativemodules.imageviewer;

import android.app.Activity;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.BaseActivityEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class ImageUrlModule extends ReactContextBaseJavaModule {

    private static final int IMAGE_ACTIVITY_REQUEST = 1234;
    private Promise mPromise;

    public ImageUrlModule(ReactApplicationContext reactContext){
        super(reactContext);

        reactContext.addActivityEventListener(new BaseActivityEventListener() {
            @Override
            public void onActivityResult(@NonNull Activity activity, int requestCode, int resultCode, @Nullable Intent data) {
                if(requestCode == IMAGE_ACTIVITY_REQUEST && mPromise != null){
                    if(resultCode == Activity.RESULT_OK && data != null){
                        String msg = data.getStringExtra("result");
                        mPromise.resolve(msg);
                    } else {
                        mPromise.reject("IMAGE_LOAD_FAILED", "Failed to load image");
                    }
                    mPromise = null;
                }
            }
        });
    }

    @NonNull
    @Override
    public String getName() {
        return "ImageUrlModule";
    }

    @ReactMethod
    public void openImageViewScreen(String imageUrl, Promise promise){
        Activity currentActivity = getCurrentActivity();
        if(currentActivity == null){
            promise.reject("NO_ACTIVITY", "Activity not found");
            return;
        }

        mPromise = promise;

        Intent intent = new Intent(currentActivity, ImageActivity.class);
        intent.putExtra("imageUrl", imageUrl);
        currentActivity.startActivityForResult(intent, IMAGE_ACTIVITY_REQUEST);
    }
}