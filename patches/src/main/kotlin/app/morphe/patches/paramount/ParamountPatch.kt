/*
 * Paramount+ Android TV — Ad Suppression Patch
 *
 * Validated against:
 *   v16.8.0  (versionCode 520000464) — com.cbs.ott
 *   v16.12.0 (versionCode 520000571) — com.cbs.ott
 *
 * Coverage:
 *   ✅ Pause ads     — CbsPauseWithAdsOverlay state machine
 *   ✅ Pre-roll CSAI — createAdsRequest() + ||cbsaavideo.com^ DNS rule
 *   ✅ Mid-roll SSAI — AD_STARTED handler → immediate ad skip seek
 *   ➡️ Live TV ads   — skip guard in g() blocks live ad skipping by design;
 *                      live TV ads require a different approach
 *
 * REQUIRED AGH ALLOWLIST (Onn 4K TV — com.cbs.ott):
 *   @@||pubads.g.doubleclick.net^$app=com.cbs.ott
 *   @@||imasdk.googleapis.com^$app=com.cbs.ott
 *   @@||dai.google.com^$app=com.cbs.ott
 *   @@||cbs.hb-api.omtrdc.net^$app=com.cbs.ott
 *
 * REQUIRED AGH BLOCKLIST ADDITIONS:
 *   ||cbsaavideo.com^
 *   ||*.cbsaavideo.com^
 */

package app.morphe.patches.paramount

import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.patch.bytecodePatch
import app.morphe.patches.shared.compat.AppCompatibilities

@Suppress("unused")
val paramountPatch = bytecodePatch(
    name = "Paramount+ Android TV",
    description = "Removes pause ads, pre-roll ads, and mid-roll ads.",
) {
    compatibleWith(AppCompatibilities.PARAMOUNT_TV)

    execute {
        // ------------------------------------------------------------------
        // Patch 1: Pause ads — CbsPauseWithAdsOverlay state machine
        //
        // Dispatcher for the pause ad overlay renderer. return-void at
        // offset 0 prevents Glide image fetch, alpha fade-in, and overlay
        // render event from ever firing. Overlay view stays at alpha=0.
        // ------------------------------------------------------------------
        PauseAdOverlayFingerprint.method.addInstructions(
            0,
            """
                return-void
            """.trimIndent(),
        )

        // ------------------------------------------------------------------
        // Patch 2: Pre-roll CSAI — createAdsRequest()
        //
        // Returns a fresh empty AdsRequestImpl before the original code
        // runs. The CSAI state machine receives a valid (non-null) object
        // so no NPE occurs, but cbsaavideo.com is never contacted because
        // the DNS rule ||cbsaavideo.com^ blocks the ad video CDN.
        // Two-layer suppression: smali intercept + DNS block.
        // ------------------------------------------------------------------
        CsaiAdRequestFingerprint.method.addInstructions(
            0,
            """
                new-instance v0, Lcom/google/ads/interactivemedia/v3/impl/AdsRequestImpl;
                invoke-direct {v0}, Lcom/google/ads/interactivemedia/v3/impl/AdsRequestImpl;-><init>()V
                return-object v0
            """.trimIndent(),
        )

        // ------------------------------------------------------------------
        // Patch 3: Mid-roll SSAI — inject skip call at end of AD_STARTED
        //
        // PCAPdroid confirmed mid-roll ads are SSAI stitched into the
        // googlevideo.com content manifest. dai.google.com fires per-break
        // (~7-8KB) at each cue point. The IMA StreamManager delivers
        // AD_BREAK_STARTED → AD_STARTED events to cl0's AdEventListener.
        //
        // cl0.f(Ad) (handleAdStarted) is the injection point because by the
        // time it completes, all conditions for the skip method (cl0.g) are
        // satisfied:
        //   dl0.i = true  — set by d() (AD_BREAK_STARTED)
        //   dl0.j = true  — set at the top of f()
        //   dl0.h = ek0   — set mid-f() via b(Ad); ek0.c = ad duration ms
        //
        // The skip method (cl0.g) computes:
        //   seekTarget = (ek0.c - bo0.u) + bo0.c
        //   n1.q0(seekTarget)  ← player seek past ad segment
        //
        // Both method names (f and g) are obfuscated and drift between
        // versions. AdSkipFingerprint resolves the skip method's class
        // descriptor and name at patch time from the stable anchor string
        // "skip ad is not supported for live streams", making this injection
        // resilient to future obfuscation changes.
        //
        // Register: p0 (this = cl0 instance) is computed dynamically from
        // registerCount - parameterCount to survive register layout changes.
        //
        // Injection position: instructions.size - 1 (just before the final
        // return-void), after dl0.h is fully populated by b(Ad).
        //
        // Live TV: cl0.g() has a live stream guard that fires first for
        // linear content — this patch has no effect on live TV playback.
        // ------------------------------------------------------------------
        val skipClass = AdSkipFingerprint.method.definingClass
        val skipMethod = AdSkipFingerprint.method.name

        val adStartedMethod = AdStartedFingerprint.method
        val thisRegister = adStartedMethod.implementation!!.registerCount -
            adStartedMethod.parameterTypes.size - 1

        adStartedMethod.addInstructions(
            adStartedMethod.implementation!!.instructions.size - 1,
            "invoke-virtual { v$thisRegister }, $skipClass->$skipMethod()V",
        )
    }
}
