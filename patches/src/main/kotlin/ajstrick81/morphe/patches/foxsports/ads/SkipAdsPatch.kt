package ajstrick81.morphe.patches.foxsports.ads

import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.patch.bytecodePatch
import ajstrick81.morphe.patches.foxsports.shared.Constants

@Suppress("unused")
val foxSportsSkipAdsPatch = bytecodePatch(
    name = "Skip ads",
    description = "Suppresses all Yospace SSAI ad delivery in Fox Sports Android TV.",
) {
    compatibleWith(Constants.COMPATIBILITY)

    execute {

        // ─────────────────────────────────────────────────────────────────────
        // Hook 1 — YospaceAnalyticEventObserver.dispatchAdEvent(FoxAdEvent)
        //
        // Fox Sports v5.152 has removed the Google IMA/DAI pipeline entirely.
        // The previous patch had 8 hooks targeting FoxImaAdListeners,
        // FoxImaStreamIdLoader, FoxPlayer.clearVodAds etc — none of those
        // classes exist in this version. Yospace SSAI is now the sole ad system.
        //
        // Silences the Yospace ad event coroutine launcher at index 0.
        // ─────────────────────────────────────────────────────────────────────
        YospaceDispatchAdEventFingerprint.method.addInstructions(0, "return-void")

        // ─────────────────────────────────────────────────────────────────────
        // Hook 2 — YospaceAnalyticEventObserver.dispatchSlateEvent(FoxSlateEvent)
        //
        // Silences Yospace slate events (black screen placeholders during live
        // ad breaks when no creative is available).
        // ─────────────────────────────────────────────────────────────────────
        YospaceDispatchSlateEventFingerprint.method.addInstructions(0, "return-void")

        // ─────────────────────────────────────────────────────────────────────
        // Hook 3 — YospaceSeekablePlaybackPolicyHandler.setHandleFastForwardSeek
        //
        // Nulls out Yospace's fast-forward seek policy. Without this, the player
        // locks the scrub bar during ad breaks even when Hooks 1–2 prevent
        // rendering. Seek policy enforcement is a separate code path.
        // ─────────────────────────────────────────────────────────────────────
        YospaceSeekPolicyFingerprint.method.addInstructions(0, "return-void")
    }
}
