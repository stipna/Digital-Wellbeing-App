# ScreenLock — Digital Wellbeing App

Kotlin/Jetpack-Compose-Android-App gegen Mindless Scrolling und Prokrastination. Package `com.stephan.screenlock`, Min-SDK 26, Target/Compile-SDK 35.

## Status

Dieses Repo ist das erste zusammenhängende Gradle-Projekt für den Screen-Locker. Vorher wurden einzelne Features nur als Zip-Snippets ausgeliefert (kein Repo-Zugriff in den jeweiligen Sessions) — siehe `claude/project-setup.md` im verknüpften Claude-Projekt für die vollständige Liefer-Historie und den Stand jedes Features.

Der Code hier ist ein **kompilierbares Grundgerüst**: Architektur, Paketstruktur, Entities/DAOs, Repositories und Screens gemäß `project-setup.md`, mit `// TODO`-Markierungen an den Stellen, an denen die zuvor gelieferte, detailliertere Logik (Billing-Verifikation, Zeitlimit-Polling, Statistik-Aggregation, Onboarding-Animationen etc.) noch eingebaut werden muss. Wo Snippets aus früheren Lieferungen existieren, sind sie in `project-setup.md` referenziert.

## Fehlt noch (siehe `project-setup.md`, Abschnitt "Offene Punkte")

- Gradle-Wrapper (`gradlew`) — beim ersten Öffnen in Android Studio erzeugen lassen
- App-Icon (`@mipmap/ic_launcher` ist nur Platzhalter-Referenz)
- Play-Console-Setup (INAPP-Produkt `lifetime_access`, RSA-Public-Key für `PurchaseVerifier`)
- Diverse Detail-Logik, die als Zip an den Nutzer geliefert wurde, aber noch nicht in dieses Skeleton übertragen ist

## Setup

1. In Android Studio öffnen (Gradle-Wrapper wird automatisch erzeugt).
2. `google-services.json` / Billing-Keys sind nicht Teil dieses Repos.
