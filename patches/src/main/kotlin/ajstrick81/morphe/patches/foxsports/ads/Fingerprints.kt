package ajstrick81.morphe.patches.foxone.ads

import app.morphe.patcher.Fingerprint
import com.android.tools.smali.dexlib2.AccessFlags

// ─────────────────────────────────────────────────────────────────────────────
// Fox One v1.9.2 (com.fox.foxone) — Ad suppression fingerprints
//
// ARCHITECTURE NOTE:
// Fox One is a FULLY NATIVE app. Unlike Tubi (which uses a WebView-SPA hybrid
// where the pre-roll runs in a sandboxed Chrome renderer), Fox One uses the
// Fox player SDK directly with no WebView ad layer. All ad delivery is
// reachable via bytecode patches.
//
// Fox Corp ships the same compiled player SDK binary across Fox One, Tubi, and
// Fox Sports. The IMA/DAI classes (FoxImaAdListeners, FoxImaStreamIdLoader,
// FoxPlayer) are byte-for-byte identical across all three apps. The Yospace
// SSAI classes are Fox One / Fox Sports specific — Tubi is VOD-only.
//
// Two ad pipelines:
//   VOD content   → Google IMA DAI  (Hooks 1–5)
//   Live content  → Yospace SSAI    (Hooks 6–8)
//
// No WebViewClient hook needed — Fox One has no WebView ad layer.
// No ImagePauseAds equivalent — Fox One has no pause ad overlay.
// ─────────────────────────────────────────────────────────────────────────────

// ─────────────────────────────────────────────────────────────────────────────
// Hook 1 — FoxImaAdListeners.adEventListener_delegate$lambda$10$lambda$9
//
// Every Google IMA ad event (AD_BREAK_STARTED, AD_STARTED, AD_PROGRESS,
// AD_COMPLETED, etc.) flows through this single lambda before reaching
// FoxPlayer.dispatchAdEvent(). Silencing it means FoxPlayer never receives
// any ad lifecycle event. Primary suppression for all IMA-driven ad events.
//
// Unique string: "adEvent" (param null check, only occurrence in this method)
// ─────────────────────────────────────────────────────────────────────────────
object FoxImaAdEventListenerFingerprint : Fingerprint(
    definingClass = "Lcom/fox/android/video/player/api/ima/listeners/FoxImaAdListeners;",
    name = "adEventListener_delegate\$lambda\$10\$lambda\$9",
    strings = listOf("adEvent"),
    accessFlags = listOf(AccessFlags.PRIVATE, AccessFlags.STATIC, AccessFlags.FINAL)
)

// ─────────────────────────────────────────────────────────────────────────────
// Hook 2 — FoxImaAdListeners.adsLoadedListener_delegate$lambda$4$lambda$3
//
// Called when the IMA AdsManager finishes loading. Only place that calls
// imaStreamManager.init() to prime the ad timeline and fire the initial
// AD_BREAK_STARTED for pre-roll. Blocking prevents the IMA session from
// ever being initialised.
//
// Unique string: "onAdsManagerLoaded"
// ─────────────────────────────────────────────────────────────────────────────
object FoxImaAdsLoadedListenerFingerprint : Fingerprint(
    definingClass = "Lcom/fox/android/video/player/api/ima/listeners/FoxImaAdListeners;",
    name = "adsLoadedListener_delegate\$lambda\$4\$lambda\$3",
    strings = listOf("onAdsManagerLoaded"),
    accessFlags = listOf(AccessFlags.PRIVATE, AccessFlags.STATIC, AccessFlags.FINAL)
)

// ─────────────────────────────────────────────────────────────────────────────
// Hook 3 — FoxPlayer.clearVodAds()
//
// Called during content transitions to clear VOD ad state. Nulls out
// ad state fields to prevent stale IMA sessions from reactivating.
// ─────────────────────────────────────────────────────────────────────────────
object FoxPlayerClearVodAdsFingerprint : Fingerprint(
    definingClass = "Lcom/fox/android/video/player/FoxPlayer;",
    name = "clearVodAds",
    accessFlags = listOf(AccessFlags.PRIVATE, AccessFlags.FINAL)
)

// ─────────────────────────────────────────────────────────────────────────────
// Hook 4 — FoxImaStreamIdLoader.requestVODDAIUrl(String, Boolean, ImaStreamUrlCallback)
//
// Initiates the Google DAI stream request for VOD content. DAI-stitched
// streams embed ad segments into the HLS manifest before the player sees
// the URL. Calling onFailure("dai_blocked") triggers Fox One's fallback
// to a clean non-stitched stream.
//
// Unique string: "requestVODDAIUrl() BEGIN..."
// ─────────────────────────────────────────────────────────────────────────────
object FoxImaVodStreamRequestFingerprint : Fingerprint(
    definingClass = "Lcom/fox/android/video/player/api/ima/loaders/FoxImaStreamIdLoader;",
    name = "requestVODDAIUrl",
    strings = listOf("requestVODDAIUrl() BEGIN..."),
    parameters = listOf(
        "Ljava/lang/String;",
        "Ljava/lang/Boolean;",
        "Lcom/fox/android/video/player/loaders/ImaStreamIdLoader\$ImaStreamUrlCallback;"
    ),
    returnType = "V",
    accessFlags = listOf(AccessFlags.PUBLIC)
)

// ─────────────────────────────────────────────────────────────────────────────
// Hook 5 — FoxImaStreamIdLoader.requestImaStreamId(String, Boolean, ImaStreamIdCallback)
//
// Live stream equivalent of Hook 4. Same intercept pattern.
//
// Unique string: "requestImaStreamId BEGIN..."
// ─────────────────────────────────────────────────────────────────────────────
object FoxImaLiveStreamRequestFingerprint : Fingerprint(
    definingClass = "Lcom/fox/android/video/player/api/ima/loaders/FoxImaStreamIdLoader;",
    name = "requestImaStreamId",
    strings = listOf("requestImaStreamId BEGIN..."),
    parameters = listOf(
        "Ljava/lang/String;",
        "Ljava/lang/Boolean;",
        "Lcom/fox/android/video/player/loaders/ImaStreamIdLoader\$ImaStreamIdCallback;"
    ),
    returnType = "V",
    accessFlags = listOf(AccessFlags.PUBLIC)
)

// ─────────────────────────────────────────────────────────────────────────────
// Hook 6 — YospaceAnalyticEventObserver.dispatchAdEvent(FoxAdEvent)
//
// LIVE CONTENT — Yospace SSAI ad event dispatcher. Every Yospace ad lifecycle
// event flows through this coroutine launcher before reaching FoxPlayer.
// Silencing it prevents FoxPlayer from entering ad-locked mode during live
// sports/news content, keeping the scrub bar active.
//
// Fox One uses Yospace for all live content via foxdtc-video.akamaized.net
// with yo.* HLS query parameters.
// ─────────────────────────────────────────────────────────────────────────────
object YospaceDispatchAdEventFingerprint : Fingerprint(
    definingClass = "Lcom/fox/android/video/player/yospace/listener/YospaceAnalyticEventObserver;",
    name = "dispatchAdEvent",
    parameters = listOf("Lcom/fox/android/video/player/event/FoxAdEvent;"),
    returnType = "V",
    accessFlags = listOf(AccessFlags.PRIVATE, AccessFlags.FINAL)
)

// ─────────────────────────────────────────────────────────────────────────────
// Hook 7 — YospaceAnalyticEventObserver.dispatchSlateEvent(FoxSlateEvent)
//
// LIVE CONTENT — Yospace slate event dispatcher. Slate events are "black
// screen" placeholders during live ad breaks when no creative is available.
// Blocking alongside Hook 6 suppresses both creatives and slate placeholders.
// ─────────────────────────────────────────────────────────────────────────────
object YospaceDispatchSlateEventFingerprint : Fingerprint(
    definingClass = "Lcom/fox/android/video/player/yospace/listener/YospaceAnalyticEventObserver;",
    name = "dispatchSlateEvent",
    parameters = listOf("Lcom/fox/android/video/player/event/FoxSlateEvent;"),
    returnType = "V",
    accessFlags = listOf(AccessFlags.PRIVATE, AccessFlags.FINAL)
)

// ─────────────────────────────────────────────────────────────────────────────
// Hook 8 — YospaceSeekablePlaybackPolicyHandler.setHandleFastForwardSeek
//
// LIVE CONTENT — Nulls out Yospace's fast-forward seek policy handler.
// Without this, the player locks the scrub bar during Yospace ad breaks
// even when the event hooks (6–7) prevent ad rendering — seek policy
// enforcement is a separate code path from event dispatch.
// ─────────────────────────────────────────────────────────────────────────────
object YospaceSeekPolicyFingerprint : Fingerprint(
    definingClass = "Lcom/fox/android/video/player/yospace/handler/YospaceSeekablePlaybackPolicyHandler;",
    name = "setHandleFastForwardSeek",
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.FINAL)
)
