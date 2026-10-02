MiniDroid Zwischenstand — 2026-10-02

Enthalten:
- Android 17 x86_64 ART APEX-Inhalt
- Bionic/runtime APEX-Inhalt inkl. linker64
- I18N / ICU
- TZData
- Statsd-Native-Abhaengigkeiten
- ld.config.txt aus dem aktuellen portablen Test
- NOTICE-Dateien der enthaltenen APEX-Komponenten
- Log des erfolgreich bis Java main() gelaufenen DEX-Smoke-Tests

Status:
ART/Bionic/Dex-Ausfuehrung wurde auf der Build-Linux-Umgebung erreicht.
Der enthaltene Smoke-Test laedt classes.dex aus abx.jar und erreicht dessen main();
Exit 1 entsteht durch absichtlich fehlende Kommandoargumente der Test-App.

Dies ist ein Zwischenstand, noch NICHT der final minimierte/portable V1-Release.
