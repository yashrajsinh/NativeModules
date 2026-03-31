package com.nativemodules.namesender;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.facebook.react.ReactApplication;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.nativemodules.R;

public class NameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_name);

        //assign views
        EditText editText = findViewById(R.id.editTextName);
        Button button = findViewById(R.id.buttonSubmit);

        button.setOnClickListener(view -> {
            String name = editText.getText().toString();
            // Get React Context
            ReactContext reactContext = ((ReactApplication) getApplication())
                    .getReactNativeHost()
                    .getReactInstanceManager()
                    .getCurrentReactContext();

            if (reactContext != null) {
                reactContext
                        .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                        .emit("onNameReceived", name);
            }

            finish(); // close activity
        });
    }
}