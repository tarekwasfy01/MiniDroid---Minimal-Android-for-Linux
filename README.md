# MiniDroid V1 portable checkpoint

<a href="https://snapcraft.io/minidroid">
    <img alt="Get it from the Snap Store" src=https://snapcraft.io/en/dark/install.svg />
  </a>

This checkpoint is a relocatable headless Android x86_64 runtime. The hard
release limit applies to the compressed archive only: the official ZIP must
remain at or below 100 MiB. Installed size is reported separately.

Run:
    ./bin/minidroid
    ./bin/minidroid smoke
    ./bin/minidroid dex <jar-or-dex> <main-class> [args...]
    ./bin/minidroid jar <jar> <main-class> [args...]
    ./bin/minidroid apk inspect <apk>
    ./bin/minidroid apk run <apk>
    ./bin/minidroid apk run <apk> --onCreate
    ./bin/minidroid apk run <apk> <dex-main-class> [args...]
    ./bin/minidroid elf <android-elf> [args...]
    ./bin/minidroid doctor
    ./bin/size-report <dist-directory>

The portable smoke test was executed after relocating the complete directory.
ART loaded and executed classes.dex from TESTS/abx.jar. The smoke command
intentionally invokes the test program without arguments, so its expected
result is its usage text and exit status 1.

This checkpoint contains Android runtime components and their collected NOTICE
material. It is a compact compatibility release, not a certification of
arbitrary Android applications.

`apk inspect` decodes binary AndroidManifest.xml without a host Android SDK and
reports package, Application, launcher Activity, aliases, permissions, DEX and
native-library entries. `apk run <apk>` resolves multi-DEX, executes
Application.onCreate(), initializes a process-local Looper, runs the generic
Activity lifecycle, inflates common binary XML layouts into Views, and presents
the resulting tree through the WSLg/X11 host window.

This remains a single-process compatibility layer without system_server/Binder
service emulation. Arbitrary framework widgets, GPU rendering, and post-exit
input routing are outside this checkpoint's proof boundary.

`bin/minidroid-bridge` provides local package install/uninstall, package and
process lists, guarded process start/stop, per-package data/cache directories,
shared-folder records, logs, backup/restore, APK export, ADB server control and
the explicit Google-components policy. A failed Activity process is reported
as `MD_E_LAUNCH`, not as a running app.

## Chat GPT Marketplace installation

This repository contains a Codex marketplace at `.agents/plugins/marketplace.json`.
The marketplace entry points to the installable plugin at `./plugins/minidroid`.

Add the `plugin` branch as a marketplace:

```powershell
codex plugin marketplace add https://github.com/tarekwasfy01/MiniDroid---Minimal-Android-for-Linux.git --ref plugin
```

Then open the Plugins Directory, select `minidroid-marketplace`, and install
`MiniDroid`. After repository updates, refresh the marketplace:

```powershell
codex plugin marketplace upgrade minidroid-marketplace
```
