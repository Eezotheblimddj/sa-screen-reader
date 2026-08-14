package com.eezo.sascreenreader;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class SettingsActivity extends AppCompatActivity {

    private ListView settingsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        settingsList = findViewById(R.id.settings_list);

        List<String> settingsOptions = new ArrayList<>();
        settingsOptions.add("🗣️ Language: English (South Africa)");
        settingsOptions.add("👆 Gesture Profiles");
        settingsOptions.add("🔊 Speech Rate");
        settingsOptions.add("📢 Volume");
        settingsOptions.add("📷 OCR Settings");
        settingsOptions.add("🌍 Translation Settings");
        settingsOptions.add("📋 Clipboard History");
        settingsOptions.add("🔄 Reset to Defaults");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, settingsOptions);
        settingsList.setAdapter(adapter);

        settingsList.setOnItemClickListener((parent, view, position, id) -> {
            String selected = settingsOptions.get(position);
            Toast.makeText(SettingsActivity.this, "Opening: " + selected, Toast.LENGTH_SHORT).show();
        });
    }
}