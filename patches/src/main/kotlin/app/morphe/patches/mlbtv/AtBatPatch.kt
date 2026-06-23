/*
 * MLB At Bat Android TV — Ad Suppression & Gambling-Ad Overlay Patch
 *
 * Validated against:
 *   v26.8.1.1 — com.bamnetworks.mobile.android.gameday.atbat
 *   (re-verified via androguard dex analysis of this exact build, 7 classesN.dex)
 *
 * Coverage:
 *   ✅ VOD ads              — createVodStreamRequest() empty zzdm →
 *                             IMA SDK throws → fallback to pre-cached CDN URL
 *   ✅ Commercial-break overlay — shields viewers from gambling ad content
 *                             during live SSAI/DAI ad breaks (Patch 5) without
 *                             touching playback/DAI request plumbing, so it's
 *                             safe for VOD and live games alike.
 *
 * ARCHITECTURE NOTE — why Patches 2/3/4 are disabled below:
 *
 *   Earlier iterations of this patch tried to block the SSAI/DAI session
 *   outright (Patches 2/3: void Lb6/h;.b0()/m0()) and the upstream TXXX
 *   metadata dispatcher IMA's StreamManager relies on internally to detect
 *   ad-break boundaries (Patch 4: void onMetadata()). Both approaches are
 *   unconfirmed-safe for LIVE games specifically — blocking b0()/m0() risks
 *   breaking live DAI playback entirely, not just ads, since there is no
 *   verified pre-DAI fallback path for live content the way there is for VOD.
 *
 *   Patch 5 below takes a different approach: instead of blocking the SSAI
 *   network plumbing, it hooks the existing no-op onAdBreakStarted()/
 *   onAdBreakEnded() IMA callbacks to show/hide a full-screen "Commercial
 *   Break in Progress" overlay over the ad view group. This never touches
 *   the playback pipeline, so it carries none of the live-stream risk.
 *
 *   Patches 2/3/4 are kept disabled (commented out, not deleted) because
 *   each one severs a step in the chain Patch 5 depends on:
 *     - Patch 2/3 block the SSAI session outright → onAdBreakStarted/Ended
 *       never fire at all.
 *     - Patch 4 blocks the TXXX dispatch path IMA uses internally for
 *       ad-break boundary detection → same effect.
 *   If re-enabling any of them, Patch 5's overlay will stop firing.
 */

package app.morphe.patches.mlbtv

import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.patch.bytecodePatch
import app.morphe.patches.shared.compat.AppCompatibilities
import com.android.tools.smali.dexlib2.Opcode
import com.android.tools.smali.dexlib2.iface.instruction.OneRegisterInstruction
import com.android.tools.smali.dexlib2.iface.instruction.ReferenceInstruction
import com.android.tools.smali.dexlib2.iface.reference.MethodReference

@Suppress("unused")
val atbatPatch = bytecodePatch(
    name = "MLB At Bat Android TV",
    description = "Removes VOD ads and shows a commercial-break overlay over live gambling ads " +
        "while preserving live game playback.",
) {
    compatibleWith(AppCompatibilities.MLB_TV)

    extendWith("extensions/extension.mpe")

    execute {
        // ------------------------------------------------------------------
        // Patch 1a: VOD SSAI — createVodStreamRequest (3-arg)
        // ------------------------------------------------------------------
        VodStreamRequest3ArgFingerprint.method.addInstructions(
            0,
            """
                new-instance v0, Lcom/google/ads/interactivemedia/v3/impl/zzdm;
                sget-object v1, Lcom/google/ads/interactivemedia/v3/internal/zzafs;->zzd:Lcom/google/ads/interactivemedia/v3/internal/zzafs;
                invoke-direct {v0, v1}, Lcom/google/ads/interactivemedia/v3/impl/zzdm;-><init>(Lcom/google/ads/interactivemedia/v3/internal/zzafs;)V
                return-object v0
            """.trimIndent(),
        )

        // ------------------------------------------------------------------
        // Patch 1b: VOD SSAI — createVodStreamRequest (4-arg)
        // ------------------------------------------------------------------
        VodStreamRequest4ArgFingerprint.method.addInstructions(
            0,
            """
                new-instance v0, Lcom/google/ads/interactivemedia/v3/impl/zzdm;
                sget-object v1, Lcom/google/ads/interactivemedia/v3/internal/zzafs;->zzd:Lcom/google/ads/interactivemedia/v3/internal/zzafs;
                invoke-direct {v0, v1}, Lcom/google/ads/interactivemedia/v3/impl/zzdm;-><init>(Lcom/google/ads/interactivemedia/v3/internal/zzafs;)V
                return-object v0
            """.trimIndent(),
        )

        // ------------------------------------------------------------------
        // Patch 2 (DISABLED) — SSAI MediaSource Startup — Lb6/h;.b0(Lq5/w;)V
        //
        // Verified: string="ImaServerSideAdInsertionMediaSource" (UNIQUE in APK)
        // proto=(Lq5/w;)V, registers=10
        //
        // Called when ImaServerSideAdInsertionMediaSource starts up.
        // return-void would prevent: Lb6/h$g; construction → requestStream()
        // call → DAI manifest URL generation → dclk_video_ads segments.
        //
        // Disabled: blocks the SSAI session outright, which also prevents
        // onAdBreakStarted()/onAdBreakEnded() from ever firing — Patch 5's
        // overlay depends on those callbacks. Unconfirmed-safe for live
        // games besides. See top-of-file architecture note.
        // ------------------------------------------------------------------
        // SsaiMediaSourceStartupFingerprint.method.addInstructions(
        //     0,
        //     """
        //         return-void
        //     """.trimIndent(),
        // )

        // ------------------------------------------------------------------
        // Patch 3 (DISABLED) — DAI StreamManager Event Handler — Lb6/h;.m0(StreamManager)V
        //
        // Verified: strings="IMA DAI Stream Event: ", "GSTREAM:DAI"
        // Belt-and-suspenders: would prevent StreamManager from processing
        // DAI stream and scheduling ad segments even if Patch 2 is bypassed.
        //
        // Disabled for the same reason as Patch 2 — see top-of-file note.
        // ------------------------------------------------------------------
        // DaiStreamManagerHandlerFingerprint.method.addInstructions(
        //     0,
        //     """
        //         return-void
        //     """.trimIndent(),
        // )

        // ------------------------------------------------------------------
        // Patch 4 (DISABLED) — TXXX Metadata Dispatcher — Lu70/i;.onMetadata(Ll5/t;)V
        //
        // Blocks ALL HLS timed metadata dispatch:
        //   → Lz70/b;.o() never called → MLB EVI coroutines never launched
        //   → Lb6/h$c;.onMetadata() never called → IMA cues suppressed
        //
        // Disabled: this is also the path IMA's StreamManager uses
        // internally to detect ad-break boundaries — blocking it prevents
        // onAdBreakStarted()/onAdBreakEnded() from firing, same conflict as
        // Patches 2/3. See top-of-file architecture note.
        // ------------------------------------------------------------------
        // ExoMediaPlayerMetadataFingerprint.method.addInstructions(
        //     0,
        //     """
        //         return-void
        //     """.trimIndent(),
        // )

        // ------------------------------------------------------------------
        // Patch 5: Ad-Break Overlay
        //
        // 5a — registers the SSAI ad view group with the extension helper.
        // Lb6/h$d;.b(Ll5/q;)Lg6/w; calls
        //   Ll5/d;->getAdViewGroup()Landroid/view/ViewGroup;
        // immediately before passing the result into
        //   ImaSdkFactory.createStreamDisplayContainer(ViewGroup, VideoStreamPlayer)
        // The register holding the ViewGroup is located dynamically (rather
        // than hardcoded) since it can shift between app builds.
        // ------------------------------------------------------------------
        SsaiDisplayContainerFingerprint.method.apply {
            val instructions = implementation!!.instructions
            val getAdViewGroupIndex = instructions.indexOfFirst { instruction ->
                instruction.opcode == Opcode.INVOKE_INTERFACE &&
                    ((instruction as ReferenceInstruction).reference as? MethodReference)?.name ==
                        "getAdViewGroup"
            }
            val moveResultIndex = getAdViewGroupIndex + 1
            val adViewGroupRegister =
                (instructions[moveResultIndex] as OneRegisterInstruction).registerA
            val totalRegisters = implementation!!.registerCount
            val paramRegisters = parameters.size + 1 // +1 for implicit `this` (p0)
            val firstParamRegister = totalRegisters - paramRegisters
            val registerName = if (adViewGroupRegister >= firstParamRegister) {
                "p${adViewGroupRegister - firstParamRegister}"
            } else {
                "v$adViewGroupRegister"
            }

            addInstructions(
                moveResultIndex + 1,
                """
                    invoke-static {$registerName}, Lajstrick81/morphe/extension/mlbtv/ads/AdBreakOverlayHelper;->registerAdViewGroup(Landroid/view/ViewGroup;)V
                """.trimIndent(),
            )
        }

        // 5b/5c — wire the no-op ad-break lifecycle callbacks to show/hide
        // the overlay. Both bodies are a single return-void (1 register,
        // `this`), so prepending at index 0 is unconditionally safe — no
        // registers are live yet for the verifier to conflict over.
        AdBreakStartedFingerprint.method.addInstructions(
            0,
            """
                invoke-static {}, Lajstrick81/morphe/extension/mlbtv/ads/AdBreakOverlayHelper;->showOverlay()V
            """.trimIndent(),
        )

        AdBreakEndedFingerprint.method.addInstructions(
            0,
            """
                invoke-static {}, Lajstrick81/morphe/extension/mlbtv/ads/AdBreakOverlayHelper;->hideOverlay()V
            """.trimIndent(),
        )
    }
}
