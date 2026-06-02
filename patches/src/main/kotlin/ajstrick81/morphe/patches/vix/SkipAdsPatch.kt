package com.ajstrick81.patches.vix.ads

import app.morphe.patcher.patch.bytecodePatch
import app.morphe.patcher.patch.BytecodePatchContext
import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.extensions.InstructionExtensions.getInstruction
import app.morphe.patcher.extensions.InstructionExtensions.replaceInstruction
import app.morphe.patcher.util.returnEarly
import com.brterabi.dalvik.dex.Opcode

// ---------------------------------------------------------------------------
// SkipAdsPatch — ViX Android TV (com.univision.prendetv) v4.46.0_tv
//
// Five independent layers, each targeting a different stage of the ad
// pipeline.  Any single layer may survive an app update; together they
// provide defence-in-depth:
//
//   Layer 1 — Zero-out LuraFreeWheel config (ad scheduler sees disabled=false)
//   Layer 2a — Nullify LuraAdsConfiguration ad URL macro bag
//   Layer 2b — Force LuraAdsPolicySurrogate to maximum skip permissiveness
//   Layer 3 — Stub InnovidHelper.startAd() (SSAI overlay never mounts)
//   Layer 4 — Auto-complete AdsUI countdown (skip countdown → 0 immediately)
//   Layer 5 — Stub VideoPlayerFragment ad-position ticker coroutine
// ---------------------------------------------------------------------------
val skipAdsPatch = bytecodePatch(
    name = "Skip ads",
    description = "Removes ad breaks and the ad countdown overlay from ViX Android TV.",
) {
    compatibleWith(PACKAGE_NAME to listOf(COMPATIBLE_VERSION))

    use(luraFreewheelConfigFingerprint)
    use(luraAdsConfigFingerprint)
    use(luraAdsPolicyFingerprint)
    use(innovidStartAdFingerprint)
    use(adsUiCountdownFingerprint)
    use(videoPlayerAdJobFingerprint)

    execute {

        // -------------------------------------------------------------------
        // LAYER 1 — LuraFreewheelConfiguration.enabled = false
        //
        // The `enabled` field is a Kotlin Boolean stored as a JVM boolean (Z).
        // The class initialiser copies a constructor parameter into the field
        // with iput-boolean.  We inject `const/4 v0, 0x0` before the first
        // iput-boolean so the field is always written as false, regardless of
        // what the app's config JSON says.
        // -------------------------------------------------------------------
        luraFreewheelConfigFingerprint.method.apply {
            val iputIndex = implementation!!.instructions.indexOfFirst {
                it.opcode == Opcode.IPUT_BOOLEAN
            }
            if (iputIndex >= 0) {
                // Force the source register to 0 (false) before the field write.
                addInstructions(
                    iputIndex,
                    """
                    const/4 v0, 0x0
                    """.trimIndent(),
                )
            }
        }

        // -------------------------------------------------------------------
        // LAYER 2a — LuraAdsConfiguration: return early from constructor
        //
        // By returning at the very start of the constructor we leave the
        // `macros` and `adUrl` fields at their Kotlin default values
        // (null / empty).  The scheduler will see no URLs and schedule nothing.
        // -------------------------------------------------------------------
        luraAdsConfigFingerprint.method.returnEarly()

        // -------------------------------------------------------------------
        // LAYER 2b — LuraAdsPolicySurrogate: return early from constructor
        //
        // Leaves skipMode at its default (most permissive) enum value so that
        // any ad that somehow starts playing is immediately skippable.
        // -------------------------------------------------------------------
        luraAdsPolicyFingerprint.method.returnEarly()

        // -------------------------------------------------------------------
        // LAYER 3 — InnovidHelper.startAd(): return early
        //
        // Prevents the Innovid WebView overlay from ever being mounted.
        // The method is a suspend function (coroutine); returnEarly() inserts
        // `return-void` at index 0, which is safe for Unit-returning suspends.
        // -------------------------------------------------------------------
        innovidStartAdFingerprint.method.returnEarly()

        // -------------------------------------------------------------------
        // LAYER 4 — AdsUI countdown coroutine: collapse delay to zero
        //
        // The countdown body calls `delay(intervalMs)` in a loop.  We locate
        // the first invoke-static/invoke-virtual that references the delay
        // value register and replace its argument with const/16 0x0 so the
        // coroutine yields immediately on every tick, draining the countdown
        // in a single frame and surfacing the skip button at t=0.
        //
        // The const/16 is injected two instructions before the delay call so
        // it overwrites the delay-duration register cleanly.
        // -------------------------------------------------------------------
        adsUiCountdownFingerprint.method.apply {
            val delayCallIndex = implementation!!.instructions.indexOfFirst {
                it.opcode == Opcode.INVOKE_STATIC || it.opcode == Opcode.INVOKE_VIRTUAL
            }
            if (delayCallIndex >= 1) {
                // Replace the long constant holding the delay duration with 0.
                replaceInstruction(
                    delayCallIndex - 1,
                    "const/16 v0, 0x0",
                )
            }
        }

        // -------------------------------------------------------------------
        // LAYER 5 — VideoPlayerFragment.startAdUpdateJob: return early
        //
        // Stops the coroutine that feeds ad-position ticks into the LuraPlayer
        // SDK.  Without position ticks the ad engine cannot fire ad-break
        // transitions.  This is the outermost gate — it fires before any ad
        // URL is fetched for pre-roll scheduling.
        // -------------------------------------------------------------------
        videoPlayerAdJobFingerprint.method.returnEarly()
    }
}
