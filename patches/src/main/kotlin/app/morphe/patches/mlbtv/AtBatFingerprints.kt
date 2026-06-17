/*
 * MLB At Bat Android TV — Ad Patch Fingerprints
 *
 * Validated against:
 *   v26.8.1  (versionCode 1750000022) — com.bamnetworks.mobile.android.gameday
 *
 * ALL FINGERPRINTS VERIFIED via full APK bytecode analysis.
 * Exact class names, method names, return types, and string refs confirmed.
 *
 * BETWEEN-INNINGS AD ARCHITECTURE (confirmed):
 *
 *   Lv70/k;.b(Lo60/c$c; Lo60/c; Z)V
 *     → logs "[MlbMediaPlayer] onAdBreakStarted"
 *     → return type V (NOT a suspend function)
 *     → also references "adMetadata", "podMetadata", "breakStarted"
 *     → PRIMARY patch point: return-void here cancels the entire break
 *
 *   Lv70/a;.c(StreamElement; Lb80/s; Lu70/u; Lg10/c;)Object  [suspend]
 *     → logs "[CreateMediaItemWithAdsUseCase] Playing stream with DAI API"
 *     → DAI path initialization
 *
 *   Lv70/a;.d(StreamElement; Lu70/u; Lb80/s; Lj60/j; Lg10/c;)Object  [suspend]
 *     → logs "[CreateMediaItemWithAdsUseCase] Playing stream with IMA SDK"
 *     → IMA SDK path initialization
 *
 *   Lu50/g3;.a(StreamElement; I; Lg10/c;)Object  [suspend]
 *     → logs "Publica bids count: "
 *     → Publica ad auction
 *
 *   Lz70/i;.z()V
 *     → logs "[LinearGoogleDaiListener] Starting pod metadata timer"
 *     → DAI pod metadata fetch (googlevideo.com ad segments)
 */

package app.morphe.patches.mlbtv

import app.morphe.patcher.Fingerprint

// ---------------------------------------------------------------------------
// Patch 1a: VOD SSAI & Gambling Ads — createVodStreamRequest (3-arg)
// Unobfuscated IMA SDK public API — confirmed present in APK.
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
// Patch 3: Between-Innings Ad Break Entry Point
//
// VERIFIED: Class Lv70/k; method b(Lo60/c$c; Lo60/c; Z)V
// Return type: V (plain method, NOT a suspend function)
// Unique strings in bytecode: "[MlbMediaPlayer] onAdBreakStarted",
//   "adMetadata", "podMetadata", "breakStarted", "PlayerAdEventListener"
//
// return-void here cancels the entire between-innings ad break chain.
// ---------------------------------------------------------------------------

internal object AdBreakStartedFingerprint : Fingerprint(
    returnType = "V",
    strings = listOf(
        "[MlbMediaPlayer] onAdBreakStarted",
        "adMetadata",
        "podMetadata",
    ),
    custom = { method, _ ->
        method.parameterTypes.size == 3 &&
            method.parameterTypes[2] == "Z"
    },
)

// ---------------------------------------------------------------------------
// Patch 4: DAI Pod Metadata Timer — LinearGoogleDaiListener
//
// VERIFIED: Class Lz70/i; method z()V
// Return type: V, no parameters, single string ref in body.
// Killing this prevents googlevideo.com dclk_video_ads segment requests.
// Depth-of-defense below Patch 3.
// ---------------------------------------------------------------------------

internal object LinearDaiPodMetadataFingerprint : Fingerprint(
    returnType = "V",
    strings = listOf("[LinearGoogleDaiListener] Starting pod metadata timer"),
    custom = { method, _ ->
        method.parameterTypes.isEmpty()
    },
)
