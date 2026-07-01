package ajstrick81.morphe.patches.pluto.ads

import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.patch.bytecodePatch
import ajstrick81.morphe.patches.pluto.shared.Constants

@Suppress("unused")
val skipAdsPatch = bytecodePatch(
    name = "Skip ads",
    description = "Suppresses ad tracking and pause ads in Pluto TV Android TV.",
) {
    compatibleWith(Constants.COMPATIBILITY)

    execute {

        // Hook 1 — BeaconTracker.fire(String, List)
        // Silences every stitched-ad tracking beacon (impressions, quartiles,
        // completes). Fire-and-forget void method — no playback dependency.
        BeaconTrackerFireFingerprint.method.addInstructions(0, "return-void")

        // Hook 2 — PauseAdsImageBinder.showPauseAdImageAfterInactivity(Bitmap, List)
        // Suppresses the pause-ad overlay and its beacons.
        PauseAdsShowFingerprint.method.addInstructions(0, "return-void")
    }
}
