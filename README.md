# Game Land Client — Fabric 1.21.1

This is a **source project**, not a precompiled JAR. It targets Minecraft Java 1.21.1,
Fabric Loader, Fabric API, and Java 21 (ARM64 Java runtime supplied by PojavLauncher).

## Implemented in this starter
- Right Shift opens/closes the menu.
- Purple client HUD with FPS and coordinates toggles.
- Settings persist in `config/game-land-client.json`.
- The HUD is touch-friendly visually; Right Shift is the working open-menu shortcut in this first source build.
- Category navigation for Combat, Visual, Movement, Player, Misc, Render, Client.

## Build
Use JDK 21 and Gradle 8.10+:
`gradle build`

The output JAR will be in `build/libs/`.
Copy the JAR into:
`Android/data/net.kdt.pojavlaunch/files/.minecraft/mods/`

Install Fabric API for Minecraft 1.21.1 as well.

## Important
This starter intentionally implements safe HUD/client UI features only. It does not implement
automatic attacks, reach modification, kill aura, anti-knockback, server disruption, or exploit tools.
The UI artwork is a visual reference; this project is an initial functional foundation and should
be built and tested on the target PojavLauncher installation before use.
