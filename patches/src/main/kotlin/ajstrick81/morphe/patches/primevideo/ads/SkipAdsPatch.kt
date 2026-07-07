package ajstrick81.morphe.patches.primevideo.ads

import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.patch.bytecodePatch
import ajstrick81.morphe.patches.primevideo.misc.extension.primeVideoExtensionPatch
import ajstrick81.morphe.patches.primevideo.shared.Constants

@Suppress("unused")
val skipAdsPatch = bytecodePatch(
    name = "Skip ads",
    description = "Multi-layer ad suppression. NOTE: SSAI hooks may be ineffective on 6.23.x if ads are handled by a custom/native pipeline. Primary defense: forced AdsConfiguration removal.",
) {
    compatibleWith(Constants.COMPATIBILITY)

    dependsOn(primeVideoExtensionPatch)

    execute {

        // ─────────────────────────────────────────────────────────────────────
        // Hook 1 —media3 ServerSideAdInsertionMediaSource.setAdPlaybackStates()
        //
        // BUG FIX: Prime Video ATV 6.23.x does NOT ship media3 in any DEX file
        // (no androidx/ package exists).
        // SAFE: skip if class is absent.
        // ─────────────────────────────────────────────────────────────────────
        SetAdPlaybackStatesMedia3Fingerprint.matchOrNull()?.method?.addInstructions(
            0,
            """
                invoke-static/range {p1 .. p1}, Lajstrick81/morphe/extension/primevideo/ads/SkipAdsPatch;->skipAllMedia3AdGroups(Lcom/google/common/collect/ImmutableMap;)Lcom/google/common/collect/ImmutableMap;
                move-result-object p1
            """
        )

        // ─────────────────────────────────────────────────────────────────────
        // Hook 2 — ExoPlayer2 ServerSideAdInsertionMediaSource.setAdPlaybackStates()
        //
        // PRIMARY hook for 6.23.x.
        // ─────────────────────────────────────────────────────────────────────
        SetAdPlaybackStatesExo2Fingerprint.method.addInstructions(
            0,
            """
                invoke-static/range {p1 .. p1}, Lajstrick81/morphe/extension/primevideo/ads/SkipAdsPatch;->skipAllExo2AdGroups(Lcom/google/common/collect/ImmutableMap;)Lcom/google/common/collect/ImmutableMap;
                move-result-object p1
            """
        )

        // ─────────────────────────────────────────────────────────────────────
        // Hook 3 — MetricsTransporter.transmit(SerializedBatch)
        //
        // Returns fake SUCCESS; no network call.
        // ─────────────────────────────────────────────────────────────────────
        MetricsTransporterTransmitFingerprint.method.addInstructions(
            0,
            """
                new-instance v0, Lcom/amazon/minerva/client/thirdparty/transport/UploadResult;
                const-string v1, "SUCCESS"
                const-string v2, "ok"
                invoke-direct {v0, v1, v2}, Lcom/amazon/minerva/client/thirdparty/transport/UploadResult;-><init>(Ljava/lang/String;Ljava/lang/String;)V
                return-object v0
            """
        )

        // ─────────────────────────────────────────────────────────────────────
        // Hook 4 — Volley BasicNetwork.performRequest(Request)
        //
        // Network-layer host/path filter.
        // ─────────────────────────────────────────────────────────────────────
        BasicNetworkPerformRequestFingerprint.method.addInstructions(
            0,
            """
                invoke-static {p1}, Lajstrick81/morphe/extension/primevideo/ads/SkipAdsPatch;->enforceAdBlock(Lcom/android/volley/Request;)V
            """
        )

    }
}
