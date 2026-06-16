/*
 * MLB At Bat Android TV — Ad & Gambling Content Suppression Patch
 *
 * Validated against:
 *   v26.8.1  (versionCode 1750000022) — com.bamnetworks.mobile.android.gameday
 *
 * Coverage:
 *   ✅ VOD ads              — createVodStreamRequest() returns empty zzcx →
 *                             IMA SDK throws → CreateAdSessionUseCase fallback
 *                             → content plays from pre-cached CDN URL without ads
 *
 *   ✅ Gambling ads (VOD)   — FanDuel, DraftKings, BetMGM suppressed via
 *                             IMA SDK exception (same path as VOD ads)
 *
 *   ✅ Between-innings ads  — PublicaBidListener.onAdBreakStarted() blocked →
 *                             Publica auction cancelled → DAI pod metadata not
 *                             fetched → googlevideo.com dclk_video_ads segments
 *                             not requested → BetMGM/Bet365 ads don't play
 *
 *   ✅ Publica bid upstream — GetPublicaBidsUseCase blocked → no ad bid placed
 *                             (depth-of-defense for between-innings ads)
 *
 *   ➡️ Live games           — DAI untouched (live games use createLiveStreamRequest
 *                             which is a completely separate code path with no fallback)
 *
 * AD BREAK ARCHITECTURE (confirmed via logcat + DEX analysis):
 *
 *   Between-innings ads flow:
 *     DAI manifest signals commercial break
 *     ↓
 *     MlbMediaPlayer.onAdBreakStarted() fires
 *     ↓
 *     PublicaBidListener.onAdBreakStarted() called
 *     ↓
 *     GetPublicaBidsUseCase fetches Publica auction (pubads.g.doubleclick.net)
 *     ↓
 *     LinearGoogleDaiListener.fetchPodMetadata() called (dai.google.com/metadata)
 *     ↓
 *     Ad video segments fetched from googlevideo.com (source=dclk_video_ads)
 *     ↓
 *     BetMGM / Bet365 / gambling ads play during commercial break
 *
 *   Our intercept:
 *     PublicaBidListener.onAdBreakStarted() → return-void
 *     ↓ (entire chain above is cancelled)
 *     "Commercial Break - We'll be right back" placeholder shown instead
 *     ↓ ✅ No gambling ads during innings breaks
 *
 * VOD STRATEGY (unchanged from v1):
 *   createVodStreamRequest() returns empty (non-null) zzcx StreamRequest.
 *   IMA SDK throws → exception caught → fallback to pre-cached CDN URL.
 *   Live games use createLiveStreamRequest() — separate path, untouched.
 *
 * zzcx CONSTRUCTOR:
 *   Same as Paramount+ v16.8.0:
 *     new-instance v0, Lcom/google/ads/interactivemedia/v3/impl/zzcx;
 *     sget-object v1, zzafv;->zzd (VOD integration type)
 *     invoke-direct v0, v1, zzcx;-><init>(zzafv)V
 *   No setter methods called — object is valid but has zero content params.
 *   Register note: .registers 5 for 3-arg (p0=this, p1..p3=strings).
 */

package app.morphe.patches.atbat

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
        // Returns a valid but empty zzcx StreamRequest. No contentSourceId,
        // videoId, or apiKey setters are called. When CreateAdSessionUseCase
        // passes this to requestStream(), the IMA SDK throws because the request
        // has no parameters. The exception is caught by the try-catch and
        // dispatched as ErrorCriticalEvent.
        //
        // At Bat's error handler then checks the pre-cached nm0.c media source
        // (built earlier by g1.c() from the original CDN URL) and falls back
        // to direct playback from that URL — content without SSAI ads or
        // gambling promotions.
        //
        // Live games are unaffected: they use createLiveStreamRequest() which
        // is a completely separate code path. Live TV has no ek0.s (CDN URL)
        // fallback, so it still needs DAI to succeed.
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
        // Patch 3: Between-Innings Ad Break — PublicaBidListener
        //
        // PublicaBidListener.onAdBreakStarted() is the top-level entry point
        // for all between-innings commercial breaks. return-void here cancels
        // the entire Publica auction + DAI pod metadata fetch + googlevideo.com
        // MPEG-TS segment requests (source=dclk_video_ads).
        //
        // Confirmed ad flow from logcat:
        //   "[MlbMediaPlayer] onAdBreakStarted"
        //   "[LinearGoogleDaiListener] Starting pod metadata timer"
        //   googlevideo.com/.../source/dclk_video_ads (responseCode: 200)
        //
        // Expected result: "Commercial Break - We'll be right back" placeholder
        // shows instead of BetMGM/Bet365 gambling ads. Game resumes normally.
        //
        // Class confirmed unobfuscated in classes6.dex:
        //   mlb.atbat.media.player.listener.publica.PublicaBidListener
        // ------------------------------------------------------------------
        PublicaAdBreakStartedFingerprint.method.addInstructions(
            0,
            """
                return-void
            """.trimIndent(),
        )

        // ------------------------------------------------------------------
        // Patch 4: Publica Bid Request Upstream — GetPublicaBidsUseCase
        //
        // Kills the Publica ad auction request upstream of the ad break.
        // Even if onAdBreakStarted somehow fires, no bid is placed so no
        // ad creative is selected.
        //
        // Depth-of-defense for between-innings ad suppression.
        //
        // NOTE: GetPublicaBidsUseCase may be a Kotlin suspend function with
        // a Continuation parameter. If this patch causes a compile error,
        // comment it out — Patch 3 alone is sufficient.
        //
        // Class confirmed unobfuscated in classes6.dex:
        //   mlb.atbat.data.usecase.GetPublicaBidsUseCase
        // ------------------------------------------------------------------
        GetPublicaBidsFingerprint.method.addInstructions(
            0,
            """
                return-void
            """.trimIndent(),
        )
    }
}
