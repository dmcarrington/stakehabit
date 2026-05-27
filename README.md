# StakeHabit ⚡

**Put your sats where your habits are.**

A habit tracker with Bitcoin Lightning staking — stake real sats on your habits. Complete them and keep your sats. Fail, and they go to charity, a friend, or get burned. Loss aversion meets Lightning.

## Why?

Every habit tracker is just checking boxes. Gamification wears off. Streaks collapse. Nobody notices when you quit.

**Financial stakes work.** Research shows loss aversion increases goal adherence by **3.2×** compared to standard habit tracking. Beeminder proved the model with fiat. StakeHabit brings it to Bitcoin.

## Core Concept

1. **Create a habit** — "Meditate 10min daily"
2. **Stake sats** — Put 10,000 sats on the line
3. **Check in daily** — Confirm you did it (optional photo/NFC/location proof)
4. **Keep or lose** — Success keeps your sats, failure routes them to your chosen destination

## Features (Roadmap)

### MVP
- [ ] Create habits with custom frequency, targets, units
- [ ] Lightning staking — connect Alby or LDK node
- [ ] Daily check-in with optional photo proof
- [ ] Failure destinations: charity, burn, friend, community pool
- [ ] Basic streak and completion stats
- [ ] Biometric lock for stake confirmation
- [ ] Offline-first with local Room database

### V2
- [ ] Nostr integration — publish habit attestations, receive zaps
- [ ] AI habit coach — adaptive difficulty, failure pattern detection
- [ ] Mutual staking — stake against friends
- [ ] Community challenges — public pools
- [ ] Wear OS companion

## Tech Stack

- **Kotlin** + **Jetpack Compose** (Android)
- **Room** — local persistence
- **Hilt** — dependency injection
- **Lightning** — LDK / Alby OAuth
- **Nostr** — rust-nostr or pure Kotlin SDK
- **CameraX** — photo proof
- **Biometric** — secure stake confirmation

## Project Structure

```
app/src/main/java/com/stakehabit/
├── data/
│   ├── local/          # Room DB, DAOs, converters
│   ├── remote/         # Nostr relays, lightning API
│   └── repository/     # Data layer coordination
├── di/                 # Hilt modules
├── domain/
│   ├── model/          # Domain entities
│   └── usecase/        # Business logic
├── lightning/          # LDK/Alby integration
├── nostr/              # Nostr SDK wrappers
└── ui/
    ├── habits/         # Habit list, creation, check-in
    ├── staking/        # Stake management
    ├── stats/          # Analytics dashboard
    ├── settings/       # Wallet, Nostr, preferences
    └── onboarding/     # First-run flow
```

## Build

```bash
./gradlew assembleDebug
```

## License

MIT — open source, self-sovereign. Your habits, your sats, your keys.
