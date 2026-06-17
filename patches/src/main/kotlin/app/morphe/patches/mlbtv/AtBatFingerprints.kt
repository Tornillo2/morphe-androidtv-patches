/*
 * MLB At Bat Android TV — Ad Patch Fingerprints
 *
 * Validated against:
 *   v26.8.1  (versionCode 1750000022) — com.bamnetworks.mobile.android.gameday
 *
 * PATCH COVERAGE:
 *
 *   Patch 1a/1b — VOD SSAI & Gambling Ads (createVodStreamRequest)
 *     At Bat uses identical IMA SDK v3 as Paramount+ v16.8.0.
 *     Empty zzcx → IMA SDK throws → fallback to pre-cached CDN URL.
 *     Live games use createLiveStreamRequest() — separate path, untouched.
 *
 *   Patch 3 — Between-Innings Ad Break (PublicaBidListener.onAdBreakStarted)
 *     Confirmed via logcat + DEX analysis (classes6.dex):
 *       "[MlbMediaPlayer] onAdBreakStarted"
 *       "[LinearGoogleDaiListener] Starting pod metadata timer"
 *       googlevideo.com/.../source/dclk_video_ads (responseCode: 200)
 *     return-void cancels Publica auction + DAI pod metadata + dclk_video_ads segments.
 *     BetMGM/Bet365 gambling ads replaced by "Commercial Break - We'll be right back".
 *
 *   Patch 4 — Publica Bid Upstream (GetPublicaBidsUseCase)
 *     Depth-of-defense: kills ad bid request upstream of ad break trigger.
 *     If this causes compile error (suspend function), comment it out —
 *     Patch 3 alone is sufficient.
 */

package app.morphe.patches.mlbtv

import app.morphe.patcher.Fingerprint

// ---------------------------------------------------------------------------
// Patch 1a: VOD SSAI & Gambling Ads — createVodStreamRequest (3-arg)
// ---------------------------------------------------------------------------

internal object VodStreamRequest3ArgFingerprint : Fingerprint(
    returnType = "Lcom/google/ads/interactivemedia/v3/api/StreamRequest;",
    custom = { method, _ ->
        method.name == "createVodStreamRequest" &&
            method.definingClass ==
                "Lcom/google/ads/interactivemedia/v3/api/ImaSdkFactory;" &&
            method.parameterTypes.size == 3 &&
            method.parameterTypes.all { it == "Ljava/lang/String;" }
    },
)

// ---------------------------------------------------------------------------
// Patch 1b: VOD SSAI & Gambling Ads — createVodStreamRequest (4-arg)
// ---------------------------------------------------------------------------

internal object VodStreamRequest4ArgFingerprint : Fingerprint(
    returnType = "Lcom/google/ads/interactivemedia/v3/api/StreamRequest;",
    custom = { method, _ ->
        method.name == "createVodStreamRequest" &&
            method.definingClass ==
                "Lcom/google/ads/interactivemedia/v3/api/ImaSdkFactory;" &&
            method.parameterTypes.size == 4 &&
            method.parameterTypes.all { it == "Ljava/lang/String;" }
    },
)

// ---------------------------------------------------------------------------
// Patch 3: Between-Innings Ad Break — PublicaBidListener.onAdBreakStarted
//
// Highest-level intercept for commercial breaks. Confirmed unobfuscated in
// classes6.dex: mlb.atbat.media.player.listener.publica.PublicaBidListener
// return-void cancels: Publica auction → DAI pod metadata → dclk_video_ads
// ---------------------------------------------------------------------------

internal object PublicaAdBreakStartedFingerprint : Fingerprint(
    returnType = "V",
    strings = listOf("[MlbMediaPlayer] onAdBreakStarted"),
    custom = { method, _ ->
        method.definingClass.endsWith("PublicaBidListener;") &&
            method.name == "onAdBreakStarted"
    },
)

// ---------------------------------------------------------------------------
// Patch 4: Publica Bid Upstream — GetPublicaBidsUseCase
//
// Depth-of-defense. Confirmed unobfuscated in classes6.dex:
// mlb.atbat.data.usecase.GetPublicaBidsUseCase
// NOTE: If this is a suspend function, the fingerprint may not match —
// that is safe to ignore, Patch 3 handles the primary intercept.
// ---------------------------------------------------------------------------

internal object GetPublicaBidsFingerprint : Fingerprint(
    returnType = "V",
    strings = listOf("Publica bids count: ", "Failed to get Publica ads"),
    custom = { method, _ ->
        method.definingClass.endsWith("GetPublicaBidsUseCase;")
    },
)
