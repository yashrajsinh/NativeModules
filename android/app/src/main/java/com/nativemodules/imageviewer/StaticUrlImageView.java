package com.nativemodules.imageviewer;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;

public class StaticUrlImageView extends AppCompatImageView {
    public StaticUrlImageView(@NonNull Context context) {
        super(context);
    }
    public void setImageUrl(String url){
        Glide.with(getContext())
                .load(url)
                .into(this);
    }
}
