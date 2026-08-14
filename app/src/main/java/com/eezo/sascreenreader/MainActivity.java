package com.eezo.sascreenreader;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnEnableService;
    private Button btnOpenSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEnableService = findViewById(R.id.btn_enable_service);
        btnOpenSettings = findViewById(R.id.btn_open_settings);

        btnEnableService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Enable SA Screen Reader from the list", Toast.LENGTH_LONG).show();
            }
        });

        btnOpenSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        checkServiceStatus();
    }

    private void checkServiceStatus() {
        AccessibilityServiceInfo info = null;
        try {
            info = ((android.accessibilityservice.AccessibilityServiceInfo) 
                    getSystemService(ACCESSIBILITY_SERVICE));
        } catch (Exception e) {
            // Service not available
        }
        
        if (info != null && info.getId().contains("sascreenreader")) {
            Toast.makeText(this, "SA Screen Reader is ACTIVE!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please enable SA Screen Reader in Accessibility Settings", Toast.LENGTH_LONG).show();
        }
    }
}