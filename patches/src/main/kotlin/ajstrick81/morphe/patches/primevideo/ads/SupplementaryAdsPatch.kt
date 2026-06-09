package ajstrick81.morphe.patches.primevideo.ads

import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.patch.bytecodePatch
import ajstrick81.morphe.patches.primevideo.shared.Constants

// ─────────────────────────────────────────────────────────────────────────────
// SUPPLEMENTARY PATCH — adds two new hooks from classes2.dex analysis
//
// Hook 1: AdsMediaSource$ComponentListener.onAdPlaybackState()
//   Plugs the CSAI gap left by our existing SSAI-only hooks. Together they
//   cover both ad delivery paths in the media3 library.
//
// Hook 2: GoogleAdvertisingProperties.isAdvertisingOptOut()
//   Forces the advertising opt-out flag to true, signaling to the WASM
//   runtime that this device has opted out of advertising. This operates
//   at a higher level than the SSAI/CSAI hooks — potentially preventing
//   the ad schedule from being built at all in the WASM layer.
//
// These two hooks complement the existing skipAdsPatch rather than
// replacing it. Both patches should be active simultaneously.
//
// Named supplementaryAdsPatch to avoid top-level val naming conflict.
// ─────────────────────────────────────────────────────────────────────────────

@Suppress("unused")
val supplementaryAdsPatch = bytecodePatch(
    name = "Skip ads (supplementary)",
    description = "Adds CSAI ad suppression and advertising opt-out hooks to complement the primary Skip ads patch.",
) {
    compatibleWith(Constants.COMPATIBILITY)

    execute {

        // ─────────────────────────────────────────────────────────────────────
        // Hook 1 — AdsMediaSource$ComponentListener.onAdPlaybackState()
        //
        // Closes the CSAI delivery gap. The existing setAdPlaybackStates
        // hooks cover the SSAI path. This covers the CSAI path where
        // AdsLoader pushes a client-side AdPlaybackState to the player.
        //
        // The stopped flag check (iget-boolean v0 stopped:Z) fires first
        // in the original method — our return-void at index 0 fires before
        // that check, giving unconditional suppression regardless of
        // listener state.
        // ─────────────────────────────────────────────────────────────────────
        AdsMediaSourceComponentListenerFingerprint.method.addInstructions(
            0,
            """
                return-void
            """
        )

        // ─────────────────────────────────────────────────────────────────────
        // Hook 2 — GoogleAdvertisingProperties.isAdvertisingOptOut()
        //
        // Forces advertising opt-out to true. The WASM runtime reads this
        // via IgniteDevicePropertiesProvider under the property key
        // "isAdvertisingOptOut" and uses it to decide whether to serve ads.
        //
        // The original method's exception handler already returns true
        // (const/4 v0, 0x1 / return v0) — we replicate this unconditionally
        // at index 0. No extension class needed — pure inline smali.
        //
        // return 1 (true = opted out of advertising)
        // ─────────────────────────────────────────────────────────────────────
        IsAdvertisingOptOutFingerprint.method.addInstructions(
            0,
            """
                const/4 v0, 0x1
                return v0
            """
        )
    }
}
