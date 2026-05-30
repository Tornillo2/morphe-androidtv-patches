package ajstrick81.morphe.patches.tubi.ads

import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.patch.bytecodePatch
import ajstrick81.morphe.patches.tubi.shared.Constants

@Suppress("unused")
val skipAdsPatch = bytecodePatch(
    name = "Skip ads",
    description = "Eliminates all Tubi ad types: intercepts DAI stream requests before they reach dai.google.com to prevent pre-roll stitching, blocks IMA ad events from reaching FoxPlayer, prevents AdsManager from priming the cold-launch pre-roll, and suppresses the pause-screen image overlay ad.",
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
        // COLD LAUNCH PRE-ROLL backstop. Blocks BaseManager.init() from firing,
        // preventing the IMA timeline from being primed for the pre-roll.
        // Belt-and-suspenders alongside Hook 5 — if the DAI stream request
        // somehow succeeds, this ensures the stitched pre-roll is never armed.
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
        // PAUSE AD suppression. Tubi's pause-screen image overlay is a
        // separate ad system from IMA — Hooks 1–3 have no effect on it.
        // return-void prevents the coroutine from fetching or displaying
        // the creative. Pause screen renders normally without the overlay.
        // ─────────────────────────────────────────────────────────────────────
        TubiPauseAdsFingerprint.method.addInstructions(
            0,
            """
                return-void
            """
        )

        // ─────────────────────────────────────────────────────────────────────
        // Hook 5 — FoxImaStreamIdLoader.requestVODDAIUrl(String, Boolean, ImaStreamUrlCallback)
        //
        // PRE-ROLL ROOT CAUSE suppression. Intercepts the DAI VOD stream
        // request before it reaches dai.google.com. Replicates a DNS-level
        // block in bytecode:
        //
        //   1. Call p3.onFailure("dai_blocked") → triggers Tubi's fallback
        //      path, which delivers a non-stitched direct content URL to
        //      FoxPlayer. Confirmed working via AGP DNS block test.
        //   2. return-void → DAI request never goes out.
        //
        // The onFailure() call is essential — omitting it leaves the callback
        // in a pending state and Tubi hangs waiting for a URL that never comes.
        //
        // p1 = adTagUrl (String)
        // p2 = forceRefresh (Boolean)
        // p3 = ImaStreamUrlCallback
        // ─────────────────────────────────────────────────────────────────────
        FoxImaVodStreamRequestFingerprint.method.addInstructions(
            0,
            """
                const-string v0, "dai_blocked"
                invoke-interface {p3, v0}, Lcom/fox/android/video/player/loaders/ImaStreamIdLoader${"$"}ImaStreamUrlCallback;->onFailure(Ljava/lang/String;)V
                return-void
            """
        )

        // ─────────────────────────────────────────────────────────────────────
        // Hook 6 — FoxImaStreamIdLoader.requestImaStreamId(String, Boolean, ImaStreamIdCallback)
        //
        // LIVE STREAM equivalent of Hook 5. Same intercept pattern for the
        // Pod/live stream path. Primarily a safety net — Tubi is VOD-only
        // so Hook 5 covers the common case.
        //
        // p3 = ImaStreamIdCallback
        // ─────────────────────────────────────────────────────────────────────
        FoxImaLiveStreamRequestFingerprint.method.addInstructions(
            0,
            """
                const-string v0, "dai_blocked"
                invoke-interface {p3, v0}, Lcom/fox/android/video/player/loaders/ImaStreamIdLoader${"$"}ImaStreamIdCallback;->onFailure(Ljava/lang/String;)V
                return-void
            """
        )

        // ─────────────────────────────────────────────────────────────────────
        // Hook 7 — xo/C$c.shouldInterceptRequest(WebView, WebResourceRequest)
        //
        // WEBVIEW PRE-ROLL ROOT CAUSE suppression. This is the architectural
        // keystone that all other hooks missed.
        //
        // Tubi is a hybrid app. The SPA at ott-androidtv.tubitv.com runs inside
        // TubiWebView and drives the ENTIRE pre-roll lifecycle in JavaScript.
        // ExoPlayer is not involved — it only initialises AFTER the pre-roll ends,
        // when the SPA calls startNativePlayer() to hand off to native playback.
        // Hooks 1–6 all target Java classes and have zero effect on JS-driven ads.
        //
        // xo/C$c is TvWebFragment$TubiWebClient (R8-minified). It extends
        // WebViewClient and Android calls shouldInterceptRequest() for every
        // network request the WebView makes — including the SPA's ad requests.
        //
        // We call TubiAdBlocker.shouldBlock(p2) first. If the request URL's
        // host matches a blocked ad domain, it returns an empty 200
        // WebResourceResponse and we return that immediately. If not, we fall
        // through to the existing LocalAssetsLoader (yo/b) logic unchanged.
        //
        // Blocked domains confirmed via AGP DNS test:
        //   dai.google.com, imasdk.googleapis.com, googletagmanager.com,
        //   doubleclick.net, googlesyndication.com
        //
        // v0 = return value from TubiAdBlocker.shouldBlock()
        // if v0 is non-null (blocked), return it immediately
        // if v0 is null (not blocked), fall through to original code
        //
        // Note: registers 10 total in this method. v0–v7 are free at index 0
        // before the original code uses them.
        // ─────────────────────────────────────────────────────────────────────
        TubiWebClientInterceptFingerprint.method.addInstructions(
            0,
            """
                invoke-static {p2}, Lajstrick81/morphe/extension/tubi/ads/TubiAdBlocker;->shouldBlock(Landroid/webkit/WebResourceRequest;)Landroid/webkit/WebResourceResponse;
                move-result-object v0
                if-eqz v0, :no_block
                return-object v0
                :no_block
                nop
            """
        )
    }
}
