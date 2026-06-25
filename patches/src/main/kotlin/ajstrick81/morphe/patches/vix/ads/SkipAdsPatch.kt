package ajstrick81.morphe.patches.vix.ads

import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.patch.bytecodePatch
import ajstrick81.morphe.patches.vix.shared.Constants

@Suppress("unused")
val skipAdsPatch = bytecodePatch(
    name = "Skip ads",
    description = "Suppresses ViX ad delivery by preventing the Innovid SSAI ad overlay from " +
        "mounting in the player.",
) {
    compatibleWith(Constants.COMPATIBILITY)

    execute {

        // ─────────────────────────────────────────────────────────────────────
        // InnovidHelper.h(boolean, WebView, VideoModel, Flow)
        //
        // The entry point that mounts the Innovid SSAI ad WebView overlay
        // (carries the "innovidAd" model; dispatched from the player's
        // ad-event switch in videoplayer/z.smali). Returning void before the
        // first instruction stops the session before any WebView is attached
        // or network request is made. Safe to stub at index 0 — this is an
        // ordinary instance method, not a constructor.
        // ─────────────────────────────────────────────────────────────────────
        InnovidStartAdFingerprint.method.addInstructions(
            0,
            """
                return-void
            """
        )
    }
}
