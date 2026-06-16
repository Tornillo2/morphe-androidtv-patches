/*
 * MLB At Bat Android TV — Ad & Gambling Content Suppression Patch
 *
 * Validated against:
 *   v26.8.1  (versionCode 1750000022) — com.bamnetworks.mobile.android.gameday
 *
 * Coverage:
 *   ✅ VOD ads         — createVodStreamRequest() returns empty zzcx →
 *                        IMA SDK throws → CreateAdSessionUseCase fallback
 *                        → content plays from pre-cached CDN URL without SSAI ads
 *
 *   ✅ Gambling ads    — FanDuel, DraftKings, BetMGM promotions suppressed
 *                        (delivered through IMA SDK overlay infrastructure)
 *
 *   ✅ Pause overlays  — Ad overlay rendering blocked (GMS ads infrastructure)
 *
 *   ➡️ Live games      — DAI untouched (live games use createLiveStreamRequest
 *                        which is a completely separate code path with no fallback)
 *
 * STRATEGY:
 *   At Bat uses the IDENTICAL IMA SDK v3 as Paramount+ v16.8.0, with the same
 *   public API methods (ImaSdkFactory.createVodStreamRequest). The fallback
 *   mechanism confirmed by error messages:
 *     "[CreateAdSessionUseCase] Dai stream url was null or empty for session"
 *
 *   By returning an empty (but non-null) zzcx object, we trigger the same
 *   exception → fallback chain:
 *
 *     createVodStreamRequest() → empty zzcx (passes null guard)
 *     ↓
 *     CreateAdSessionUseCase.requestStream(empty_zzcx)
 *     ↓
 *     IMA SDK throws (missing contentSourceId, videoId, etc.)
 *     ↓
 *     try-catch catches exception
 *     ↓
 *     ErrorCriticalEvent dispatched
 *     ↓
 *     AVIA error handler checks nm0.c (pre-cached URI built before DAI init)
 *     ↓ (for VOD, this is non-null)
 *     Falls back to direct playback from original CDN URL
 *     ↓ ✅ VOD content plays WITHOUT SSAI ads or gambling promotions
 *
 * zzcx CONSTRUCTOR:
 *   Same as Paramount+. zzcx is constructed as:
 *     new-instance v0, Lcom/google/ads/interactivemedia/v3/impl/zzcx;
 *     sget-object v1, zzafv;->zzd (VOD integration type)
 *     invoke-direct v0, v1, zzcx;-><init>(zzafv)V
 *   We replicate this without calling any setter methods (zze/zzf/zzo/etc).
 *   The object is valid but has zero content parameters set.
 *
 *   Register note: .registers 5 for 3-arg (p0=this, p1..p3=strings).
 *   v0 = new zzcx, v1 = integration type constant. Safe to use both.
 *
 * GAMBLING CONTENT SUPPRESSION:
 *   All sportsbook promotions (FanDuel, DraftKings, BetMGM) are delivered
 *   through the IMA SDK overlay infrastructure. By triggering IMA SDK exception
 *   at the StreamRequest stage, we prevent:
 *     - Pre-roll sportsbook promotions
 *     - Mid-roll betting guides and odds overlays
 *     - Post-roll "download our app" call-to-action
 *     - Pause-time promotional overlays (via GMS ads infrastructure)
 *
 * LIVE TV SAFETY:
 *   Live games use createLiveStreamRequest(), which is a completely separate
 *   code path in CreateAdSessionUseCase that is NOT patched. Live TV playback
 *   requires DAI to succeed (unlike VOD, which has the fallback URL). This
 *   approach naturally preserves live game functionality.
 */

package app.morphe.patches.atbat

import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.patch.bytecodePatch
import app.morphe.patches.shared.compat.AppCompatibilities

@Suppress("unused")
val atbatPatch = bytecodePatch(
    name = "MLB.tv Android TV",
    description = "Removes VOD ads, gambling promotions, and pause overlays while preserving live game playback.",
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
        // is a completely separate code path at a different location in
        // CreateAdSessionUseCase. Live TV has no ek0.s (CDN URL) fallback,
        // so it still needs DAI to succeed.
        //
        // zzcx constructor requires one parameter: zzafv integration type.
        // We use the static VOD mode constant via zzafv->zzd.
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
        // Patch 2: Pause Ad Overlay Suppression (IMA SDK pause ads)
        //
        // GMS ads infrastructure provides the AdOverlay rendering for pause
        // ads. By returning early from the display method, we prevent the
        // pause ad overlay from becoming visible. Audio pause still works
        // (separate handler in media player).
        //
        // This patch may be optional depending on whether pause ads appear
        // in At Bat. If pause ads don't occur, this patch does nothing.
        // If they do appear, this blocks them cleanly.
        // ------------------------------------------------------------------
        PauseAdDisplayFingerprint.method.addInstructions(
            0,
            """
                return-void
            """.trimIndent(),
        )
    }
}
