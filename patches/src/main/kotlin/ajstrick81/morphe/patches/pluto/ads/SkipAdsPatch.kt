package ajstrick81.morphe.patches.pluto.ads

import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.patch.bytecodePatch
import ajstrick81.morphe.patches.pluto.shared.Constants

@Suppress("unused")
val skipAdsPatch = bytecodePatch(
    name = "Skip ads",
    description = "Suppresses Pluto TV's ad experience — tracking beacons, pause ads, " +
        "and clickable-ad overlays. Pluto stitches ad video into the stream server-side " +
        "(SSAI), so in-stream ads (especially live TV) are not removable at the bytecode " +
        "layer; pair with DNS filtering for third-party trackers. Validated 5.66.0-leanback.",
) {
    compatibleWith(Constants.COMPATIBILITY)

    execute {

        // Hook 1 — BeaconTracker.fire(String, List)
        // Silences every stitched-ad tracking beacon (impressions, quartiles,
        // completes). Fire-and-forget void method — no playback dependency.
        BeaconTrackerFireFingerprint.method.addInstructions(0, "return-void")

        // Hook 2 — PauseAdsImageBinder.showPauseAdImageAfterInactivity(Bitmap, List)
        // Suppresses the pause-ad overlay and its beacons.
        PauseAdsShowFingerprint.method.addInstructions(0, "return-void")

        // Hook 3 — ClickableAdsBinder$Companion.bind(...): Disposable
        // Prevents clickable ("interactive") ad overlays from ever being wired
        // by returning an already-disposed Disposable at offset 0. The caller
        // adds it to a CompositeDisposable as usual — a disposed Disposable is
        // a harmless no-op, so the contract is honored and nothing hangs.
        //
        // Verifier note: at offset 0 no registers are live, so writing a fresh
        // Disposable into v0 and returning it is always accepted (same pattern
        // as the Peacock getOkHttpClient replacement). Disposables.disposed()
        // is confirmed present in the app's bundled RxJava.
        ClickableAdsBindFingerprint.method.addInstructions(
            0,
            """
                invoke-static {}, Lio/reactivex/disposables/Disposables;->disposed()Lio/reactivex/disposables/Disposable;
                move-result-object v0
                return-object v0
            """,
        )
    }
}
