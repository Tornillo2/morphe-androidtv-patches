/*
 * Paramount+ Android TV — Ad Patch Fingerprints
 *
 * Validated against:
 *   v16.8.0  (versionCode 520000464) — com.cbs.ott
 *   v16.12.0 (versionCode 520000571) — com.cbs.ott
 *
 * Active patch targets:
 *   1. CbsPauseWithAdsOverlay state machine — pause ad overlay renderer ✅
 *
 * VOD SSAI NOTE:
 *   createVodStreamRequest patches have been removed. Paramount+ VOD uses
 *   pure SSAI — the content manifest URL is returned by the DAI exchange
 *   itself. Patching the stream request breaks playback entirely because
 *   there is no fallback content URL. VOD ad suppression requires
 *   intercepting the IMA StreamManager ad break event chain AFTER the
 *   DAI exchange completes (onAdBreakStarted / StreamEvent handling).
 *   This requires further APK analysis — tracked for next session.
 *
 * Live TV:
 *   createLiveStreamRequest is intentionally NOT patched.
 *   The asset key set by that method IS the channel identity — it is used
 *   to obtain the stitched HLS manifest from saa.paramountplus.com.
 *   Patching it produces a blank StreamRequest and a black screen.
 */

package app.morphe.patches.paramount

import app.morphe.patcher.Fingerprint

// ---------------------------------------------------------------------------
// Pause ad fingerprint — CbsPauseWithAdsOverlay state machine
//
// This public static method is the sole dispatcher for the pause ad overlay.
// It receives a sealed state object and branches on its type:
//   - State carrying a URL  → Glide network load → alpha fade-in to 1.0
//   - State carrying a File → Glide local load   → sized image render
//   - Reset state           → fade-out to alpha=0
//   - Fallthrough           → logs "renderState: X not updating overlay."
//
// The method name is minified and drifted between versions:
//   v16.8.0  → P(CbsPauseWithAdsOverlay, uy1)V
//   v16.12.0 → M(CbsPauseWithAdsOverlay, lz1)V
//
// Fingerprint strategy: anchor on the two stable log strings from the
// fallthrough branch. These are developer-facing messages unlikely to
// change across versions. Combined with endsWith() on the class name to
// handle the package path migration between v16.8.0 and v16.12.0:
//   v16.8.0  → Lcom/cbs/player/view/tv/CbsPauseWithAdsOverlay;
//   v16.12.0 → Lcom/paramount/android/pplus/widgets/player/tv/view/CbsPauseWithAdsOverlay;
// ---------------------------------------------------------------------------

internal object PauseAdOverlayFingerprint : Fingerprint(
    returnType = "V",
    strings = listOf("renderState: ", " not updating overlay."),
    custom = { method, _ ->
        method.definingClass.endsWith("CbsPauseWithAdsOverlay;")
    },
)
