package ajstrick81.morphe.patches.primevideo.ads

import app.morphe.patcher.Fingerprint
import com.android.tools.smali.dexlib2.AccessFlags

// ─────────────────────────────────────────────────────────────────────────────
// Primary target — media3 SSAI ad schedule entry point
// classes.dex / smali/androidx/media3/exoplayer/source/ads/
//
// Called by the Ignite native layer (libignite.so + downloaded JS bundle)
// when it pushes the SSAI ad schedule into ExoPlayer. The ImmutableMap
// carries one AdPlaybackState per period UID, each containing the full set
// of AdGroups with their timing, duration, and individual ad URIs.
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
// Secondary target — ExoPlayer2 SSAI ad schedule entry point
// classes4.dex / smali/com/google/android/exoplayer2/source/ads/
//
// The ExoPlayer2 SSAI source is bundled inside the Google Mobile Ads SDK.
// Same transformation applied. ExoPlayer2 variant takes only ImmutableMap.
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
// Tertiary target — DefaultHttpDataSource.open(DataSpec)
// classes2.dex / smali/androidx/media3/datasource/
//
// This is the media3 HTTP data source used to fetch ALL media segments —
// both content and ad segments. Called every time a new segment URL is opened.
//
// The DataSpec parameter carries a public final uri:android.net.Uri field
// containing the full URL being requested. At index 0 we read this URI,
// check it against known ad CDN patterns (PCAP-validated), and return 0L
// (zero bytes available) for ad segment URLs before any network connection
// is established.
//
// Returning 0L causes media3 to treat the segment as empty and advance the
// playhead past it — the player never downloads or renders the ad content.
// This operates at the segment fetch layer, BEFORE the WASM runtime can
// pre-buffer content, which is why setAdPlaybackStates alone cannot stop
// pre-roll ads that are buffered before the hook fires.
//
// Together with the two SSAI fingerprints above this provides defense in
// depth across both the ad schedule layer and the segment delivery layer.
//
// Ad URL patterns matched (PCAP-confirmed from Onn 4K TV session):
//   avoddashs3ww-a.akamaihd.net        — Akamai ad video segments
//   aivottevtad-a.akamaihd.net         — Akamai ad event tracking
//   vod-dash-pv-ta-amazon.akamaized.net — Amazon Akamai CDN ad segments
//   ters-*.aiv-delivery.net            — SGAI ad stitching server
//   vod-dash.main.amazon.pv-cdn.net    — Amazon PV-CDN ad segments
//   pop-vod-dash.main.amazon.pv-cdn.net — Amazon PV-CDN pop variant
//
// Excluded from blocking (content delivery):
//   cf-trickplay.aux.pv-cdn.net        — scrubber thumbnail CDN
//   cf-timedtext.aux.pv-cdn.net        — subtitle/caption CDN
//   api.us-east-1.aiv-delivery.net     — content manifest API
// ─────────────────────────────────────────────────────────────────────────────
object DefaultHttpDataSourceOpenFingerprint : Fingerprint(
    definingClass = "Landroidx/media3/datasource/DefaultHttpDataSource;",
    name = "open",
    parameters = listOf("Landroidx/media3/datasource/DataSpec;"),
    returnType = "J",
    accessFlags = listOf(AccessFlags.PUBLIC)
)
