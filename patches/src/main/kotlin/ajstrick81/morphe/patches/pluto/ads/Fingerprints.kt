package ajstrick81.morphe.patches.pluto.ads

import app.morphe.patcher.Fingerprint
import com.android.tools.smali.dexlib2.AccessFlags

// Hook 1 — BeaconTracker.fire(String adEventName, List urls)
//
// Single choke point for ALL server-side-ad-stitching (SSAI) tracking
// beacons. Pluto bakes ads into the HLS stream server-side and signals ad
// boundaries with in-stream ID3 tags; every impression/quartile/complete
// beacon for those stitched ads funnels through this one fire-and-forget
// method (it builds an RxJava Completable and subscribes, returning void —
// no caller reads a result). Neutering it silences all ad analytics with
// zero risk to the playback pipeline.
object BeaconTrackerFireFingerprint : Fingerprint(
    definingClass = "Ltv/pluto/library/adsbeaconstracking/BeaconTracker;",
    name = "fire",
    parameters = listOf(
        "Ljava/lang/String;",
        "Ljava/util/List;"
    ),
    returnType = "V",
    strings = listOf("adEventName", "urls"),
    accessFlags = listOf(AccessFlags.PUBLIC)
)

// Hook 2 — PauseAdsImageBinder.showPauseAdImageAfterInactivity(Bitmap, List)
//
// Display entry point for pause ads (the full-screen ad image shown when
// playback is paused). The method both renders the ad bitmap and fires its
// tracking beacons; returning void up front suppresses the graphic and the
// beacons in one cut. Pure UI side effect — safe to no-op.
object PauseAdsShowFingerprint : Fingerprint(
    definingClass = "Ltv/pluto/feature/leanbackpauseads/binder/PauseAdsImageBinder;",
    name = "showPauseAdImageAfterInactivity",
    parameters = listOf(
        "Landroid/graphics/Bitmap;",
        "Ljava/util/List;"
    ),
    returnType = "V",
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.FINAL)
)

// ---------------------------------------------------------------------------
// Tier 2 candidate — VOD auto-skip (NOT wired yet; needs on-device testing)
//
// Pluto keeps a full client-side ad-break timeline
// (tv/pluto/library/playercommon/data/AdBreak + AdBreakLocation, and
// tv/pluto/library/playerui/timebar/rx/AdBreakPositionSource exposing
// adBreakPositions). The Avia player exposes seekTo(J)Z
// (tv/pluto/library/player/impl/avia/...). Hooking the ad-break-start
// observation in AdBreakUseCase / the player mediator to seek to break-end
// would remove the stitched ad *picture* for VOD. It cannot work for live
// TV (ads occupy real broadcast wall-clock time). Left unhooked until it
// can be validated on a device to avoid the suspend/seek regressions the
// Tubi notes document.
// ---------------------------------------------------------------------------
