package ajstrick81.morphe.patches.peacock.ads

import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.patch.bytecodePatch
import ajstrick81.morphe.patches.peacock.shared.Constants

// Note: val is named peacockSkipAdsPatch to avoid top-level name conflicts
// with other platform patches in the same compiled module.

@Suppress("unused")
val peacockSkipAdsPatch = bytecodePatch(
    name = "Skip ads",
    description = "Prevents Helio CSAI ad state from reaching the ExoPlayer pipeline, suppressing all ad breaks.",
) {
    compatibleWith(Constants.COMPATIBILITY)

    execute {

        // ─────────────────────────────────────────────────────────────────────
        // Hook — MultiPeriodAdsMediaSource$ComponentListener.a(AdPlaybackState)
        //
        // This single hook is the complete patch. HelioAdsLoader calls
        // ComponentListener.a() to push every AdPlaybackState update into
        // the MultiPeriodAdsMediaSource pipeline. Returning void at index 0
        // means no AdPlaybackState ever reaches the ExoPlayer playback thread.
        //
        // The existing guard in a() already checks:
        //   iget-boolean v0, b:Z
        //   if-eqz v0, :cond_0  → returns early if stopped
        //
        // Our return-void fires before that check, giving us unconditional
        // suppression regardless of listener state.
        //
        // HelioAdsLoader continues to initialize and request ad data from
        // Peacock's ad server — from the server's perspective the client is
        // active. Only the delivery of that state to the player is silenced.
        //
        // This is the same architectural pattern as Prime Video ATV's
        // setAdPlaybackStates() intercept but targeting the Comcast Helio
        // CSAI path rather than the Amazon Ignite SSAI path.
        // ─────────────────────────────────────────────────────────────────────
        HelioComponentListenerAdPlaybackStateFingerprint.method.addInstructions(
            0,
            """
                return-void
            """
        )
    }
}
