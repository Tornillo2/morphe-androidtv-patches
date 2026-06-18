/*
 * MLB At Bat Android TV — Ad & Gambling Content Suppression Patch
 *
 * Validated against:
 *   v26.8.1  (versionCode 1750000022) — com.bamnetworks.mobile.android.gameday
 *
 * Coverage:
 *   ✅ VOD ads              — createVodStreamRequest() returns empty zzdm →
 *                             IMA SDK throws → fallback to pre-cached CDN URL
 *   ✅ Gambling ads (VOD)   — FanDuel, DraftKings, BetMGM via IMA SDK path
 *   ✅ Between-innings ads  — Lb6/k;.b(Uri) returns empty zzdm →
 *                             ImaServerSideAdInsertionMediaSource fails to init
 *                             → ExoPlayer falls back to plain HLS without ads
 *   ➡️ Live games           — Untouched (createLiveStreamRequest separate path)
 *
 * BYTECODE VERIFIED — IMA SDK StreamRequest in this APK version:
 *   Class:       Lcom/google/ads/interactivemedia/v3/impl/zzdm;
 *   Constructor: <init>(Lcom/google/ads/interactivemedia/v3/internal/zzafs;)V
 *   VOD type:    Lcom/google/ads/interactivemedia/v3/internal/zzafs;->zzd
 *   Live type:   Lcom/google/ads/interactivemedia/v3/internal/zzafs;->zzc (untouched)
 *
 * VOD PATCH STRATEGY (Patches 1a/1b):
 *   Same empty-StreamRequest technique as Paramount+ v16.8.0 but updated
 *   to use zzdm/zzafs (the correct classes in this APK version).
 *   .registers 6, v0=new zzdm, v1=zzafs VOD type constant.
 *   No setter methods called → IMA SDK throws on requestStream() → fallback.
 *
 * BETWEEN-INNINGS PATCH STRATEGY (Patch 2):
 *   Root cause confirmed via logcat + bytecode: ads are SSAI-stitched into
 *   the HLS manifest server-side by dai.google.com. ExoPlayer plays them
 *   as normal video segments — app-level event patches fire too late.
 *
 *   Lb6/k;.b(Uri) parses ssai://dai.google.com URIs into StreamRequests
 *   for ImaServerSideAdInsertionMediaSource (Lb6/h;). Return empty zzdm
 *   here → SSAI media source constructor receives unpopulated StreamRequest
 *   → fails initialization → ExoPlayer falls back to plain HLS → no ads.
 *
 *   Same empty-StreamRequest concept as VOD, same register layout.
 *   .registers 8, p0=this, p1=Uri, v0=new zzdm, v1=zzafs VOD type.
 */

package app.morphe.patches.mlbtv

import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.patch.bytecodePatch
import app.morphe.patches.shared.compat.AppCompatibilities

@Suppress("unused")
val atbatPatch = bytecodePatch(
    name = "MLB At Bat Android TV",
    description = "Removes VOD ads and between-innings gambling ads while preserving live game playback.",
) {
    compatibleWith(AppCompatibilities.MLB_TV)

    execute {
        // ------------------------------------------------------------------
        // Patch 1a: VOD SSAI — createVodStreamRequest (3-arg)
        //
        // Returns empty zzdm StreamRequest (no setters called).
        // IMA SDK throws on requestStream() → exception caught →
        // AVIA falls back to pre-cached CDN URL → VOD plays without ads.
        //
        // Bytecode verified: .registers 6
        //   v0 = new zzdm instance
        //   v1 = zzafs VOD type constant (zzd field)
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
        // Returning empty zzdm here causes SSAI media source init to fail →
        // ExoPlayer falls back to plain HLS → no between-innings gambling ads.
        //
        // Bytecode verified: .registers 8
        //   p0 = this, p1 = Uri (input parameter)
        //   v0 = new zzdm instance (safe — not a parameter register)
        //   v1 = zzafs VOD type constant
        //
        // The SSAI source uses the StreamRequest to request a DAI stream.
        // Without valid contentSourceId/assetKey/videoId, the request fails
        // and ExoPlayer's fallback mechanism serves the plain HLS stream.
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
    }
}
