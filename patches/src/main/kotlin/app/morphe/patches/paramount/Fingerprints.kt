/*
 * Paramount+ Android TV — Ad Patch Fingerprints
 *
 * Validated against:
 *   v16.8.0  (versionCode 520000464) — com.cbs.ott
 *
 * AD SUPPRESSION MECHANISM (confirmed via APK analysis of v16.8.0):
 *
 *   g1.c() builds the player media source (nm0.c) from ek0.s (content URI)
 *   BEFORE DAI initialization begins. This runs for all resource config types:
 *     Lwmg (simple URI), Ln27 (IMA resource), Luf3 (DAI/SSAI)
 *
 *   For VOD:      uf3.ek0.s = cbsaavideo.com DASH manifest URL (non-null)
 *                 g1.c() → nm0.c = valid media source
 *                 DAI fails → AVIA uses nm0.c → content plays without SSAI ads
 *
 *   For live TV:  uf3.ek0.s = null (no pre-DAI content URL exists)
 *                 g1.c() → nm0.c = null
 *                 DAI fails → nm0.c = null → black screen
 *
 *   Therefore: the fallback is inherently self-regulating. Causing DAI to
 *   fail gracefully suppresses VOD SSAI ads while live TV requires DAI to
 *   succeed (since it has no ek0.s fallback URL). Live TV is NOT patched.
 *
 * STRATEGY:
 *   Return an empty (but non-null) zzcx StreamRequest from createVodStreamRequest().
 *   vk0.C is non-null → passes null guard → requestStream(emptyZzcx) is called →
 *   IMA SDK throws (no content source or video ID set) → exception caught by
 *   pk0.run() try-catch → dispatched as ErrorCriticalEvent → AVIA error handler
 *   detects nm0.c is valid → falls back to cbsaavideo.com → VOD plays without ads.
 *
 *   Live TV uses createLiveStreamRequest() — completely separate code path at
 *   pk0.run()[147] — unaffected by these fingerprints.
 *
 *   NOTE: This approach was previously tested WITH AdStartedFingerprint and
 *   AdSkipFingerprint active, which corrupted player state and blocked the
 *   nm0.c fallback path. This build contains ONLY these two patches + pause ads.
 *
 * isLive check location in pk0.run():
 *   [135] invoke-virtual v3, Lek0;->b()Z
 *   [137] if-eqz v3, +1a   → if NOT live (VOD), goto VOD path [151]
 *   [147] createLiveStreamRequest()  ← live TV (untouched)
 *   [160] createVodStreamRequest()   ← VOD (patched)
 *
 * Stream type enum (k33):
 *   k33.b = VOD  (ek0.b() returns false)
 *   k33.c = LIVE (ek0.b() returns true)
 *   k33.d = DVR
 */

package app.morphe.patches.paramount

import app.morphe.patcher.Fingerprint

// ---------------------------------------------------------------------------
// Patch 1a: VOD SSAI — createVodStreamRequest (3-arg)
//
// Called by pk0.run()[160] for standard VOD content.
// Returns a valid but empty zzcx object — no contentSourceId (zze),
// no videoId (zzf), no apiKey (zzo) set. requestStream(emptyZzcx) throws
// inside the IMA SDK, caught by pk0.run() try-catch, triggers the
// ErrorCriticalEvent → AVIA falls back to nm0.c (cbsaavideo.com).
//
// Fully unobfuscated (IMA SDK public API). Parameters: 3 Strings.
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
// Patch 1b: VOD SSAI — createVodStreamRequest (4-arg)
//
// Called by pk0.run() for VOD content with networkCode parameter.
// Same strategy as 3-arg — empty zzcx, triggers IMA SDK exception.
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
// Patch 2: Pause ads — CbsPauseWithAdsOverlay state machine
//
// Independent of IMA DAI — unaffected by the VOD stream request patches.
// Anchored on stable log strings. endsWith() handles package path migration.
// ---------------------------------------------------------------------------

internal object PauseAdOverlayFingerprint : Fingerprint(
    returnType = "V",
    strings = listOf("renderState: ", " not updating overlay."),
    custom = { method, _ ->
        method.definingClass.endsWith("CbsPauseWithAdsOverlay;")
    },
)
