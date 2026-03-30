package com.nativemodules.imageviewer;

import androidx.annotation.NonNull;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;

public class StaticUrlImageViewManager extends SimpleViewManager<StaticUrlImageView> {
    @NonNull
    @Override
    public String getName() {
        return "StaticUrlImageView";
    }

    @NonNull
    @Override
    protected StaticUrlImageView createViewInstance(@NonNull ThemedReactContext reactContext) {
        return new StaticUrlImageView(reactContext);
    }
}
