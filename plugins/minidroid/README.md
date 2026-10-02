# MiniDroid ChatGPT Plugin 1.0.1

Skill-only developer plugin for MiniDroid.

Official project repository:
https://github.com/tarekwasfy01/MiniDroid---Minimal-Android-for-Linux

Official legal pages and branding are referenced from the repository. Copies of the legal entry pages are bundled under `assets/site/`; the official project icon is referenced by its raw GitHub URL in plugin metadata and `OFFICIAL_PROJECT.md`.

## Marketplace installation

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
