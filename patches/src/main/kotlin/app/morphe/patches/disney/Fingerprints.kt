/*
 * Forked from:
 * https://gitlab.com/ReVanced/revanced-patches/-/blob/main/patches/src/main/kotlin/app/revanced/patches/disneyplus/ads/Fingerprints.kt
 * ALL CREDIT GOES TO RookieEnough FOR THE ORIGINAL CODE
 */

/*
 * Forked from:
 * https://gitlab.com/ReVanced/revanced-patches/-/blob/main/patches/src/main/kotlin/app/revanced/patches/disneyplus/ads/Fingerprints.kt
 * ALL CREDIT GOES TO RookieEnough FOR THE ORIGINAL CODE
 *
 * Updated for Disney+ Android TV v26.8.0+rc6 (versionCode 1779314460)
 * - InsertionGetPointsFingerprint:   VALIDATED ✅  (class + method unchanged)
 * - InsertionGetRangesFingerprint:   VALIDATED ✅  (class + method unchanged)
 * - PauseAdScheduledFingerprint:     NEW ✅  targets onPauseScheduled() in
 *                                    MediaXInterstitialController
 */

package app.morphe.patches.disney

import app.morphe.patcher.Fingerprint

// ---------------------------------------------------------------------------
// Existing fingerprints — validated present and structurally unchanged in
// com.dss.sdk.internal.media.Insertion as of v26.8.0+rc6-2026.05.20
// ---------------------------------------------------------------------------

internal object InsertionGetPointsFingerprint : Fingerprint(
    returnType = "Ljava/util/List",
    custom = { method, _ ->
        method.name == "getPoints" &&
            method.definingClass == "Lcom/dss/sdk/internal/media/Insertion;"
    },
)

internal object InsertionGetRangesFingerprint : Fingerprint(
    returnType = "Ljava/util/List",
    custom = { method, _ ->
        method.name == "getRanges" &&
            method.definingClass == "Lcom/dss/sdk/internal/media/Insertion;"
    },
)

// ---------------------------------------------------------------------------
// New fingerprint — pause ads
//
// Target: MediaXInterstitialController.onPauseScheduled(MediaXPause)
//
// This method is the entry point for the pause ad lifecycle. When the player
// pauses, the MEL (Media Event Layer) calls onPauseScheduled() which publishes
// the pause session to a RxJava PublishSubject, triggering the ad overlay.
//
// Patching strategy: return-void at instruction 0, preventing the event from
// ever reaching the subscriber that renders the pause ad overlay.
//
// Anchor string "mediaXPause" is a Kotlin non-null assertion label injected
// by the compiler at the top of the method — stable across minification
// because it comes from the Kotlin source parameter name, not ProGuard.
//
// Full call chain (for reference):
//   onPauseScheduled(MediaXPause)
//     → pauseSession (MediaXPauseSession field, set by createPauseSession)
//     → getPauseScheduled() PublishSubject
//       → subscriber renders CbsPauseWithAdsOverlay equivalent
// ---------------------------------------------------------------------------

internal object PauseAdScheduledFingerprint : Fingerprint(
    returnType = "V",
    strings = listOf("mediaXPause"),
    custom = { method, _ ->
        method.name == "onPauseScheduled" &&
            method.definingClass ==
                "Lcom/disneystreaming/nve/player/mel/MediaXInterstitialController;"
    },
)
