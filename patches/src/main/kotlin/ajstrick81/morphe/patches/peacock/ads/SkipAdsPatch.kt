package ajstrick81.morphe.patches.peacock.ads

import ajstrick81.morphe.patches.peacock.shared.Constants
import app.morphe.patcher.patch.bytecodePatch
import app.morphe.patcher.extensions.InstructionExtensions.addInstructions

@Suppress("unused")
val skipAdsPatch = bytecodePatch(
    name = "Skip ads",
    description = "Disables ad delivery via three confirmed Sky SDK layers, an OkHttp " +
        "interceptor that blocks ad CDN and analytics domains at the network layer, " +
        "and a WebView shouldInterceptRequest wrapper that intercepts Chromium-routed " +
        "ad segment and FreeWheel traffic. Validated v7.5.102.",
) {
    compatibleWith(Constants.COMPATIBILITY)

    execute {
        // ── Layer 1 ─────────────────────────────────────────────────────────
        // Kill MediaTailor SSAI proxy — empty string prevents proxy URL
        // configuration, disabling server-side ad insertion at the source.
        MediaTailorProxyHostFingerprint.method.addInstructions(
            0,
            """
                const-string v0, ""
                return-object v0
            """.trimIndent(),
        )

        // ── Layer 3 ─────────────────────────────────────────────────────────
        // Abort MediaTailor ad service construction — return null from the
        // factory method identified by its unique error string anchor.
        MediaTailorAdServiceMethodFingerprint.method.addInstructions(
            0,
            """
                const/4 v0, 0x0
                return-object v0
            """.trimIndent(),
        )

        // ── Layer 4 ─────────────────────────────────────────────────────────
        // Force AdvertisingStrategy.None — getSsaiConfigurationProvider()
        // returning null causes strategyForType() to take the confirmed
        // if-eqz → None branch for ALL playback types. No crash risk.
        SsaiConfigurationProviderFingerprint.method.addInstructions(
            0,
            """
                const/4 v0, 0x0
                return-object v0
            """.trimIndent(),
        )

        // ── Layer 6 ─────────────────────────────────────────────────────────
        // Replace NetworkingKt.getOkHttpClient() body entirely via a no-arg
        // static call to PeacockAdPatchHelper.buildOkHttpClient().
        //
        // History of VerifyErrors:
        //   v1.4.56 — offset 5, 4-instruction block passing v0 as Builder arg
        //             → type=Undefined at 0x16 (move-result-object after build())
        //   v1.4.57 — offset 5, single invoke-static {v0} passing Builder arg
        //             → type=Conflict at 0x10 (verifier ambiguous on v0 type
        //                at mid-method merge point)
        //
        // Root cause: any insertion at offset 5 involves a register (v0) that
        // the verifier is actively tracking mid-method. Even a single pass-through
        // invoke causes a type conflict at the merge point.
        //
        // Fix — offset 0, no register arguments:
        //   At offset 0 no registers are live. invoke-static {} touches nothing.
        //   move-result-object v0 assigns a fresh OkHttpClient into an
        //   uninitialized register — the verifier always accepts this.
        //   return-object v0 exits cleanly. Original method body unreachable.
        //
        // PeacockAdPatchHelper.buildOkHttpClient() replicates the original body
        // in full (Builder + OkHttpWorkaroundInterceptor + build()) and prepends
        // AdBlockInterceptor so both interceptors are chained.
        GetOkHttpClientFingerprint.method.addInstructions(
            0,
            """
                invoke-static {}, Lajstrick81/morphe/extension/peacock/ads/PeacockAdPatchHelper;->buildOkHttpClient()Lokhttp3/OkHttpClient;
                move-result-object v0
                return-object v0
            """.trimIndent(),
        )

        // ── Layer 7 ─────────────────────────────────────────────────────────
        // WebView shouldInterceptRequest injection.
        //
        // PCAP/GREASE analysis confirmed that ad segment delivery (prd-cf CDN)
        // and all FreeWheel/analytics traffic travels through the Chromium network
        // stack inside XTVWebView, completely bypassing OkHttp. Layer 6 alone
        // cannot intercept this traffic.
        //
        // XTVWebView is a full WebView subclass that loads:
        //   https://tv.clients.peacocktv.com/android.html?containerVersion=7.5.102
        // xtvClient (XTVWebView$xtvClient$1) extends WebViewClient but does NOT
        // override shouldInterceptRequest — falls through to the no-op default.
        //
        // Injection point: instruction index 54 (bytecode offset 252) in
        // XTVWebView.<init>(Context), immediately before setWebViewClient(xtvClient).
        //   v5 = XTVWebView (this), v1 = xtvClient$1 instance
        //
        // Two instructions inserted before index 54 replace v1 with the wrapped
        // client. No new registers allocated — v1 is already typed as WebViewClient
        // at this point, so the verifier sees a legal same-type reassignment.
        //
        // PeacockWebViewHelper.wrapClient() delegates all existing xtvClient
        // callbacks (onPageStarted, onPageFinished, onLoadResource, onReceivedError,
        // onReceivedHttpError, onReceivedSslError, onRenderProcessGone) and adds
        // shouldInterceptRequest() returning an empty 200 response for confirmed
        // ad CDN and FreeWheel hostnames.
        XtvClientWrapFingerprint.method.addInstructions(
            54,
            """
                invoke-static {v1}, Lajstrick81/morphe/extension/peacock/ads/PeacockWebViewHelper;->wrapClient(Landroid/webkit/WebViewClient;)Landroid/webkit/WebViewClient;
                move-result-object v1
            """.trimIndent(),
        )
    }
}
