package ajstrick81.morphe.patches.peacock.ads

import ajstrick81.morphe.patches.peacock.shared.Constants
import app.morphe.patcher.patch.bytecodePatch
import app.morphe.patcher.extensions.InstructionExtensions.addInstructions

@Suppress("unused")
val skipAdsPatch = bytecodePatch(
    name = "Skip ads",
    description = "Disables ad delivery via three confirmed Sky SDK layers plus an OkHttp " +
        "interceptor that blocks ad CDN and analytics domains at the network layer, " +
        "replacing the AGH DNS dependency. Validated v7.5.102.",
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
        // Inject AdBlockInterceptor into NetworkingKt.getOkHttpClient() via
        // the zero-register wrapper pattern (PeacockAdPatchHelper.injectAdBlocker).
        //
        // Root cause of previous VerifyError: the original 4-instruction block
        // used new-instance + invoke-direct + invoke-virtual + move-result-object,
        // which caused type-undefined v0 at 0x16 because addInstructions(5)
        // inserted mid-method before register state was fully initialized from
        // the verifier's perspective.
        //
        // Fix: single invoke-static passing v0 (the Builder) to a Java/Kotlin
        // wrapper that calls addInterceptor() internally. No v-register reads,
        // writes, or move-result-object needed — the verifier sees only a void
        // static call with a known input type. Cannot produce a VerifyError.
        //
        // Offset 5 = after Builder.<init>, before the existing
        // OkHttpWorkaroundInterceptor new-instance. Both interceptors are
        // chained: AdBlockInterceptor runs first, then OkHttpWorkaroundInterceptor.
        GetOkHttpClientFingerprint.method.addInstructions(
            5,
            """
                invoke-static {v0}, Lajstrick81/morphe/extension/peacock/ads/PeacockAdPatchHelper;->injectAdBlocker(Lokhttp3/OkHttpClient${'$'}Builder;)V
            """.trimIndent(),
        )
    }
}
