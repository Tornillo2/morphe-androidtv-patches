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
        // invoke-static/range is used instead of invoke-static because
        // setAdPlaybackStates() has enough local variables that p1 maps to
        // a register above v15 (v17 was reported in the patch log). Standard
        // invoke-static only supports registers v0-v15 (4-bit encoding).
        // invoke-static/range supports the full 16-bit register space and
        // uses {pN .. pN} syntax to specify a single-register range.
        //
        // The extension iterates every AdPlaybackState in the map and calls
        // withSkippedAdGroup(i) for each active group index, returning a new
        // ImmutableMap with all groups marked skipped. Replacing p1 here means
        // the original validation loop and handler-post see only skipped groups.
        //
        // withSkippedAdGroup() calls AdGroup.withAllAdsSkipped() which sets
        // AD_STATE_SKIPPED on each ad without touching isServerSideInserted,
        // so the SSAI validation in the original method continues to pass.
        // ─────────────────────────────────────────────────────────────────────
        SetAdPlaybackStatesMedia3Fingerprint.method.addInstructions(
            0,
            """
                invoke-static/range {p1 .. p1}, Lajstrick81/morphe/extension/primevideo/ads/SkipAdsPatch;->skipAllMedia3AdGroups(Lcom/google/common/collect/ImmutableMap;)Lcom/google/common/collect/ImmutableMap;
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
        // Same invoke-static/range fix applied for the same reason — the
        // ExoPlayer2 variant is inside the GMS Ads SDK (classes3.dex) and
        // may have the same high-register layout.
        // ─────────────────────────────────────────────────────────────────────
        SetAdPlaybackStatesExo2Fingerprint.method.addInstructions(
            0,
            """
                invoke-static/range {p1 .. p1}, Lajstrick81/morphe/extension/primevideo/ads/SkipAdsPatch;->skipAllExo2AdGroups(Lcom/google/common/collect/ImmutableMap;)Lcom/google/common/collect/ImmutableMap;
                move-result-object p1
            """
        )
    }
}
