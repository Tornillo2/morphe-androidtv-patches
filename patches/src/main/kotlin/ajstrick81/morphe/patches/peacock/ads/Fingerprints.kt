package ajstrick81.morphe.patches.peacock.ads

import app.morphe.patcher.Fingerprint
import com.android.tools.smali.dexlib2.AccessFlags

// ─────────────────────────────────────────────────────────────────────────────
// Primary target — MultiPeriodAdsMediaSource$ComponentListener.a(AdPlaybackState)
// smali/com/comcast/helio/hacks/multiperiodads/
//
// This is the Helio framework's CSAI ad state update entry point — the
// functional equivalent of ServerSideAdInsertionMediaSource.setAdPlaybackStates()
// in Prime Video ATV, but for Peacock's Comcast Helio player.
//
// The ComponentListener implements MultiPeriodAdsLoader$EventListener.
// Method a() is called by HelioAdsLoader when it has a new AdPlaybackState
// to push into the MultiPeriodAdsMediaSource pipeline.
//
// The implementation:
//   1. Checks if the listener is stopped (field b:Z) — returns early if true
//   2. Gets the Handler (field a)
//   3. Creates a Runnable (class a) that wraps the AdPlaybackState
//   4. Posts the Runnable to the Handler for playback thread execution
//
// Intercepting at index 0 with return-void means the AdPlaybackState
// is never posted to the playback Handler and ExoPlayer never receives
// the ad schedule — no ad groups, no ad break state, no ads play.
//
// Fingerprint uses the obfuscated method name "a" which is stable within
// this version. The definingClass is unobfuscated (Comcast's own framework
// classes are not minified), making this fingerprint durable across minor
// Peacock APK updates as long as the Helio framework version is consistent.
//
// Note: method name is "a" (single character) — R8 obfuscated the name but
// kept the class name. The parameter type AdPlaybackState is from the
// unobfuscated media3 library and uniquely identifies this overload since
// method b() takes a different parameter type (MultiPeriodAdLoadException).
// ─────────────────────────────────────────────────────────────────────────────
object HelioComponentListenerAdPlaybackStateFingerprint : Fingerprint(
    definingClass = "Lcom/comcast/helio/hacks/multiperiodads/MultiPeriodAdsMediaSource\$ComponentListener;",
    name = "a",
    parameters = listOf("Landroidx/media3/common/AdPlaybackState;"),
    returnType = "V",
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.FINAL)
)
