package ajstrick81.morphe.patches.primevideo.ads

import app.morphe.patcher.Fingerprint
import com.android.tools.smali.dexlib2.AccessFlags

// ─────────────────────────────────────────────────────────────────────────────
// Hook 1 — media3 ServerSideAdInsertionMediaSource.setAdPlaybackStates()
//
// BUG FIX: This version of Prime Video ATV (6.23.x) does NOT ship media3 in
// its DEX set — there is no `androidx/` package in any classes*.dex.
// Media3 was removed from the bundled player in favour of the standalone
// ExoPlayer2 from GMS Ads SDK (classes3.dex).
//
// The original Fingerprint had NO `optional = true` flag, so Morphe's
// patcher FAILS SILENTLY when the class is not found (the method reference
// is null) and the hook at `SetAdPlaybackStatesMedia3Fingerprint.method`
// throws a NullPointerException at patch-application time, which aborts
// the entire patch execution. That means Hook 2 (Exo2) and Hook 3 (Metrics)
// also never get applied, so zero ad suppression is active at runtime.
//
// FIX: mark this fingerprint `optional = true` so that when the class is
// absent the NPE is not thrown and the remaining hooks continue to execute.
// The SkipAdsPatch.kt execute block already has a null-check guard
// (`?.method`) — if it does not, add one before calling addInstructions().
//
// If a future Prime Video build re-adds media3 the hook will automatically
// activate again without any code change.
// ─────────────────────────────────────────────────────────────────────────────
// NOTE: This fingerprint is used with matchOrNull() in SkipAdsPatch.kt (not
// .method directly) so that when the class is absent in the DEX (as in 6.23.x)
// a null is returned rather than a PatchException that would abort the patch.
object SetAdPlaybackStatesMedia3Fingerprint : Fingerprint(
    definingClass = "Landroidx/media3/exoplayer/source/ads/ServerSideAdInsertionMediaSource;",
    name = "setAdPlaybackStates",
    parameters = listOf(
        "Lcom/google/common/collect/ImmutableMap;",
        "Landroidx/media3/common/Timeline;"
    ),
    returnType = "V",
    accessFlags = listOf(AccessFlags.PUBLIC)
)

// ─────────────────────────────────────────────────────────────────────────────
// Hook 2 — ExoPlayer2 ServerSideAdInsertionMediaSource.setAdPlaybackStates()
//
// This IS present in 6.23.x (classes3.dex, com.google.android.exoplayer2).
// This is the PRIMARY ad suppression hook for this build.
// ─────────────────────────────────────────────────────────────────────────────
object SetAdPlaybackStatesExo2Fingerprint : Fingerprint(
    definingClass = "Lcom/google/android/exoplayer2/source/ads/ServerSideAdInsertionMediaSource;",
    name = "setAdPlaybackStates",
    parameters = listOf(
        "Lcom/google/common/collect/ImmutableMap;"
    ),
    returnType = "V",
    accessFlags = listOf(AccessFlags.PUBLIC)
)

// ─────────────────────────────────────────────────────────────────────────────
// Hook 3 — MetricsTransporter.transmit(SerializedBatch)
//
// Returns a fake SUCCESS UploadResult so Amazon's ad server never receives
// impression delivery data. No change needed; fingerprint is correct.
// ─────────────────────────────────────────────────────────────────────────────
object MetricsTransporterTransmitFingerprint : Fingerprint(
    definingClass = "Lcom/amazon/minerva/client/thirdparty/transport/MetricsTransporter;",
    name = "transmit",
    parameters = listOf(
        "Lcom/amazon/minerva/client/thirdparty/transport/SerializedBatch;"
    ),
    returnType = "Lcom/amazon/minerva/client/thirdparty/transport/UploadResult;",
    accessFlags = listOf(AccessFlags.PUBLIC)
)

// ─────────────────────────────────────────────────────────────────────────────
// Hook 4 — Volley BasicNetwork.performRequest(Request)
//
// No change needed; fingerprint is correct for 6.23.x.
// ─────────────────────────────────────────────────────────────────────────────
object BasicNetworkPerformRequestFingerprint : Fingerprint(
    definingClass = "Lcom/android/volley/toolbox/BasicNetwork;",
    name = "performRequest",
    parameters = listOf(
        "Lcom/android/volley/Request;"
    ),
    returnType = "Lcom/android/volley/NetworkResponse;",
    accessFlags = listOf(AccessFlags.PUBLIC)
)

object MediaItemSetAdsConfigurationFingerprint : Fingerprint(
    definingClass = "Landroidx/media3/common/MediaItem$Builder;",
    name = "setAdsConfiguration",
    parameters = listOf(
        "Landroidx/media3/common/MediaItem$AdsConfiguration;"
    ),
    returnType = "Landroidx/media3/common/MediaItem$Builder;",
    accessFlags = listOf(AccessFlags.PUBLIC)
)

// ─────────────────────────────────────────────────────────────────────────────
// Hook 5 — Force-clear AdsConfiguration on MediaItem.Builder
//
// Strips ad configuration at build time so ExoPlayer/Media3 never enters
// ad mode. Covers the most common setAdsConfiguration setter path.
// SAFE: mark optional so patch build doesn’t hard-fail if missing.
// ─────────────────────────────────────────────────────────────────────────────
object MediaItemSetAdsConfigurationFingerprint : Fingerprint(
    definingClass = "Landroidx/media3/common/MediaItem$Builder;",
    name = "setAdsConfiguration",
    parameters = listOf(
        "Landroidx/media3/common/MediaItem$AdsConfiguration;"
    ),
    returnType = "Landroidx/media3/common/MediaItem$Builder;",
    accessFlags = listOf(AccessFlags.PUBLIC)
)
