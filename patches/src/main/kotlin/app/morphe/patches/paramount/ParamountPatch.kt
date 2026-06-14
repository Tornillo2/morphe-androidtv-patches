/*
 * Paramount+ Android TV — Ad Suppression Patch
 *
 * Validated against:
 *   v16.8.0  (versionCode 520000464) — com.cbs.ott
 *
 * Coverage:
 *   ✅ VOD SSAI ads  — createVodStreamRequest() returns null → non-critical
 *                      DAI fail → AVIA falls back to cbsaavideo.com URL →
 *                      content plays without SSAI ads
 *   ✅ Pause ads     — CbsPauseWithAdsOverlay state machine
 *   ➡️ Live TV ads   — untouched by design (no fallback URL exists for live)
 *
 * KEY ARCHITECTURAL INSIGHT (confirmed via logcat + ImaSdkFactory.smali):
 *
 *   VOD error payload always contains:
 *     "resourceUrl": "https://vod-gcs-cedexis.cbsaavideo.com/.../stream.mpd"
 *   This URL is fetched BEFORE DAI is attempted. When DAI fails non-critically
 *   (null StreamRequest → null guard fires in pk0.run()), AVIA falls back to
 *   this URL and plays content without SSAI ads.
 *
 *   Live TV error payload has NO resourceUrl. DAI is the only manifest source.
 *   Patching createLiveStreamRequest() kills live TV completely.
 *
 * NULL vs EMPTY OBJECT — WHY NULL:
 *   Returning an empty zzcx (with no setters called) causes requestStream()
 *   to be called with invalid params → IMA SDK throws critical exception →
 *   error 6007 → AVIA terminates playback, no fallback.
 *
 *   Returning null causes the null guard in pk0.run() to fire:
 *     "DAI AdsLoader or StreamRequest is null, cannot request DAI stream."
 *   This is a non-critical log path → AVIA falls back to cbsaavideo.com.
 *
 * LIVE TV NOTE:
 *   createLiveStreamRequest() (both overloads) is NOT patched.
 *   Uses zzafv;->zzc (TRUMAN_STITCHED_MANIFEST_LINEAR) + zza(assetKey).
 *   Completely separate code path from createVodStreamRequest().
 */

package app.morphe.patches.paramount

import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.patch.bytecodePatch
import app.morphe.patches.shared.compat.AppCompatibilities

@Suppress("unused")
val paramountPatch = bytecodePatch(
    name = "Paramount+ Android TV",
    description = "Removes VOD ads and pause ads while preserving live TV.",
) {
    compatibleWith(AppCompatibilities.PARAMOUNT_TV)

    execute {
        // ------------------------------------------------------------------
        // Patch 1 & 2: VOD SSAI — createVodStreamRequest (3-arg and 4-arg)
        //
        // Returning null triggers the null guard in pk0.run() rather than
        // calling requestStream() with invalid params. This is the non-critical
        // failure path that allows AVIA to fall back to the pre-fetched
        // cbsaavideo.com content URL and play without SSAI ads.
        //
        // Register note: both overloads have .locals 2, using v0 and v1.
        // const/4 v0, 0x0 is safe — v0 is a local register in both methods.
        // ------------------------------------------------------------------
        arrayOf(
            VodStreamRequest3ArgFingerprint,
            VodStreamRequest4ArgFingerprint,
        ).forEach { fingerprint ->
            fingerprint.method.addInstructions(
                0,
                """
                    const/4 v0, 0x0
                    return-object v0
                """.trimIndent(),
            )
        }

        // ------------------------------------------------------------------
        // Patch 3: Pause ads — CbsPauseWithAdsOverlay state machine
        //
        // return-void at offset 0 prevents Glide image fetch, alpha fade-in,
        // and overlay render event. Overlay stays at alpha=0 during pause.
        // Independent of IMA DAI — unaffected by the VOD null-return patches.
        // ------------------------------------------------------------------
        PauseAdOverlayFingerprint.method.addInstructions(
            0,
            """
                return-void
            """.trimIndent(),
        )
    }
}
