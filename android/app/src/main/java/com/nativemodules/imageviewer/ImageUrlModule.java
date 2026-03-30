package com.nativemodules.imageviewer;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class ImageUrlModule extends ReactContextBaseJavaModule {

    public  ImageUrlModule(ReactApplicationContext reactApplicationContext){
        super(reactApplicationContext);
    }
   @NonNull
    @Override
    public String getName() {
        return "ImageUrlModule";
    }
    //React method which will show an image to user
    @ReactMethod
    public void getImageURL(Promise promise){
        try {
            //call API
            String url = "https://media1.tenor.com/m/ppVtnp_ePbMAAAAC/cursed2rule.gif";
            promise.resolve(url);
        }catch (Exception e){
            promise.reject("Error", e);
        }
    }
}
