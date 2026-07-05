package ajstrick81.morphe.patches.primevideo.ads

import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.patch.bytecodePatch
import ajstrick81.morphe.patches.primevideo.misc.extension.primeVideoExtensionPatch
import ajstrick81.morphe.patches.primevideo.shared.Constants

@Suppress("unused")
val skipAdsPatch = bytecodePatch(
    name = "Skip ads",
    description = "Multi-layer ad suppression targeting the SSAI schedule, impression reporting, and the Volley network chokepoint.",
) {
    compatibleWith(Constants.COMPATIBILITY)

    dependsOn(primeVideoExtensionPatch)

    execute {

        // ─────────────────────────────────────────────────────────────────────
        // Hook 1 — media3 ServerSideAdInsertionMediaSource.setAdPlaybackStates()
        //
        // BUG FIX: Prime Video ATV 6.23.x does NOT ship media3 in any DEX file
        // (no androidx/ package exists). The original code called
        // SetAdPlaybackStatesMedia3Fingerprint.method which internally does
        //   matchOrNull() ?: throw PatchException(...)
        // This PatchException was thrown and aborted the entire execute{} block,
        // silently preventing Hooks 2, 3, and 4 from ever being applied —
        // which is why ads still showed after patching.
        //
        // FIX: Use matchOrNull() directly and skip the hook if the class is
        // absent. If a future Prime Video build ships media3 again the hook
        // will activate automatically without any code change.
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
        // PRIMARY hook for 6.23.x. The extension method skipAllExo2AdGroups()
        // now correctly guards against empty maps (which would re-trigger the
        // caller's Assertions.checkArgument(!isEmpty()) crash) and preserves
        // the adsId contract required by the caller's subsequent assertions.
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
        // Returns a fake SUCCESS UploadResult without making any network
        // request. UploadResult lives in the app's own DEX so we construct
        // it with inline smali — no extension method needed.
        // No changes from original.
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
        // Network-layer host/path filter. The extension method enforceAdBlock()
        // now also blocks FLS impression pixels, AAX header-bidding hosts, and
        // handles both camelCase and PascalCase getVideoAds path variants.
        // ─────────────────────────────────────────────────────────────────────
        BasicNetworkPerformRequestFingerprint.method.addInstructions(
            0,
            """
                invoke-static {p1}, Lajstrick81/morphe/extension/primevideo/ads/SkipAdsPatch;->enforceAdBlock(Lcom/android/volley/Request;)V
            """
        )
    }
}
