/*
 * MLB At Bat Android TV — Ad & Gambling Content Suppression Patch
 *
 * Validated against:
 *   v26.8.1  (versionCode 1750000022) — com.bamnetworks.mobile.android.gameday
 *
 * Coverage:
 *   ✅ VOD ads              — createVodStreamRequest() returns empty zzcx →
 *                             IMA SDK throws → fallback to pre-cached CDN URL
 *   ✅ Gambling ads (VOD)   — FanDuel, DraftKings, BetMGM via IMA SDK path
 *   ✅ Between-innings ads  — Ad break entry point (Lv70/k;.b) blocked →
 *                             adMetadata/podMetadata chain cancelled
 *   ✅ DAI pod segments     — LinearGoogleDaiListener pod metadata timer
 *                             blocked → googlevideo.com dclk_video_ads
 *                             segments not fetched (depth-of-defense)
 *   ➡️ Live games           — DAI untouched (createLiveStreamRequest path)
 *
 * FINGERPRINT VERIFICATION:
 *   All fingerprints verified via full APK bytecode analysis.
 *   Exact class names, method signatures, and string references confirmed.
 *
 *   Patch 3 — Lv70/k;.b(Lo60/c$c; Lo60/c; Z)V
 *     Plain void method (NOT suspend). return-void is correct.
 *     Strings in body: "[MlbMediaPlayer] onAdBreakStarted", "adMetadata",
 *     "podMetadata", "breakStarted", "PlayerAdEventListener"
 *
 *   Patch 4 — Lz70/i;.z()V
 *     Plain void method, no parameters, single string ref.
 *     return-void cancels DAI pod metadata fetch.
 */

package app.morphe.patches.mlbtv

import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.patch.bytecodePatch
import app.morphe.patches.shared.compat.AppCompatibilities

@Suppress("unused")
val atbatPatch = bytecodePatch(
    name = "MLB At Bat Android TV",
    description = "Removes VOD ads, between-innings gambling ads, and sportsbook promotions while preserving live game playback.",
) {
    compatibleWith(AppCompatibilities.MLB_TV)

    execute {
        // ------------------------------------------------------------------
        // Patch 1: VOD SSAI & Gambling Content — createVodStreamRequest
        //
        // Returns valid but empty zzcx StreamRequest → IMA SDK throws →
        // exception caught → fallback to pre-cached CDN URL (nm0.c).
        // Live games use createLiveStreamRequest() — separate path, untouched.
        // ------------------------------------------------------------------
        VodStreamRequest3ArgFingerprint.method.addInstructions(
            0,
            """
                new-instance v0, Lcom/google/ads/interactivemedia/v3/impl/zzcx;
                sget-object v1, Lcom/google/ads/interactivemedia/v3/internal/zzafv;->zzd:Lcom/google/ads/interactivemedia/v3/internal/zzafv;
                invoke-direct {v0, v1}, Lcom/google/ads/interactivemedia/v3/impl/zzcx;-><init>(Lcom/google/ads/interactivemedia/v3/internal/zzafv;)V
                return-object v0
            """.trimIndent(),
        )

        VodStreamRequest4ArgFingerprint.method.addInstructions(
            0,
            """
                new-instance v0, Lcom/google/ads/interactivemedia/v3/impl/zzcx;
                sget-object v1, Lcom/google/ads/interactivemedia/v3/internal/zzafv;->zzd:Lcom/google/ads/interactivemedia/v3/internal/zzafv;
                invoke-direct {v0, v1}, Lcom/google/ads/interactivemedia/v3/impl/zzcx;-><init>(Lcom/google/ads/interactivemedia/v3/internal/zzafv;)V
                return-object v0
            """.trimIndent(),
        )

        // ------------------------------------------------------------------
        // Patch 3: Between-Innings Ad Break Entry Point
        //
        // Verified: Lv70/k;.b(Lo60/c$c; Lo60/c; Z)V
        // Plain void method — return-void is correct.
        // Cancels adMetadata + podMetadata chain → no dclk_video_ads fetched.
        // Expected: commercial break placeholder shown instead of gambling ads.
        // ------------------------------------------------------------------
        AdBreakStartedFingerprint.method.addInstructions(
            0,
            """
                return-void
            """.trimIndent(),
        )

        // ------------------------------------------------------------------
        // Patch 4: DAI Pod Metadata Timer — LinearGoogleDaiListener
        //
        // Verified: Lz70/i;.z()V
        // Plain void method, no parameters.
        // Depth-of-defense: prevents googlevideo.com ad segment requests
        // even if Patch 3 is somehow bypassed.
        // ------------------------------------------------------------------
        LinearDaiPodMetadataFingerprint.method.addInstructions(
            0,
            """
                return-void
            """.trimIndent(),
        )
    }
}
