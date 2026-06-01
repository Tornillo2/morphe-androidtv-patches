/*
 * Paramount+ Android TV — Ad Suppression Patch
 *
 * Validated against:
 *   v16.8.0  (versionCode 520000464) — com.cbs.ott
 *   v16.12.0 (versionCode 520000571) — com.cbs.ott
 *
 * What this patch covers:
 *   ✅ VOD SSAI pre-roll / mid-roll ads (IMA DAI — both overloads)
 *   ✅ Pause ads (CbsPauseWithAdsOverlay)
 *   ➡️ Live TV — intentionally untouched (see note below)
 *
 * LIVE TV NOTE:
 *   createLiveStreamRequest() sets the DAI asset key which IS the channel
 *   identity — it is used to obtain the stitched HLS manifest URL from
 *   saa.paramountplus.com. Patching it produces a blank StreamRequest,
 *   causing yk0.run() to hit the "DAI AdsLoader or StreamRequest is null"
 *   guard and producing a black screen. Live TV ad suppression requires
 *   a different approach (SSAI segment-level or DNS layer).
 */

package app.morphe.patches.paramount

import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.patch.bytecodePatch
import app.morphe.patches.shared.compat.AppCompatibilities

@Suppress("unused")
val paramountPatch = bytecodePatch(
    name = "Paramount+ Android TV",
    description = "Removes VOD ads and pause ads.",
) {
    compatibleWith(AppCompatibilities.PARAMOUNT_TV)

    execute {
        // ------------------------------------------------------------------
        // Patch 1 & 2: VOD SSAI — createVodStreamRequest (3-arg and 4-arg)
        //
        // Both overloads construct a zzcx StreamRequest object then call a
        // chain of setters that populate the IMA DAI ad parameters:
        //   zze() — contentSourceId  (identifies the ad-supported content)
        //   zzf() — videoId          (identifies the specific video asset)
        //   zzo() — apiKey           (authenticates against the DAI service)
        //   zzg() — networkCode      (4-arg only — ad network identifier)
        //
        // These four values are what saa.paramountplus.com uses to compose
        // the SSAI manifest. Without them the DAI service returns no ads.
        //
        // Strategy: prepend a new-instance + init + return-object at offset 0.
        // The original setter chain is never reached. The returned zzcx object
        // is valid and properly typed so all null checks in the caller pass —
        // the request just carries no ad parameters.
        //
        // Register usage (v0, v1) is safe in both overloads:
        //   3-arg: .registers 5 — v0..v4 available, params at v2..v4
        //   4-arg: .registers 6 — v0..v5 available, params at v2..v5
        // ------------------------------------------------------------------
        arrayOf(
            VodStreamRequest3ArgFingerprint,
            VodStreamRequest4ArgFingerprint,
        ).forEach { fingerprint ->
            fingerprint.method.addInstructions(
                0,
                """
                    new-instance v0, Lcom/google/ads/interactivemedia/v3/impl/zzcx;
                    sget-object v1, Lcom/google/ads/interactivemedia/v3/internal/zzafv;->zzd:Lcom/google/ads/interactivemedia/v3/internal/zzafv;
                    invoke-direct {v0, v1}, Lcom/google/ads/interactivemedia/v3/impl/zzcx;-><init>(Lcom/google/ads/interactivemedia/v3/internal/zzafv;)V
                    return-object v0
                """.trimIndent(),
            )
        }

        // ------------------------------------------------------------------
        // Patch 3: Pause ads — CbsPauseWithAdsOverlay state machine
        //
        // This public static method is the sole dispatcher for the pause ad
        // overlay. It receives a sealed state object and branches:
        //
        //   URL state  → Glide.with(ctx).load(url) → animate alpha 0→1 (1000ms)
        //   File state → Glide.with(ctx).load(file) → sized image load
        //   Reset state → O(0) → animate alpha 1→0
        //   Fallthrough → logs "renderState: X not updating overlay." (no-op)
        //
        // The overlay view is constructed independently and always exists at
        // alpha=0. Patching here means:
        //   - No Glide network request for the ad image is ever made
        //   - The alpha fade-in to 1.0 is never triggered
        //   - The overlay stays invisible (alpha=0) for the duration of pause
        //   - No NPE risk — the overlay View object and its key event handler
        //     (N / Q) are unaffected and still functional
        //
        // This is architecturally equivalent to the Paramount+ phone-side
        // Innovid pause ad suppression confirmed in the AGP ruleset, but
        // applied at the rendering layer rather than the network layer —
        // making it effective even without DNS filtering or root.
        //
        // Strategy: return-void at offset 0. The entire state machine is
        // skipped. No register allocation changes needed (.registers 6,
        // v0 is unused before any branch).
        // ------------------------------------------------------------------
        PauseAdOverlayFingerprint.method.addInstructions(
            0,
            """
                return-void
            """.trimIndent(),
        )
    }
}
