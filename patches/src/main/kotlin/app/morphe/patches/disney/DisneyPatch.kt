/*
 * Credit:
 * Original work by RookieEnough aka The G.O.A.T :)
 *
 * Forked from:
 * https://gitlab.com/ReVanced/revanced-patches/-/blob/main/patches/src/main/kotlin/app/revanced/patches/disneyplus/ads/Fingerprints.kt
 *
 * Modified for use in morphe-androidtv-patches
 */
/*
 * Credit:
 * Original work by RookieEnough aka The G.O.A.T :)
 *
 * Forked from:
 * https://gitlab.com/ReVanced/revanced-patches/-/blob/main/patches/src/main/kotlin/app/revanced/patches/disneyplus/ads/Fingerprints.kt
 *
 * Modified for use in morphe-androidtv-patches
 *
 * Validated against Disney+ Android TV v26.8.0+rc6-2026.05.20
 * Package:     com.disney.disneyplus
 * VersionCode: 1779314460
 */

package app.morphe.patches.disney

import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.patch.bytecodePatch
import app.morphe.patches.shared.compat.AppCompatibilities

@Suppress("unused")
val disneyPatch = bytecodePatch(
    name = "Disney+ Android TV",
    description = "Removes mid-roll / pre-roll ads and pause ads.",
) {
    compatibleWith(AppCompatibilities.DISNEY_PLUS_TV)

    execute {
        // ------------------------------------------------------------------
        // Patch 1 & 2: Pre-roll / mid-roll SGAI/SSAI ad insertion
        //
        // Insertion.getPoints() returns the list of InsertionPoints (ad cue
        // positions) for a media item.  Returning an empty list causes the
        // player to see zero ad cues and skip all break scheduling.
        //
        // Insertion.getRanges() returns ad range windows used by:
        //   - Media3ExtensionsKt.allowedLiveInterstitials()  (live ad gating)
        //   - InsertionJsonAdapters                          (serialisation)
        // Emptying it prevents live interstitial gating from admitting any
        // ad range and stops serialisation from writing range data.
        //
        // Both methods are simple iget-object / return-object pairs, so
        // prepending a fresh ArrayList return at offset 0 is safe — the
        // original iget is never reached and the field is never read.
        // ------------------------------------------------------------------
        arrayOf(
            InsertionGetPointsFingerprint,
            InsertionGetRangesFingerprint,
        ).forEach { fingerprint ->
            fingerprint.method.addInstructions(
                0,
                """
                    new-instance v0, Ljava/util/ArrayList;
                    invoke-direct { v0 }, Ljava/util/ArrayList;-><init>()V
                    return-object v0
                """.trimIndent(),
            )
        }

        // ------------------------------------------------------------------
        // Patch 3: Pause ads
        //
        // MediaXInterstitialController.onPauseScheduled(MediaXPause) is the
        // MEL callback fired when the player detects a user pause event and
        // a pause ad has been scheduled by the ad server (via AdSession
        // .getPauseUrl()).
        //
        // Call chain:
        //   onPauseScheduled(MediaXPause)
        //     iget pauseSession (MediaXPauseSession)
        //     if null → return (already a no-op guard)
        //     getPauseScheduled() PublishSubject → onNext(pauseSession)
        //       → downstream subscriber renders the pause ad overlay
        //
        // Patching strategy: return-void at instruction 0.
        //
        // Effect:
        //   - The RxJava onNext() is never called
        //   - The pause ad overlay is never shown
        //   - No NPE risk: pauseSession is still created by createPauseSession()
        //     and can be cleaned up normally by clear(); we just never surface
        //     the event that would render the UI
        //   - Equivalent to the CbsPauseWithAdsOverlay.M() → return-void
        //     patch used in the Paramount+ patch set
        // ------------------------------------------------------------------
        PauseAdScheduledFingerprint.method.addInstructions(
            0,
            """
                return-void
            """.trimIndent(),
        )
    }
}
