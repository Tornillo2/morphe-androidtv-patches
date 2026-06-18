/*
 * MLB At Bat Android TV — Ad Patch Fingerprints
 *
 * Validated against:
 *   v26.8.1  (versionCode 1750000022) — com.bamnetworks.mobile.android.gameday
 *
 * ALL FINGERPRINTS VERIFIED via full APK bytecode analysis (androguard).
 *
 * AD DELIVERY ARCHITECTURE (confirmed via logcat + bytecode):
 *
 *   tv-gmc.mlb.com serves BOTH game content AND /EVI/ ad segments:
 *     Game:  tv-gmc.mlb.com/{date}/{gameId}-HD_7500K/.../seg.ts
 *     Ads:   tv-gmc.mlb.com/EVI/{date}/{gameId}-AD-evi_7500K/.../seg.ts
 *   DNS blocking cannot distinguish paths — domain blocking kills the stream.
 *
 *   Both ad systems are scheduled via HLS TXXX timed metadata cues.
 *   Two handlers process these cues and must both be blocked:
 *
 *   Patch 3 — Lz70/b;.o(Ll5/t;)V  [MLB TXXX handler]
 *     Reads TXXX cue → launches coroutines Lz70/d;/Lz70/e; → fetches
 *     pod metadata → dispatches EVI segment URLs to ExoPlayer.
 *     VERIFIED unique: only method in APK with TXXX string + Ll5/t; param
 *     that is NOT named "onMetadata".
 *
 *   Patch 4 — Lb6/h$c;.onMetadata(Ll5/t;)V  [IMA SSAI TXXX handler]
 *     Reads TXXX cue → onUserTextReceived() callbacks → DAI segment insertion.
 *     VERIFIED unique: only "onMetadata" in class Lb6/h$c;
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
// VERIFIED: registers=8, 17 strings including "ssai", "dai.google.com",
//   "assetKey", "Invalid URI scheme or authority."
// Unique: only method in APK taking Uri param returning StreamRequest
//   with these strings.
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
// Patch 3: MLB TXXX Ad Cue Handler — Lz70/b;.o(Ll5/t;)V
//
// VERIFIED: returnType=V, param=Ll5/t;, only string in body = "TXXX"
// UNIQUE: only method in APK with TXXX string + Ll5/t; param + returnType V
//   that is NOT named "onMetadata" (confirmed by full APK scan).
//
// MLB-specific HLS timed metadata handler. Reads TXXX cue from DAI manifest,
// launches coroutines Lz70/d; and Lz70/e; to fetch pod metadata and schedule
// tv-gmc.mlb.com/EVI/ segment URLs. return-void cancels entire chain.
// ---------------------------------------------------------------------------

internal object MlbTxxxAdCueFingerprint : Fingerprint(
    returnType = "V",
    strings = listOf("TXXX"),
    custom = { method, _ ->
        method.name != "onMetadata" &&
            method.parameterTypes.size == 1 &&
            method.parameterTypes[0] == "Ll5/t;"
    },
)

// ---------------------------------------------------------------------------
// Patch 4: IMA SSAI TXXX Handler — Lb6/h$c;.onMetadata(Ll5/t;)V
//
// VERIFIED: name=onMetadata, returnType=V, param=Ll5/t;, string="TXXX"
//   definingClass contains "b6/h" (ImaSSAI inner listener class).
// UNIQUE: only "onMetadata" in Lb6/h$c; taking Ll5/t; param.
//
// IMA SSAI layer handler. Calls onUserTextReceived() on all registered
// VideoStreamPlayerCallback instances — triggers DAI segment insertion.
// return-void suppresses IMA SSAI ad cues alongside MLB EVI (Patch 3).
// ---------------------------------------------------------------------------

internal object ImaSsaiTxxxHandlerFingerprint : Fingerprint(
    returnType = "V",
    strings = listOf("TXXX"),
    custom = { method, _ ->
        method.name == "onMetadata" &&
            method.definingClass.contains("b6/h") &&
            method.parameterTypes.size == 1 &&
            method.parameterTypes[0] == "Ll5/t;"
    },
)
