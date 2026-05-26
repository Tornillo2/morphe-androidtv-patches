package ajstrick81.morphe.patches.primevideo.ads

import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.patch.bytecodePatch
import ajstrick81.morphe.patches.primevideo.misc.extension.primeVideoExtensionPatch
import ajstrick81.morphe.patches.primevideo.shared.Constants

@Suppress("unused")
val skipAdsPatch = bytecodePatch(
    name = "Skip ads",
    description = "Prevents server-side inserted ads from playing in the video stream.",
) {
    compatibleWith(Constants.COMPATIBILITY)

    dependsOn(primeVideoExtensionPatch)

    execute {

        // ─────────────────────────────────────────────────────────────────────
        // Patch 1 — media3 ServerSideAdInsertionMediaSource.setAdPlaybackStates()
        //
        // Register layout at entry of setAdPlaybackStates(ImmutableMap, Timeline):
        //   p0 = this (ServerSideAdInsertionMediaSource)
        //   p1 = ImmutableMap<Object, AdPlaybackState>   ← we transform this
        //   p2 = Timeline
        //
        // We call our extension before the original logic runs. The extension
        // iterates every AdPlaybackState in the map and calls withSkippedAdGroup(i)
        // for each active group index, returning a new ImmutableMap. We then
        // replace p1 with the transformed map so the original validation loop
        // and handler-post proceed with all groups already marked skipped.
        //
        // The original validation checks isServerSideInserted == true on each
        // AdGroup. withSkippedAdGroup() calls AdGroup.withAllAdsSkipped() which
        // only sets AD_STATE_SKIPPED on each ad — it does not touch
        // isServerSideInserted — so validation passes cleanly.
        // ─────────────────────────────────────────────────────────────────────
        SetAdPlaybackStatesMedia3Fingerprint.method.addInstructions(
            0,
            """
                invoke-static { p1 }, Lajstrick81/morphe/extension/primevideo/ads/SkipAdsPatch;->skipAllMedia3AdGroups(Lcom/google/common/collect/ImmutableMap;)Lcom/google/common/collect/ImmutableMap;
                move-result-object p1
            """
        )

        // ─────────────────────────────────────────────────────────────────────
        // Patch 2 — ExoPlayer2 ServerSideAdInsertionMediaSource.setAdPlaybackStates()
        //
        // Register layout at entry of setAdPlaybackStates(ImmutableMap):
        //   p0 = this (ServerSideAdInsertionMediaSource)
        //   p1 = ImmutableMap<Object, AdPlaybackState>   ← we transform this
        //
        // Same strategy as Patch 1 but targeting the ExoPlayer2 variant inside
        // the bundled GMS Ads SDK. The ExoPlayer2 AdPlaybackState API is
        // structurally identical — same withSkippedAdGroup(int) contract,
        // same removedAdGroupCount / adGroupCount fields.
        // ─────────────────────────────────────────────────────────────────────
        SetAdPlaybackStatesExo2Fingerprint.method.addInstructions(
            0,
            """
                invoke-static { p1 }, Lajstrick81/morphe/extension/primevideo/ads/SkipAdsPatch;->skipAllExo2AdGroups(Lcom/google/common/collect/ImmutableMap;)Lcom/google/common/collect/ImmutableMap;
                move-result-object p1
            """
        )
    }
}
