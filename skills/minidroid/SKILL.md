---
name: minidroid
description: Use for MiniDroid, the bundled minidroid-x86_64 runtime, MiniDroidManager.apk, ART/Bionic/linker64 bootstrapping, APK lifecycle, Android resources/views, MiniDroid bridges, package/process/storage management, ADB integration, add-ons, Android compatibility packs, runtime testing, debugging, patching and release work.
---

# MiniDroid

MiniDroid is a portable Android compatibility runtime for Linux x86_64. It is not a full Android OS, emulator, VM, WSA clone, or containerized system image.

## Prime directive

Preserve the architectural goal:

**As much Android compatibility as possible, as little Android system as necessary.**

The working runtime path is conceptually:

`Linux -> Android linker64 -> Bionic -> ART -> Java core/framework -> DEX/APK -> MiniDroid host bridges`

Do not replace this with a full Android boot, system_server stack, Android VM, or emulator merely because that would be easier.

## Bundled runtime

The plugin includes the user-supplied runtime archive at:

`assets/minidroid-x86_64.zip`

Treat that archive as the project snapshot to inspect, test, patch or extract when the task needs concrete files. Do not invent files or APIs that are not present.

Before making implementation claims, inspect the current runtime and tests. Prefer evidence from the bundled files and actual executions over assumptions.

## Existing snapshot structure

The bundled archive contains, among other things:

- `bin/minidroid`
- `bin/minidroid-bridge`
- `bin/adb`
- `manager/MiniDroidManager.apk`
- `framework/framework.jar`
- `framework/framework-res.apk`
- `framework/minidroid-framework-patch.jar`
- `host/minidroid-window-x11`
- Android ART/Bionic/APEX runtime files under `rootfs/`
- tests under `TESTS/`
- `MANIFEST.sha256`
- license and third-party notice material

Read `references/PROJECT_STATE.md` for the intended engineering baseline and open work.

## Engineering rules

1. Inspect first. Do not patch blindly.
2. Preserve all working ART/Bionic/linker behavior.
3. Create a checkpoint before risky changes.
4. After each substantial change, rerun relevant regression tests.
5. Never report a UI button or backend as functional unless the actual operation succeeds.
6. Do not hide missing behavior behind unconditional PASS messages or fake return values.
7. Use explicit, diagnosable error codes for unimplemented or failed operations.
8. Keep the runtime relocatable; do not introduce absolute build paths.
9. No root, chroot, host `/apex` mount, Android SDK/JDK, Python or build tree may be required at runtime unless the user explicitly changes that requirement.
10. Respect the core compressed-size target of <=100 MiB; prefer leaving reserve rather than filling it.
11. Keep runtime data, app data, add-ons and imported images separate.
12. Preserve and update license/provenance metadata when adding binaries or libraries.

## APK runtime

An APK is not a JAR. Treat APK execution as:

`APK -> AndroidManifest.xml -> package/application/activity -> resources/assets/native libs -> framework -> MiniDroid host backend`

When debugging APK startup, separate these failure domains:

- manifest / binary AXML
- DEX / multidex
- Application bootstrap
- Looper/lifecycle
- Activity attach/state
- resources.arsc
- binary XML layout
- Views/widgets
- host window backend
- input
- JNI/native libraries
- missing framework APIs/services

Do not introduce Binder/system_server simply to satisfy one incidental framework initialization if a small compatible local façade is sufficient and semantically safe.

## GUI architecture

The product manager remains an Android app:

`MiniDroidManager.apk -> Application -> MainActivity -> Android Views -> MiniDroid graphics bridge -> Linux window`

Do not replace the product manager with Qt, GTK, Tk or another native GUI. Host-native helpers are acceptable as backend implementation details.

Wayland is preferred where practical; X11 is an acceptable fallback. The bundled snapshot includes an X11 host window helper.

## Manager bridge

`MiniDroidSystem` / `minidroid-bridge` is the privileged path for manager operations.

Privileged operations include:

- package management
- process management
- storage/data/cache
- backup/restore
- logs/crash information
- runtime health/doctor
- compatibility scans
- display profile
- shared folders
- ADB control
- add-on management
- system-image/compatibility-pack management

Do not rely on package name alone for privilege. Normal APKs must not receive manager privileges.

## Package/process behavior

Package operations should be transactional where practical.

Install should validate the APK, parse package/version/ABI, register DEX, extract compatible native libraries, create app data and update package state.

Update should preserve app data unless explicitly requested otherwise.

Uninstall should distinguish removing the app from removing app+data.

A launch operation must only be reported successful when the runtime actually started the app. Do not treat a headless bootstrap that immediately exits as a running GUI application.

## Data isolation

Keep per-app data outside the immutable core, for example under a user data root with separate package directories for files/cache/code/native state.

Apps must not automatically receive unrestricted access to the Linux home directory. Shared folders are explicit user grants.

## ADB

Bundling `adb` alone is not sufficient for MiniDroid to be an ADB target.

A complete MiniDroid ADB path requires the device side as well: transport, authentication, shell bridge, package bridge and logging bridge (and adbd or a compatible implementation).

Target behavior includes:

- `adb devices` sees MiniDroid
- `adb shell`
- `adb install`
- `adb uninstall`
- `adb logcat`

Prefer local transport first. Do not expose TCP 5555 by default. Network ADB must require explicit opt-in.

## Add-ons and Android images

The core runtime is immutable from the add-on system's point of view. Add-ons live separately, are verifiable, enable/disable cleanly, uninstall cleanly, and roll back on failure.

A full Android image is only a **component source / compatibility pack input**. It must not replace the MiniDroid architecture or cause MiniDroid to boot a complete Android system.

Image workflow:

`inspect/import -> verify hashes -> identify format/partitions/APEX/framework/native libraries -> determine useful dependency closure -> build separate add-on -> test -> activate`

Do not blindly copy a system image over the core runtime.

## Google components

The MiniDroid core remains Google-free. Proprietary Google components must not be redistributed as part of the core. Any optional installation must be explicit, removable, license/source aware and use a lawful source.

## Testing discipline

Use targeted gates while developing, then run the wider release suite.

Core gates should include linker/Bionic/ART/Java/DEX, relocation, JNI/System.loadLibrary, APK manifest/multidex/native ABI, Application/Activity, resources/XML/View rendering, manager boot, package install/launch/stop/uninstall, data isolation, add-ons and any ADB/GLES/audio feature that is claimed supported.

A crash or unsupported API should produce an actionable diagnostic instead of silently succeeding.

## Release discipline

Before calling a build finished:

- build from the verified runtime tree
- verify `MANIFEST.sha256`
- verify manager APK integrity/signature where applicable
- run `minidroid doctor`
- run relocation smoke tests
- test the manager as an Android app
- test real package operations
- update implemented/test/size/license/limitations reports
- ensure the compressed core stays within the configured size budget

## Working style for user requests

When the user provides a newer MiniDroid ZIP, source tree or log, treat it as authoritative over this bundled snapshot.

When asked to "fix everything", do not respond only with a plan. Inspect the project, make the highest-value concrete fixes possible, run tests, report exact remaining blockers, and produce a new archive when file-editing tools are available.

When the user asks for commands, prefer complete copy-pasteable commands and avoid requiring them to program manually.
