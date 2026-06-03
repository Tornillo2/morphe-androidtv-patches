package app.morphe.patches.peacocktvandroidtv.ads

import app.morphe.patches.shared.compat.AppCompatibilities
import app.morphe.patcher.patch.bytecodePatch
import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.util.returnEarly

@Suppress("unused")
val skipAdsPatch = bytecodePatch(
    name = "Skip ads",
    description = "Disables ad delivery via five independent layers: MediaTailor SSAI proxy, " +
        "ObfuscatedProfileId SDK registry (Adobe, Comscore, Conviva, Freewheel, MParticle, " +
        "MediaTailor, Nielsen, OpenMeasurement), MediaTailor ad service constructor, " +
        "SSAI configuration provider (forces AdvertisingStrategy.None), and the Sky SDK " +
        "player engine ad break handler. Validated against v7.5.102.",
) {
    compatibleWith(AppCompatibilities.PEACOCK_TV_ANDROID_TV)

    execute {
        // ── Layer 1 ─────────────────────────────────────────────────────────
        // Kill MediaTailor SSAI proxy — empty string prevents proxy URL
        // configuration, disabling server-side ad insertion at the source.
        MediaTailorProxyHostFingerprint.method.addInstructions(
            0,
            """
                const-string v0, ""
                return-object v0
            """.trimIndent(),
        )

        // ── Layer 2 ─────────────────────────────────────────────────────────
        // Master kill switch — empty ObfuscatedProfileId array prevents all
        // 9 ad/analytics SDKs from being registered by the Sky SDK.
        ObfuscatedProfileIdValuesFingerprint.method.addInstructions(
            0,
            """
                const/4 v0, 0x0
                new-array v0, v0, [Lcom/sky/core/player/addon/common/data/ObfuscatedProfileId;
                return-object v0
            """.trimIndent(),
        )

        // ── Layer 3 ─────────────────────────────────────────────────────────
        // Abort MediaTailor ad service construction — returnEarly(null)
        // targets the factory method via its unique error string anchor,
        // surviving R8/D8 minification. Approach: RookieEnough/De-ReVanced.
        MediaTailorAdServiceMethodFingerprint.method.returnEarly(null)

        // ── Layer 4 ─────────────────────────────────────────────────────────
        // Force AdvertisingStrategy.None — getSsaiConfigurationProvider()
        // returning null causes Configuration$getDefaultAdvertisingStrategyProvider$1
        // .strategyForType() to take the confirmed if-eqz → None branch
        // for ALL playback types. AutomaticSSAI becomes unreachable. No crash.
        SsaiConfigurationProviderFingerprint.method.returnEarly(null)

        // ── Layer 5 ─────────────────────────────────────────────────────────
        // Kill ad breaks at the player engine level — immediately return-void
        // from handleAdBreakStarted() in PlayerEngineItemImpl so the Sky SDK
        // player never enters an ad break regardless of what upstream layers
        // may have missed. This is the last line of defense before the player
        // actually plays ad content.
        HandleAdBreakStartedFingerprint.method.addInstructions(
            0,
            "return-void",
        )
    }
}
