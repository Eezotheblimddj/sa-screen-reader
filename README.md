SA Screen Reader (SASR) — Complete Repository Description
An open-source, fully accessible Android screen reader built specifically for South Africa, featuring native AccessibilityService support, 11 official South African languages, localized currency formatting, loadshedding alerts, and smart accessibility utility tools.
Overview
SA Screen Reader is designed to provide seamless, localized accessibility for Android users in South Africa. Operating as a native AccessibilityService (the same core architecture as TalkBack), it bridges the gap left by generic screen readers by incorporating local context, languages, and everyday mobile utilities.
Version Roadmap & Features
Version 1.0 — Core Accessibility & Local Context (Current Build)
 * AccessibilityService Core: Native Android accessibility integration that reads focused items, screen text, and structural elements aloud.
 * 11 Official SA Languages First: Prioritized language selection including isiZulu, isiXhosa, Afrikaans, Sesotho, Sepedi, Setswana, siSwati, Tshivenda, Xitsonga, isiNdebele, and English — plus ~200 global languages supported by Android's TTS engine.
 * Localized Currency Formatting: Automatically parses and speaks South African currency formats (e.g., converting "R250.50" to "250 Rand and 50 cents").
 * Loadshedding Local Alerts: Localized scheduling reminders to warn users before planned power outages.
 * WhatsApp Integration: NotificationListenerService integration for seamless message monitoring and reading.
 * Gesture & Voice Navigation: Custom tap patterns, volume-button call handling, and quiet hours.
Version 2.0 — Camera, Location & Transit (Planned)
 * OCR Text Reader: Powered by Google ML Kit to read signs, documents, and product labels.
 * Barcode Scanning: Instant product lookup.
 * Taxi Rank & Transit Finder: Location-based local transport identification using the Google Places API.
 * Grant Day Reminders: Local monthly scheduled notifications.
Version 3.0 — Face Recognition & Security (Planned)
 * On-Device Face Recognition: Local facial identification using ML Kit and TensorFlow Lite, fully compliant with POPIA (Protection of Personal Information Act) where no facial data ever leaves your device.
 * Turn-by-Turn Navigation: Localized routing assistance.
Version 4.0 — Money & Utility Tools (Planned)
 * Banknote Identifier: Custom TFLite image classifier trained to identify South African Rand notes (R10, R20, R50, R100, R200).
 * Color & Light Detectors: Ambient light tone feedback and center-pixel color identification.
Version 5.0 — Status Gestures & Polish (Planned)
 * Instant Status Gestures: Shake detection or double 2-finger taps to instantly announce battery status, signal strength, charging state, and current time.
Technical Architecture
 * Language: Kotlin
 * Minimum Android SDK: 26 (Android 8.0 Oreo, providing maximum real-world device compatibility)
 * Target Android SDK: 35
 * Core Components:
   * AccessibilityService (for screen traversal and speech output)
   * NotificationListenerService (for notification and chat alerts)
   * TextToSpeech (with localized fallback handling for en-ZA and local language data packs)
Project Structure
za.co.jami.sascreenreader/
│
├── SAScreenReaderService.kt     # Core accessibility and TTS speech engine
├── AndroidManifest.xml          # Service registrations and permissions
└── res/
    └── xml/
        └── accessibility_service_config.xml # Service configuration metadata

Getting Started & Building from Source
 * Clone the Repository:
   git clone https://github.com/YOUR_USERNAME/sa-screen-reader.git

 * Open in IDE: Open the project directory in your Android development environment.
 * Build the APK: Compile the project targets and generate your debug or signed release APK.
 * Install & Enable:
   * Install the APK onto your test device.
   * Navigate to Settings → Accessibility → Installed Apps → SA Screen Reader and toggle the service On.
License & Contributing
This project is open-source and intended to empower accessibility development. Contributions, translations for local South African languages, and feedback are welcome.
