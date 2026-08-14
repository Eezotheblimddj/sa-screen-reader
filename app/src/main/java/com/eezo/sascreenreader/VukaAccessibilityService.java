package com.eezo.sascreenreader;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Intent;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.Locale;

public class VukaAccessibilityService extends AccessibilityService {

    private static final String TAG = "VukaEngine";
    private TextToSpeech tts;
    private String currentLanguage = "en-ZA";

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        // Get the source node
        AccessibilityNodeInfo source = event.getSource();
        if (source == null) {
            return;
        }

        // Get the text from the source
        CharSequence text = source.getText();
        if (text != null && text.length() > 0) {
            speakText(text.toString());
        }

        // Handle different event types
        switch (event.getEventType()) {
            case AccessibilityEvent.TYPE_VIEW_CLICKED:
                Log.d(TAG, "Element clicked: " + text);
                break;
            case AccessibilityEvent.TYPE_VIEW_SCROLLED:
                Log.d(TAG, "Scroll event detected");
                break;
            case AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED:
                Log.d(TAG, "Text changed: " + text);
                break;
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
                Log.d(TAG, "Window changed: " + event.getPackageName());
                break;
            case AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED:
                Log.d(TAG, "Notification: " + text);
                speakText("Notification: " + text);
                break;
        }
    }

    @Override
    public void onInterrupt() {
        if (tts != null) {
            tts.stop();
        }
    }

    @Override
    public void onServiceConnected() {
        super.onServiceConnected();

        // Set up the accessibility service info
        AccessibilityServiceInfo info = getServiceInfo();
        info.eventTypes = AccessibilityEvent.TYPES_ALL_MASK;
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_SPOKEN;
        info.flags = AccessibilityServiceInfo.FLAG_DEFAULT |
                AccessibilityServiceInfo.FLAG_RETRIEVE_INTERACTIVE_WINDOWS |
                AccessibilityServiceInfo.FLAG_REPORT_VIEW_IDS |
                AccessibilityServiceInfo.FLAG_REQUEST_TOUCH_EXPLORATION_MODE;
        info.notificationTimeout = 100;
        setServiceInfo(info);

        // Initialize Text-to-Speech
        initTTS();

        Log.d(TAG, "Vuka Accessibility Service Connected!");
        speakText("Vuka Engine is ready");
    }

    private void initTTS() {
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    // Set language (default to English South Africa)
                    int result = tts.setLanguage(new Locale("en", "ZA"));
                    if (result == TextToSpeech.LANG_MISSING_DATA ||
                            result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e(TAG, "Language not supported, falling back to default");
                        tts.setLanguage(Locale.US);
                    }
                    Log.d(TAG, "TTS initialized successfully");
                } else {
                    Log.e(TAG, "TTS initialization failed");
                }
            }
        });
    }

    private void speakText(String text) {
        if (tts != null && text != null && !text.isEmpty()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                tts.speak(text, TextToSpeech.QUEUE_ADD, null, null);
            } else {
                tts.speak(text, TextToSpeech.QUEUE_ADD, null);
            }
            Log.d(TAG, "Speaking: " + text);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        Log.d(TAG, "Vuka Engine shut down");
    }
}