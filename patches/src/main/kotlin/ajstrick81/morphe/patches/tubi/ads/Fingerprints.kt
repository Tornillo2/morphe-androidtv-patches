package ajstrick81.morphe.patches.tubi.ads

import app.morphe.patcher.Fingerprint
import com.android.tools.smali.dexlib2.AccessFlags

// ─────────────────────────────────────────────────────────────────────────────
// Hook 1 — FoxImaAdListeners.adEventListener_delegate$lambda$10$lambda$9
// classes.dex / com/fox/android/video/player/api/ima/listeners/
//
// Every Google IMA AdEvent (AD_BREAK_STARTED, AD_STARTED, AD_POD_START,
// AD_PROGRESS, AD_COMPLETED, AD_BREAK_ENDED, etc.) flows through this single
// private static method before reaching FoxPlayer.dispatchAdEvent().
//
// Returning void at index 0 means FoxPlayer never receives any ad event —
// no AD_BREAK_STARTED triggers playback lock, no AD_STARTED triggers ad
// rendering, no AD_POD_START initiates the ad break sequence.
//
// The IMA StreamManager continues running internally; the DAI stream URL
// remains valid and content segments are served normally. Only the event
// dispatch to FoxPlayer is silenced.
//
// Fox Corporation reuses FoxPlayer and FoxImaAdListeners across Tubi,
// Fox One, Fox Sports, and other Fox properties. The same fingerprint
// applies to all of them.
//
// PRIMARY suppression mechanism — blocks mid-roll and post-roll ad events.
// ─────────────────────────────────────────────────────────────────────────────
object FoxImaAdEventListenerFingerprint : Fingerprint(
    definingClass = "Lcom/fox/android/video/player/api/ima/listeners/FoxImaAdListeners;",
    name = "adEventListener_delegate\$lambda\$10\$lambda\$9",
    parameters = listOf(
        "Lcom/fox/android/video/player/api/ima/listeners/FoxImaAdListeners;",
        "Lcom/google/ads/interactivemedia/v3/api/AdEvent;"
    ),
    returnType = "V",
    accessFlags = listOf(AccessFlags.PRIVATE, AccessFlags.STATIC, AccessFlags.FINAL)
)

// ─────────────────────────────────────────────────────────────────────────────
// Hook 2 — FoxImaAdListeners.adsLoadedListener_delegate$lambda$4$lambda$3
// classes.dex / com/fox/android/video/player/api/ima/listeners/
//
// Called by the Google IMA SDK when the AdsManager finishes loading. This
// method is the ONLY place in the Fox IMA stack that calls
// BaseManager.init(), which is what tells the IMA SDK to prime the ad
// timeline and fire the initial AD_BREAK_STARTED for the pre-roll.
//
// Call chain:
//   IMA SDK → AdsLoader.AdsLoadedListener.onAdsManagerLoaded()
//     → adsLoadedListener_delegate$lambda$4$lambda$3()     ← OUR HOOK
//         stores StreamManager in imaStreamManager
//         registers adEventListener / adErrorListener
//         calls BaseManager.init()                         ← triggers pre-roll
//         calls ImaStreamIdCallback.onSuccess(streamId)    ← session confirm
//
// Returning void at index 0 prevents init() from firing, so the IMA SDK
// never primes the pre-roll position and AD_BREAK_STARTED is never issued.
//
// Stream playback URL is NOT affected — it arrives via the separate
// ImaStreamIdLoader.requestVODDAIUrl() → ImaStreamUrlCallback.onSuccess()
// path, which runs independently before this listener ever fires.
// Blocking this lambda only suppresses the IMA session confirmation and
// the pre-roll trigger; content loads and plays normally.
//
// COLD LAUNCH PRE-ROLL suppression — complements Hook 1 which covers
// mid-rolls. Together the two hooks eliminate all IMA ad delivery.
// ─────────────────────────────────────────────────────────────────────────────
object FoxImaAdsLoadedListenerFingerprint : Fingerprint(
    definingClass = "Lcom/fox/android/video/player/api/ima/listeners/FoxImaAdListeners;",
    name = "adsLoadedListener_delegate\$lambda\$4\$lambda\$3",
    parameters = listOf(
        "Lcom/fox/android/video/player/api/ima/listeners/FoxImaAdListeners;",
        "Lcom/google/ads/interactivemedia/v3/api/AdsManagerLoadedEvent;"
    ),
    returnType = "V",
    accessFlags = listOf(AccessFlags.PRIVATE, AccessFlags.STATIC, AccessFlags.FINAL)
)

// ─────────────────────────────────────────────────────────────────────────────
// Hook 3 — FoxPlayer.clearVodAds()
// classes.dex / com/fox/android/video/player/FoxPlayer
//
// FoxPlayer's built-in method that clears the VOD ad schedule. Hooked to
// also call the Java extension which nulls the IMA StreamManager reference
// in FoxImaAdListeners via reflection, preventing any stale IMA session
// from reactivating ad delivery after a clearVodAds call.
//
// Belt-and-suspenders cleanup for edge cases where the StreamManager
// reference survives past Hook 2. Silent no-op if clearVodAds() is not
// called during the playback lifecycle.
// ─────────────────────────────────────────────────────────────────────────────
object FoxPlayerClearVodAdsFingerprint : Fingerprint(
    definingClass = "Lcom/fox/android/video/player/FoxPlayer;",
    name = "clearVodAds",
    parameters = listOf(),
    returnType = "V",
    accessFlags = listOf(AccessFlags.PRIVATE)
)

// ─────────────────────────────────────────────────────────────────────────────
// Hook 4 — ImagePauseAds.l(VideoApi, long)
// classes9.dex / com/tubitv/features/player/presenters/pauseads/
//
// Tubi-specific pause ad entry point. Called when the user pauses playback,
// passing the current VideoApi metadata and an elapsed time offset. The
// method launches a coroutine (ImagePauseAds$f) that:
//   1. Fetches a PauseAdsResponse from Tubi's ad server
//   2. Downloads the creative image
//   3. Displays it via PauseAdsView as an overlay on the pause screen
//   4. Fires impression/view tracking events on show and dismiss
//
// This is entirely separate from the IMA DAI linear ad pipeline — it is
// a Tubi-owned display ad system with its own network requests and view
// hierarchy. Hooks 1–3 have no effect on it.
//
// Returning void at index 0 prevents the coroutine from ever being created.
// The pause screen renders normally; the ad overlay is simply never fetched
// or displayed. No tracking events fire (no impression to report).
// ─────────────────────────────────────────────────────────────────────────────
object TubiPauseAdsFingerprint : Fingerprint(
    definingClass = "Lcom/tubitv/features/player/presenters/pauseads/ImagePauseAds;",
    name = "l",
    parameters = listOf("Lcom/tubitv/core/api/models/VideoApi;", "J"),
    returnType = "V",
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.FINAL)
)
