/*
 * MLB At Bat Android TV — Ad Patch Fingerprints
 *
 * Validated against:
 *   v26.8.1  (versionCode 1750000022) — com.bamnetworks.mobile.android.gameday
 *
 * ALL FINGERPRINTS VERIFIED via full APK bytecode analysis (androguard).
 *
 * TXXX METADATA DISPATCH ARCHITECTURE (confirmed via bytecode trace):
 *
 *   ExoPlayer fires onMetadata(Ll5/t;) on all registered listeners
 *       ↓
 *   Lu70/i;.onMetadata(Ll5/t;)V  ← SINGLE dispatch point
 *       Logs: "[ExoMediaPlayer] metadata received from stream"
 *       Iterates f0 ArrayList of Ly70/s; listeners
 *       Calls invoke-interface Ly70/s;->o(Ll5/t;)V on each
 *       ↓
 *   Lz70/b;.o(Ll5/t;)V  ← MLB TXXX handler (registers=15, has TXXX string)
 *       Reads TXXX cue → launches Lz70/d;/Lz70/e; coroutines
 *       → fetches pod metadata → dispatches tv-gmc.mlb.com/EVI/ URLs
 *       ↓
 *   Lb6/h$c;.onMetadata(Ll5/t;)V  ← IMA SSAI handler (registers=11, has TXXX)
 *       Calls onUserTextReceived() → DAI (dclk_video_ads) segment insertion
 *
 * PREVIOUS PATCH FAILURE REASON:
 *   MlbTxxxAdCueFingerprint used "method.name != onMetadata" which matched
 *   empty stub delegates (registers=2) instead of Lz70/b;.o (registers=15).
 *   Morphe matched a stub — patch compiled but did nothing at runtime.
 *
 * CORRECT STRATEGY:
 *   Patch Lu70/i;.onMetadata — the single upstream dispatcher.
 *   return-void here stops ALL downstream listener dispatch in one shot.
 *   No need to patch Lz70/b;.o or Lb6/h$c;.onMetadata separately.
 *
 *   VERIFIED UNIQUE: Only method in APK with:
 *     - name = "onMetadata"
 *     - string = "[ExoMediaPlayer] metadata received from stream"
 *     - proto = (Ll5/t;)V
 *     - registers = 5
 *
 * IMA SDK StreamRequest (verified from createVodStreamRequest bytecode):
 *   Class:       Lcom/google/ads/interactivemedia/v3/impl/zzdm;
 *   Constructor: <init>(Lcom/google/ads/interactivemedia/v3/internal/zzafs;)V
 *   VOD type:    Lcom/google/ads/interactivemedia/v3/internal/zzafs;->zzd
 */

package app.morphe.patches.mlbtv

import app.morphe.patcher.Fingerprint

// ---------------------------------------------------------------------------
// Patch 1a: VOD SSAI — createVodStreamRequest (3-arg)
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
// Patch 2: Between-Innings SSAI — Lb6/k;.b(Uri)→StreamRequest
//
// VERIFIED: registers=8, strings include "ssai", "dai.google.com",
//   "assetKey", "Invalid URI scheme or authority."
// Parses ssai://dai.google.com URIs for ImaServerSideAdInsertionMediaSource.
// Empty zzdm → SSAI source fails init → ExoPlayer falls back to plain HLS.
// ---------------------------------------------------------------------------

internal object SsaiStreamRequestFingerprint : Fingerprint(
    returnType = "Lcom/google/ads/interactivemedia/v3/api/StreamRequest;",
    strings = listOf(
        "ssai",
        "dai.google.com",
        "assetKey",
        "Invalid URI scheme or authority.",
    ),
    custom = { method, _ ->
        method.parameterTypes.size == 1 &&
            method.parameterTypes[0] == "Landroid/net/Uri;"
    },
)

// ---------------------------------------------------------------------------
// Patch 3: TXXX Metadata Dispatcher — Lu70/i;.onMetadata(Ll5/t;)V
//
// VERIFIED: name=onMetadata, proto=(Ll5/t;)V, registers=5
//   String: "[ExoMediaPlayer] metadata received from stream"
//   Class:  Lu70/i; (ExoMediaPlayer listener wrapper)
//
// UNIQUE: Only method in APK with this string + name + proto combination.
//
// Single upstream dispatch point for ALL HLS timed metadata.
// Iterates f0 ArrayList of Ly70/s; listeners and calls o(Ll5/t;) on each.
// return-void here stops ALL downstream TXXX processing:
//   - Lz70/b;.o()     → MLB EVI ad coroutines never launched
//   - Lb6/h$c;.onMetadata() → IMA DAI segment insertion never triggered
//   - tv-gmc.mlb.com/EVI/ and dclk_video_ads segments never dispatched
// ---------------------------------------------------------------------------

internal object ExoMediaPlayerMetadataFingerprint : Fingerprint(
    returnType = "V",
    strings = listOf("[ExoMediaPlayer] metadata received from stream"),
    custom = { method, _ ->
        method.name == "onMetadata" &&
            method.parameterTypes.size == 1 &&
            method.parameterTypes[0] == "Ll5/t;"
    },
)
