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
 *   ✅ Between-innings ads  — PublicaBidListener.onAdBreakStarted() blocked +
 *                             CreateMediaItemWithAdsUseCase blocked upstream
 *   ➡️ Live games           — DAI untouched (createLiveStreamRequest path)
 *
 * SUSPEND FUNCTION STRATEGY:
 *   onAdBreakStarted and CreateMediaItemWithAdsUseCase are Kotlin coroutines.
 *   They compile to methods returning Ljava/lang/Object; with a Continuation
 *   parameter. return-void is invalid here — instead we return const/4 0x0
 *   (null Object), which causes the coroutine to complete with null result,
 *   effectively suppressing the ad break without crashing.
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
        // Returns a valid but empty zzcx StreamRequest → IMA SDK throws →
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
        // Patch 3: Between-Innings Ad Break — PublicaBidListener
        //
        // onAdBreakStarted is a Kotlin suspend function (returns Object).
        // return-object v0 with const/4 v0, 0x0 returns null, completing
        // the coroutine without executing the ad break body.
        //
        // Cancels: Publica auction → DAI pod metadata → dclk_video_ads
        // Expected: "Commercial Break - We'll be right back" instead of ads.
        //
        // If this fingerprint doesn't match (method signature differs),
        // comment it out — Patch 4 handles the upstream intercept.
        // ------------------------------------------------------------------
        PublicaAdBreakStartedFingerprint.method.addInstructions(
            0,
            """
                const/4 v0, 0x0
                return-object v0
            """.trimIndent(),
        )

        // ------------------------------------------------------------------
        // Patch 4: DAI/IMA Stream Init — CreateMediaItemWithAdsUseCase
        //
        // Controls both DAI API and IMA SDK ad stream initialization paths.
        // Sits upstream of between-innings ad segment requests.
        //
        // Also a suspend function — return null Object to suppress.
        //
        // NOTE: If this patch causes live game issues, comment it out.
        // The VOD patches (1a/1b) are independent and safe to keep.
        // ------------------------------------------------------------------
        CreateMediaItemWithAdsFingerprint.method.addInstructions(
            0,
            """
                const/4 v0, 0x0
                return-object v0
            """.trimIndent(),
        )
    }
}
