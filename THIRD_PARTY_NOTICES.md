# MiniDroid third-party notices

MiniDroid includes redistributable Android Open Source Project components from
the Android 17 x86_64 build `aosp_cf_x86_64_auto`, build `16373615`.

## Included APEX components

The NOTICE files for ART, Bionic/runtime, ICU/I18N, TZData, and statsd are
included in `LICENSES/` using their original APEX component names:

- `com.android.art-NOTICE.html.gz`
- `com.android.runtime-NOTICE.html.gz`
- `com.android.i18n-NOTICE.html.gz`
- `com.android.tzdata-NOTICE.html.gz`
- `com.android.os.statsd-NOTICE.html.gz`

## Included system framework

The following files are extracted from the same `system_a` EROFS partition and
are covered by `LICENSES/aosp-system-NOTICE.xml.gz`:

- `framework/framework.jar`
- `framework/framework-res.apk`

The system NOTICE archive is the complete AOSP system NOTICE file, not a
shortened replacement. Its SHA-256 is recorded in `MANIFEST.sha256`.

## Provenance

- Source image: `aosp_cf_x86_64_auto-img-16373615.zip`
- Android release: Android 17 / API 37
- Target ABI: x86_64
- Framework JAR SHA-256: `4176A12D4989951C1CE44244B7F0964FC1D1048B08B5E07760D534B592C5C8FA`
- Framework resources SHA-256: `583338EADB1464B71B5AA2C3233B4A749AA36A63C2D4739BFE4F93854B33338F`
- System NOTICE SHA-256: `5C8F6B51FD8C7FC68E8706086E119556C528D74D38C15D9A38842B49D36E36AD`

MiniDroid does not include Microsoft/WSA binaries. The EROFS extraction tool
was used only as a build-time utility and is not part of the runtime release.

## Android Debug Bridge

`bin/adb` is the Linux x86_64 Android Debug Bridge client, version
`1.0.41 / 37.0.1-15733141`. Its corresponding Android SDK notice bundle is
included as `LICENSES/adb-NOTICE.txt`.

- SHA-256: `A902BE8F45C6C62E76C9EFAF6947A0FA747C9CABD89A2AC8E0D16ECB30B3ED01`
- Runtime command: `bin/minidroid adb ...`
