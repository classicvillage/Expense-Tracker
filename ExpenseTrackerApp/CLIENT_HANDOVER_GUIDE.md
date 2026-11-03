# Smart Expense Tracker - Client & Developer Handover Guide

This repository contains the complete native Android App project and the bundled offline web application.

---

## 🚀 Option 1: 1-Minute APK Build in Android Studio
1. Open **Android Studio** on your computer (Windows, macOS, or Linux).
2. Click **File > Open**, and select this `ExpenseTrackerApp` folder.
3. Allow Gradle to synchronize dependencies (~30-45 seconds).
4. In the top menu, click: **Build > Build Bundle(s) / APK(s) > Build APK(s)**.
5. When build finishes, click the **locate** popup link.
   Your compiled APK file is located at:
   `app/build/outputs/apk/debug/app-debug.apk`
6. Transfer `app-debug.apk` via USB, WhatsApp, Telegram, or Google Drive to your client's Android phone and install!

---

## 📲 Option 2: Instant PWA Direct Install (Zero Build Required)
Your client can also install and run the app immediately on any Android phone or iPhone without Android Studio:
1. Open the included `index.html` in mobile Chrome (Android) or Safari (iOS).
2. Tap the browser menu (⋮ on Android or Share on iOS).
3. Tap **Add to Home screen** or **Install App**.
4. The app installs as a standalone home-screen icon, runs full-screen with no browser address bar, and stores all expenses offline!

---

## ⚡ Background SMS Auto-Detection Architecture
The Android native code in `SmsReceiver.java` listens to `android.provider.Telephony.SMS_RECEIVED`.
Whenever an incoming bank alert (UPI debit, card purchase, ATM withdrawal) arrives:
- The SMS is intercepted in real time.
- The amount, merchant name, category, and date are automatically extracted.
- The transaction is recorded instantly into the local SQLite/IndexedDB ledger.
