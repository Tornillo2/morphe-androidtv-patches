package app.morphe.patches.hbomax.ads

import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.patch.bytecodePatch
import app.morphe.patches.shared.compat.AppCompatibilities
import com.android.tools.smali.dexlib2.Opcode

@Suppress("unused")
val hboAdsPatch = bytecodePatch(
    name = "Max - Disable Ads",
    description = "Suppresses nonlinear overlay ads (Bolt), SSAI linear ad " +
        "timeline registration (GMSS/AdSparx), and live stream preroll ad " +
        "timeline entry generation for all content types.",
) {
    compatibleWith(AppCompatibilities.HBO_TV)

    execute {

        // ─────────────────────────────────────────────────────────────────────
        // Patch 1: BoltNonLinearAdsRequest.write$Self()
        // Suppress advertisingInfo (field index 2) from the serialized JSON
        // body entirely — omitting the key avoids NPE in the kotlinx
        // serialization encoder. Replace playbackId (field index 5) with
        // empty string. Fields 0/1/3/4/6 kept intact so the Bolt server
        // returns a graceful empty/4xx rather than a hard crash.
        // Note: getAdRequestType and getPlaybackId getters are R8-inlined
        // in this version and cannot be targeted directly — write$Self is
        // the correct suppression point at the serialization layer.
        // ─────────────────────────────────────────────────────────────────────
        BoltNonLinearAdsRequestWriteSelfFingerprint.method.apply {
            implementation!!.instructions.clear()
            addInstructions(
                0,
                """
                    sget-object v0, Lcom/wbd/adtech/bolt/BoltNonLinearAdsRequest;->${'$'}childSerializers:[Lkotlinx/serialization/KSerializer;
                    const/4 v1, 0x0
                    iget-object v2, p0, Lcom/wbd/adtech/bolt/BoltNonLinearAdsRequest;->adRequestType:Ljava/lang/String;
                    invoke-interface {p1, p2, v1, v2}, Lbr/d;->o(Lar/f;ILjava/lang/String;)V
                    sget-object v1, Lcom/wbd/adtech/bolt/BoltNonLinearAdsRequest${'$'}AdContext${'$'}${'$'}serializer;->INSTANCE:Lcom/wbd/adtech/bolt/BoltNonLinearAdsRequest${'$'}AdContext${'$'}${'$'}serializer;
                    iget-object v2, p0, Lcom/wbd/adtech/bolt/BoltNonLinearAdsRequest;->adContext:Lcom/wbd/adtech/bolt/BoltNonLinearAdsRequest${'$'}AdContext;
                    const/4 v3, 0x1
                    invoke-interface {p1, p2, v3, v1, v2}, Lbr/d;->E(Lar/f;ILyq/o;Ljava/lang/Object;)V
                    sget-object v1, Lcom/wbd/adtech/bolt/BoltNonLinearAdsRequest${'$'}DeviceInfo${'$'}${'$'}serializer;->INSTANCE:Lcom/wbd/adtech/bolt/BoltNonLinearAdsRequest${'$'}DeviceInfo${'$'}${'$'}serializer;
                    iget-object v2, p0, Lcom/wbd/adtech/bolt/BoltNonLinearAdsRequest;->deviceInfo:Lcom/wbd/adtech/bolt/BoltNonLinearAdsRequest${'$'}DeviceInfo;
                    const/4 v3, 0x3
                    invoke-interface {p1, p2, v3, v1, v2}, Lbr/d;->E(Lar/f;ILyq/o;Ljava/lang/Object;)V
                    const/4 v1, 0x4
                    aget-object v0, v0, v1
                    iget-object v2, p0, Lcom/wbd/adtech/bolt/BoltNonLinearAdsRequest;->capabilities:Ljava/util/List;
                    invoke-interface {p1, p2, v1, v0, v2}, Lbr/d;->E(Lar/f;ILyq/o;Ljava/lang/Object;)V
                    const/4 v0, 0x5
                    const-string v1, ""
                    invoke-interface {p1, p2, v0, v1}, Lbr/d;->o(Lar/f;ILjava/lang/String;)V
                    const/4 v0, 0x6
                    iget-object p0, p0, Lcom/wbd/adtech/bolt/BoltNonLinearAdsRequest;->editId:Ljava/lang/String;
                    invoke-interface {p1, p2, v0, p0}, Lbr/d;->o(Lar/f;ILjava/lang/String;)V
                    return-void
                """.trimIndent(),
            )
        }

        // ─────────────────────────────────────────────────────────────────────
        // Patch 2: BoltDynamicAdFetcher$fetchNonLinearAds$1.invokeSuspend()
        // Insert const/4 v8, 0x0 immediately after move-result-object v8
        // following the fetchNonLinearAds call. Discards the real ad list
        // before it reaches the coroutine collector — null != COROUTINE_SUSPENDED
        // so if-ne branch is taken, Result.success(null) returned, no ads
        // scheduled, no crash.
        // ─────────────────────────────────────────────────────────────────────
        BoltDynamicAdFetcherInvokeSuspendFingerprint.method.apply {
            val instructions = implementation!!.instructions
            val moveResultIndex = instructions.indexOfFirst { instruction ->
                val idx = instructions.indexOf(instruction)
                instruction.opcode == Opcode.MOVE_RESULT_OBJECT &&
                    idx > 0 &&
                    instructions[idx - 1].opcode == Opcode.INVOKE_VIRTUAL_RANGE
            }
            addInstructions(
                moveResultIndex + 1,
                "const/4 v8, 0x0",
            )
        }

        // ─────────────────────────────────────────────────────────────────────
        // Patch 3: SsaiInfoTimelineBuilder.buildAdBreaksFromAdSparxAdBreaks()
        // return-void at entry suppresses all SSAI ad break timeline
        // registration for VOD/movies. .locals 16 untouched — execution
        // never reaches any instruction that uses those slots.
        // ─────────────────────────────────────────────────────────────────────
        SsaiInfoTimelineBuilderBuildAdBreaksFingerprint.method.addInstructions(
            0,
            "return-void",
        )

        // ─────────────────────────────────────────────────────────────────────
        // Patch 4: SsaiInfoTimelineBuilder.access$buildAdBreaksFromAdSparxAdBreaks()
        // Synthetic accessor used by buildTimeline inner lambdas. Clear and
        // return-void so the lambda call path is also suppressed.
        // ─────────────────────────────────────────────────────────────────────
        SsaiInfoTimelineBuilderAccessorFingerprint.method.apply {
            implementation!!.instructions.clear()
            addInstructions(0, "return-void")
        }

        // ─────────────────────────────────────────────────────────────────────
        // Patch 5: GenerateLiveTimelineEntriesForAdBreakKt.generateLiveTimelineEntriesForAdBreak()
        // Return empty ArrayList instead of building AdBreakEntry/AdEntry
        // objects. The caller (generateLiveTimelineEntries) does addAll() on
        // the result — empty list means no ad entries added to the live
        // timeline while chapter/content entries are built normally.
        // Suppresses "1 of 2" countdown prerolls on live and episodic TV.
        // ─────────────────────────────────────────────────────────────────────
        GenerateLiveTimelineEntriesForAdBreakFingerprint.method.apply {
            implementation!!.instructions.clear()
            addInstructions(
                0,
                """
                    const-string v0, "adBreaks"
                    invoke-static {p0, v0}, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object;Ljava/lang/String;)V
                    new-instance v0, Ljava/util/ArrayList;
                    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V
                    return-object v0
                """.trimIndent(),
            )
        }
    }
}
