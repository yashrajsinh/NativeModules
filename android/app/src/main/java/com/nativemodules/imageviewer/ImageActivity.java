package com.nativemodules.imageviewer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.nativemodules.R;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        ImageView imageView = findViewById(R.id.imageView);

        String imageUrl = getIntent().getStringExtra("imageUrl");
        if (imageUrl == null || imageUrl.isEmpty()) {
            setResult(Activity.RESULT_CANCELED);
            finish();
            return;
        }

        // Just display the image, nothing fancy
        Glide.with(this)
                .load(imageUrl)
                .into(imageView);
    }

    @Override
    public void onBackPressed() {
        // Send result back when user closes
        Intent result = new Intent();
        result.putExtra("result", "User closed the image screen");
        setResult(Activity.RESULT_OK, result);
        super.onBackPressed();
    }
}