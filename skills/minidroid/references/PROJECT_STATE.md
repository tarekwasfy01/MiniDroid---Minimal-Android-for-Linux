# MiniDroid project state represented by this plugin

This plugin is built from the user-supplied `minidroid-x86_64.zip` snapshot and the project's current engineering brief.

## Snapshot facts visible in the archive

The archive contains a portable MiniDroid launcher, local bridge, bundled Linux x86_64 adb client, Android Manager APK, Android framework resources/JARs, a MiniDroid framework patch JAR, an X11 host-window helper, ART/Bionic/APEX runtime files, smoke-test artifacts, relocation logs, checksums and license notices.

## Engineering baseline from the ongoing project

Work leading into this snapshot established or targeted:

- real Android Application/Activity lifecycle rather than treating APKs as JARs
- binary Android manifest parsing and launcher resolution
- multidex discovery
- Android resource ID resolution and binary XML layout handling
- a real Linux host window path
- MiniDroidManager as an Android APK
- real package/install/uninstall/backup/cache/export operations where implemented
- local privileged manager bridge
- ADB client integration with the larger goal that MiniDroid itself becomes an ADB target

## Do not overstate

The presence of a binary or UI control is not proof of a complete subsystem. In particular, verify device-side ADB transport/auth/shell/package/log behavior before claiming that `adb devices`, `adb shell`, `adb install` or `adb logcat` work against MiniDroid itself.

Likewise, verify each manager action end-to-end against the current runtime before marking it complete.

## Product architecture

MiniDroid remains a compact Android compatibility runtime, not a full Android OS. Full Android images are optional component pools used to create compatibility add-ons; they must never become a replacement Android VM/system boot path.
