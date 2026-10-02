# MiniDroid V1 checkpoint 02

Relocatable ART/DEX runtime checkpoint.

Validated in a fresh path with:
    bin/minidroid smoke
    bin/minidroid apk run manager/MiniDroidManager.apk --onCreate

Expected success marker:
    MINIDROID_SMOKE_OK

The launcher removes host LD_PRELOAD, generates a location-specific Android
linker config, initializes Android data/cache directories, and launches
dalvikvm64 through Android linker64.

`bin/minidroid dex <dex-or-jar> <MainClass> [args...]` is provided for
user DEX/JAR entry points.

Generic APK manifest/Application/launcher-class resolution is included, with
multi-DEX and activity-alias support. The minimal Activity bridge initializes a
process-local Looper, inflates common binary XML layouts into Views, and
presents them through the WSLg/X11 host window.
