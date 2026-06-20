package ajstrick81.morphe.patches.foxsports.ads

import app.morphe.patcher.Fingerprint
import com.android.tools.smali.dexlib2.AccessFlags

// ─────────────────────────────────────────────────────────────────────────────
// Fox Sports Android TV v5.152.0 — Ad suppression fingerprints
//
// ARCHITECTURE vs v5.x PREVIOUS:
// Fox Sports v5.152 has DROPPED the Google IMA/DAI pipeline entirely.
// Binary analysis confirms:
//   ❌ FoxImaAdListeners       — removed
//   ❌ FoxImaStreamIdLoader    — removed
//   ❌ clearVodAds             — removed
//   ❌ Google DAI              — removed
//   ❌ Rainmaker               — not present (Fox Sports specific, not Tubi)
//   ❌ FreeWheel SDK           — URL strings only (config remnants, no live SDK)
//   ✅ Yospace SSAI            — primary and only active ad system
//   ✅ ExoPlayer               — video player (not Bitmovin)
//   ✅ WebViewClient           — standard Android WebView, NOT a hybrid SPA
//                                (no DWebView/DSBridge — no WebView ad layer)
//
// The ad stack is now purely Yospace SSAI via the Fox player SDK.
// Live sports content is stitched at the CDN level (foxdtc domain).
// Three hooks suppress the entire ad pipeline.
// ─────────────────────────────────────────────────────────────────────────────

// Hook 1 — YospaceAnalyticEventObserver.dispatchAdEvent(FoxAdEvent)
//
// Every Yospace ad lifecycle event (ad start, progress, complete, break
// start/end) flows through this coroutine launcher before reaching the player.
// Returning void prevents FoxPlayer from entering ad-locked mode, keeps the
// scrub bar active, and prevents ad UI rendering during live content.
object YospaceDispatchAdEventFingerprint : Fingerprint(
    definingClass = "Lcom/fox/android/video/player/yospace/listener/YospaceAnalyticEventObserver;",
    name = "dispatchAdEvent",
    parameters = listOf("Lcom/fox/android/video/player/event/FoxAdEvent;"),
    returnType = "V",
    accessFlags = listOf(AccessFlags.PRIVATE, AccessFlags.FINAL)
)

// Hook 2 — YospaceAnalyticEventObserver.dispatchSlateEvent(FoxSlateEvent)
//
// Yospace slate events — "black screen" placeholders during live ad breaks
// when no creative is available. Blocking alongside Hook 1 suppresses both
// creatives and slate placeholders during live playback.
object YospaceDispatchSlateEventFingerprint : Fingerprint(
    definingClass = "Lcom/fox/android/video/player/yospace/listener/YospaceAnalyticEventObserver;",
    name = "dispatchSlateEvent",
    parameters = listOf("Lcom/fox/android/video/player/event/FoxSlateEvent;"),
    returnType = "V",
    accessFlags = listOf(AccessFlags.PRIVATE, AccessFlags.FINAL)
)

// Hook 3 — YospaceSeekablePlaybackPolicyHandler.setHandleFastForwardSeek
//
// Yospace enforces a seek policy that locks the scrub bar during ad breaks.
// This hook nulls out the fast-forward seek handler so ad segments are freely
// seekable. Without this, the scrub bar locks even when Hooks 1–2 prevent
// ad rendering — seek policy enforcement is a separate code path.
object YospaceSeekPolicyFingerprint : Fingerprint(
    definingClass = "Lcom/fox/android/video/player/yospace/handler/YospaceSeekablePlaybackPolicyHandler;",
    name = "setHandleFastForwardSeek",
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.FINAL)
)
