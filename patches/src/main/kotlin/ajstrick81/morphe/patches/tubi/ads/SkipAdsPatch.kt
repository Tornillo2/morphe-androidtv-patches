package ajstrick81.morphe.patches.tubi.ads

import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.patch.bytecodePatch
import ajstrick81.morphe.patches.tubi.shared.Constants

@Suppress("unused")
val skipAdsPatch = bytecodePatch(
    name = "Skip ads",
    description = "Eliminates all Tubi ad types: blocks IMA DAI mid-roll and post-roll events from reaching FoxPlayer, prevents the IMA AdsManager from priming the cold-launch pre-roll, and suppresses the pause-screen image overlay ad.",
) {
    compatibleWith(Constants.COMPATIBILITY)

    execute {

        // ─────────────────────────────────────────────────────────────────────
        // Hook 1 — FoxImaAdListeners.adEventListener_delegate$lambda$10$lambda$9
        //
        // PRIMARY mid-roll/post-roll suppression. Every IMA ad event flows
        // through this single method before FoxPlayer.dispatchAdEvent().
        // return-void at index 0 silences all ad events for the session.
        // ─────────────────────────────────────────────────────────────────────
        FoxImaAdEventListenerFingerprint.method.addInstructions(
            0,
            """
                return-void
            """
        )

        // ─────────────────────────────────────────────────────────────────────
        // Hook 2 — FoxImaAdListeners.adsLoadedListener_delegate$lambda$4$lambda$3
        //
        // COLD LAUNCH PRE-ROLL suppression. This is the only call site for
        // BaseManager.init(), which primes the IMA timeline and fires the
        // initial AD_BREAK_STARTED for the pre-roll.
        //
        // return-void at index 0 prevents init() from being called.
        // The DAI content stream URL is unaffected — it was already delivered
        // via requestVODDAIUrl → ImaStreamUrlCallback before this fires.
        // ─────────────────────────────────────────────────────────────────────
        FoxImaAdsLoadedListenerFingerprint.method.addInstructions(
            0,
            """
                return-void
            """
        )

        // ─────────────────────────────────────────────────────────────────────
        // Hook 3 — FoxPlayer.clearVodAds()
        //
        // Belt-and-suspenders: calls our extension to null the IMA
        // StreamManager reference in FoxImaAdListeners via reflection,
        // ensuring no stale IMA session survives across content transitions.
        // Silent no-op if clearVodAds() is not called during normal flow.
        // ─────────────────────────────────────────────────────────────────────
        FoxPlayerClearVodAdsFingerprint.method.addInstructions(
            0,
            """
                invoke-static { p0 }, Lajstrick81/morphe/extension/tubi/ads/SkipAdsPatch;->onClearVodAds(Ljava/lang/Object;)V
            """
        )

        // ─────────────────────────────────────────────────────────────────────
        // Hook 4 — ImagePauseAds.l(VideoApi, long)
        //
        // PAUSE AD suppression. Tubi's pause-screen image overlay is driven
        // by a completely separate ad system from IMA — Hooks 1–3 have no
        // effect on it. l() is the entry point that launches the coroutine
        // fetching and displaying the creative.
        //
        // return-void at index 0 prevents the coroutine from ever being
        // created. The pause screen renders normally without the ad overlay.
        // No impression or tracking events fire.
        // ─────────────────────────────────────────────────────────────────────
        TubiPauseAdsFingerprint.method.addInstructions(
            0,
            """
                return-void
            """
        )
    }
}
