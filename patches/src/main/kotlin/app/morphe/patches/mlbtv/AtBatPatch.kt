/*
 * MLB At Bat Android TV — Ad & Gambling Content Suppression Patch
 *
 * Validated against:
 *   v26.8.1  (versionCode 1750000022) — com.bamnetworks.mobile.android.gameday
 *
 * Coverage:
 *   ✅ VOD ads              — createVodStreamRequest() empty zzdm →
 *                             IMA SDK throws → fallback to pre-cached CDN URL
 *   ✅ Between-innings SSAI — Lb6/k;.b(Uri) empty zzdm →
 *                             ImaServerSideAdInsertionMediaSource fails
 *   ✅ MLB EVI ad segments  — Lz70/b;.o() TXXX cue handler blocked →
 *                             tv-gmc.mlb.com/EVI/ segments never dispatched
 *   ✅ Google DAI ad cues   — Lb6/h$c;.onMetadata() TXXX handler blocked →
 *                             dclk_video_ads segments never inserted
 *   ➡️ Live games           — Untouched (createLiveStreamRequest path)
 *   ➡️ Game stream          — Untouched (tv-gmc.mlb.com/{date}/{gameId}-HD
 *                             segments bypass all patched code paths)
 *
 * WHY TXXX PATCHING WORKS:
 *   tv-gmc.mlb.com serves both game content AND /EVI/ ad segments on the
 *   same domain — DNS blocking kills the stream. Path-level blocking is
 *   impossible at the DNS layer.
 *
 *   The /EVI/ ad segments are scheduled via HLS TXXX timed metadata cues
 *   embedded in the DAI manifest. Both handlers must be blocked:
 *
 *   Lz70/b;.o() — MLB's handler:
 *     TXXX cue → parse ad timing → launch Lz70/d;/Lz70/e; coroutines
 *     → fetch pod metadata → dispatch EVI URLs to ExoPlayer
 *     return-void → ExoPlayer receives no EVI segment URLs → game continues
 *
 *   Lb6/h$c;.onMetadata() — IMA SSAI handler:
 *     TXXX cue → onUserTextReceived() callbacks → DAI segment insertion
 *     return-void → no dclk_video_ads segments inserted
 *
 * BYTECODE VERIFIED:
 *   StreamRequest impl:  Lcom/google/ads/interactivemedia/v3/impl/zzdm;
 *   VOD type constant:   Lcom/google/ads/interactivemedia/v3/internal/zzafs;->zzd
 *   zzdm constructor:    <init>(Lcom/google/ads/interactivemedia/v3/internal/zzafs;)V
 *   VOD patch registers: .registers 6, v0=new zzdm, v1=zzafs type
 *   SSAI patch registers:.registers 8, p1=Uri input, v0=new zzdm, v1=zzafs type
 */

package app.morphe.patches.mlbtv

import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.patch.bytecodePatch
import app.morphe.patches.shared.compat.AppCompatibilities

@Suppress("unused")
val atbatPatch = bytecodePatch(
    name = "MLB At Bat Android TV",
    description = "Removes VOD ads and between-innings gambling ads while preserving live game and stream playback.",
) {
    compatibleWith(AppCompatibilities.MLB_TV)

    execute {
        // ------------------------------------------------------------------
        // Patch 1a: VOD SSAI — createVodStreamRequest (3-arg)
        // Empty zzdm → IMA SDK throws → fallback to pre-cached CDN URL.
        // Live games use createLiveStreamRequest() — separate path, untouched.
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
        // Same as 1a, extra networkCode param doesn't affect register layout.
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
        // Patch 2: Between-Innings SSAI — Lb6/k;.b(Uri)→StreamRequest
        //
        // Parses ssai://dai.google.com URIs for ImaServerSideAdInsertionMediaSource.
        // Empty zzdm → SSAI source fails init → ExoPlayer fallback to plain HLS.
        // Verified: registers=8, p0=this, p1=Uri, v0=new zzdm, v1=zzafs type.
        // ------------------------------------------------------------------
        SsaiStreamRequestFingerprint.method.addInstructions(
            0,
            """
                new-instance v0, Lcom/google/ads/interactivemedia/v3/impl/zzdm;
                sget-object v1, Lcom/google/ads/interactivemedia/v3/internal/zzafs;->zzd:Lcom/google/ads/interactivemedia/v3/internal/zzafs;
                invoke-direct {v0, v1}, Lcom/google/ads/interactivemedia/v3/impl/zzdm;-><init>(Lcom/google/ads/interactivemedia/v3/internal/zzafs;)V
                return-object v0
            """.trimIndent(),
        )

        // ------------------------------------------------------------------
        // Patch 3: MLB TXXX Ad Cue Handler — Lz70/b;.o(Ll5/t;)V
        //
        // Verified: registers=15, TXXX string, Ll5/t; param. Plain void method.
        // Cancels MLB EVI ad dispatch: no pod metadata fetch, no EVI segment
        // URLs delivered to ExoPlayer. Game stream continues uninterrupted.
        // ------------------------------------------------------------------
        MlbTxxxAdCueFingerprint.method.addInstructions(
            0,
            """
                return-void
            """.trimIndent(),
        )

        // ------------------------------------------------------------------
        // Patch 4: IMA SSAI TXXX Handler — Lb6/h$c;.onMetadata(Ll5/t;)V
        //
        // Verified: registers=11, TXXX string, Ll5/t; param, name=onMetadata.
        // Cancels Google DAI (dclk_video_ads) segment insertion alongside
        // MLB EVI. Belt-and-suspenders with Patch 3.
        // ------------------------------------------------------------------
        ImaSsaiTxxxHandlerFingerprint.method.addInstructions(
            0,
            """
                return-void
            """.trimIndent(),
        )
    }
}
