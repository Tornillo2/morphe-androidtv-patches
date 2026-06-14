/*
 * Paramount+ Android TV — Ad Patch Fingerprints
 *
 * Validated against:
 *   v16.8.0  (versionCode 520000464) — com.cbs.ott
 *
 * Strategy confirmed via logcat + ImaSdkFactory.smali analysis:
 *
 *   VOD has a pre-fetched resourceUrl (cbsaavideo.com) that AVIA holds
 *   before attempting DAI. When DAI initialization fails non-critically
 *   (null StreamRequest → null guard fires), AVIA falls back to that
 *   direct URL and plays content without SSAI ads.
 *
 *   Live TV has NO pre-fetched URL. DAI is the only source of the manifest.
 *   Patching createLiveStreamRequest() would kill live TV entirely.
 *
 *   Returning null from createVodStreamRequest() triggers the null guard
 *   in pk0.run() ("DAI AdsLoader or StreamRequest is null") — a non-critical
 *   log path — allowing the fallback. Returning an empty zzcx causes
 *   requestStream(emptyZzcx) to be called, which throws a critical IMA
 *   exception (error 6007) that terminates playback instead of falling back.
 *
 * Active patches:
 *   1. VodStreamRequest3ArgFingerprint — return null (non-critical DAI fail)
 *   2. VodStreamRequest4ArgFingerprint — return null (non-critical DAI fail)
 *   3. PauseAdOverlayFingerprint       — pause ad overlay suppression
 *
 * NOT patched (intentional):
 *   createLiveStreamRequest() — live TV has no fallback URL
 *   createPodStreamRequest()  — same reason
 */

package app.morphe.patches.paramount

import app.morphe.patcher.Fingerprint

// ---------------------------------------------------------------------------
// Patch 1 & 2: VOD SSAI — createVodStreamRequest (3-arg and 4-arg)
//
// Both overloads confirmed in ImaSdkFactory.smali v16.8.0:
//
//   3-arg (deprecated, called by pk0.run() main SSAI path):
//     zzafv;->zzd (TRUMAN_STITCHED_MANIFEST_VOD)
//     zze(videoId), zzf(contentSourceId), zzo(apiKey)
//
//   4-arg (additional callers):
//     zzafv;->zzd (same)
//     zze, zzf, zzo, zzg(networkCode)
//
//   Live TV uses createLiveStreamRequest() → zzafv;->zzc (LINEAR) + zza(assetKey)
//   Completely separate code path — unaffected by these fingerprints.
//
// Fingerprint strategy: ImaSdkFactory is fully unobfuscated (Google IMA SDK
// public API). Method name stable. Parameter count differentiates overloads.
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
// Patch 3: Pause ads — CbsPauseWithAdsOverlay state machine
//
// Method name is minified and drifts between versions:
//   v16.8.0  → P(CbsPauseWithAdsOverlay, uy1)V
//   v16.12.0 → M(CbsPauseWithAdsOverlay, lz1)V
//
// Anchored on stable fallthrough-branch log strings. endsWith() handles
// the package path migration between versions.
// ---------------------------------------------------------------------------

internal object PauseAdOverlayFingerprint : Fingerprint(
    returnType = "V",
    strings = listOf("renderState: ", " not updating overlay."),
    custom = { method, _ ->
        method.definingClass.endsWith("CbsPauseWithAdsOverlay;")
    },
)
