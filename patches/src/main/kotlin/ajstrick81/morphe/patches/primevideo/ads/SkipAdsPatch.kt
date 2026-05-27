package ajstrick81.morphe.patches.primevideo.ads

import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.extensions.InstructionExtensions.getInstruction
import app.morphe.patcher.patch.bytecodePatch
import com.android.tools.smali.dexlib2.Opcode
import com.android.tools.smali.dexlib2.iface.instruction.OneRegisterInstruction
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
        // Hook 1 — Capture the ExoPlayer instance at construction time
        //
        // ExoPlayer.Builder.build() ends with:
        //   new-instance v0, ExoPlayerImpl
        //   invoke-direct {v0, p0, v1}, ExoPlayerImpl-><init>(...)
        //   return-object v0       ← we insert before this
        //
        // We locate the RETURN_OBJECT instruction by iterating the instruction
        // list manually (indexOfFirstInstructionOrThrow is not available in
        // Morphe 1.3.0). The register holding the return value is read
        // dynamically so this survives any future register layout changes.
        //
        // capturePlayer() stores the instance in a static WeakReference in the
        // extension, giving the skipAll methods a live Player handle for
        // subsequent setPlaybackParameters() calls during ad breaks.
        // ─────────────────────────────────────────────────────────────────────
        ExoPlayerBuilderBuildFingerprint.method.apply {
            val instructions = implementation!!.instructions.toList()
            val returnIndex = instructions.indexOfFirst {
                it.opcode == Opcode.RETURN_OBJECT
            }
            val playerRegister = getInstruction<OneRegisterInstruction>(returnIndex).registerA
            addInstructions(
                returnIndex,
                """
                    invoke-static/range { v$playerRegister .. v$playerRegister }, Lajstrick81/morphe/extension/primevideo/ads/SkipAdsPatch;->capturePlayer(Ljava/lang/Object;)V
                """
            )
        }

        // ─────────────────────────────────────────────────────────────────────
        // Hook 2 — media3 ServerSideAdInsertionMediaSource.setAdPlaybackStates()
        //
        // Register layout at entry of setAdPlaybackStates(ImmutableMap, Timeline):
        //   p0 = this (ServerSideAdInsertionMediaSource)
        //   p1 = ImmutableMap<Object, AdPlaybackState>   ← we transform this
        //   p2 = Timeline
        //
        // skipAllMedia3AdGroups() does two things atomically:
        //   1. Marks every active ad group as AD_STATE_SKIPPED via
        //      withSkippedAdGroup(i) before ExoPlayer sees the map.
        //   2. Detects whether groups were present and ramps ExoPlayer to
        //      8x speed (or resets to 1.0x) via the captured Player ref.
        //
        // invoke-static/range handles the high register number — p1 maps to
        // v17+ in this method due to its large local variable count.
        // ─────────────────────────────────────────────────────────────────────
        SetAdPlaybackStatesMedia3Fingerprint.method.addInstructions(
            0,
            """
                invoke-static/range {p1 .. p1}, Lajstrick81/morphe/extension/primevideo/ads/SkipAdsPatch;->skipAllMedia3AdGroups(Lcom/google/common/collect/ImmutableMap;)Lcom/google/common/collect/ImmutableMap;
                move-result-object p1
            """
        )

        // ─────────────────────────────────────────────────────────────────────
        // Hook 3 — ExoPlayer2 ServerSideAdInsertionMediaSource.setAdPlaybackStates()
        //
        // Same strategy as Hook 2 but targeting the ExoPlayer2 variant inside
        // the bundled GMS Ads SDK. ExoPlayer2 and media3 AdPlaybackState APIs
        // are structurally identical — same fields, same withSkippedAdGroup
        // contract, same speed ramp logic.
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
