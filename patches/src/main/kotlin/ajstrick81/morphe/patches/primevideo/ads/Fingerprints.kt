package ajstrick81.morphe.patches.primevideo.ads

import app.morphe.patcher.Fingerprint
import com.android.tools.smali.dexlib2.AccessFlags

// ─────────────────────────────────────────────────────────────────────────────
// Hook 1 — media3 ServerSideAdInsertionMediaSource.setAdPlaybackStates()
//
// This method lives in the media3 library. In 6.23.x the app does not
// bundle media3 classes, so this fingerprint is present only for forward
// compatibility. Use matchOrNull() so patch application does not fail when
// the class is absent.
// ─────────────────────────────────────────────────────────────────────────────
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
// Present in 6.23.x via the Google Mobile Ads SDK (classes3.dex).
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
// Fake SUCCESS to suppress impression delivery.
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
// Primary network-layer ad-blocking hook for this build.
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
