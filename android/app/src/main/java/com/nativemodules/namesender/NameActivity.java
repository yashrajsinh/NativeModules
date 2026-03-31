package com.nativemodules.namesender;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.nativemodules.R;

public class NameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        EditText editText = findViewById(R.id.editTextName);
        Button button = findViewById(R.id.buttonSubmit);

        button.setOnClickListener(view -> {
            String name = editText.getText().toString();

            // Resolve the promise — no ReactContext needed here at all
            NameModule.resolvePending(name);

            finish();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // User pressed back without submitting — reject the promise cleanly
        if (NameModule.pendingPromise != null) {
            NameModule.pendingPromise.reject("DISMISSED", "User closed the screen");
            NameModule.pendingPromise = null;
        }
    }
}