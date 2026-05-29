package ajstrick81.morphe.patches.peacock.ads

import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.patch.bytecodePatch
import ajstrick81.morphe.patches.peacock.shared.Constants

// Note: val is named peacockSkipAdsPatch to avoid a top-level name conflict
// with ajstrick81.morphe.patches.tubi.ads.skipAdsPatch. Kotlin does not allow
// two top-level vals with the same name within the same compiled module even
// when they are in different packages.

@Suppress("unused")
val peacockSkipAdsPatch = bytecodePatch(
    name = "Skip ads",
    description = "Blocks Helio CSAI ad delivery via two hooks: a coroutine sentinel that prevents the ad schedule coroutine from constructing HelioAdPlaybackState, and a return-void on ComponentListener.a() that silences any AdPlaybackState updates that reach the media3 ExoPlayer timeline.",
) {
    compatibleWith(Constants.COMPATIBILITY)

    execute {

        // ─────────────────────────────────────────────────────────────────────
        // Hook 1 — HelioAdsLoader$1$1$1.invokeSuspend(Object)
        //
        // The coroutine state machine that drives the entire ad schedule
        // lifecycle. Returning COROUTINE_SUSPENDED at index 0 permanently
        // suspends the coroutine — it never resumes, so HelioAdPlaybackState
        // is never constructed and HelioAdsLoader.f stays null.
        //
        // Because all three downstream dispatch paths guard with:
        //   if-eqz HelioAdsLoader.f, :skip
        // a null f means no AdPlaybackState is ever dispatched to
        // ComponentListener, and the ExoPlayer timeline never learns about
        // any ad periods.
        //
        // Coroutine sentinel: return CoroutineSingletons.c (= COROUTINE_SUSPENDED).
        // Field name 'c' confirmed via APK autopsy on v7.5.102.
        //
        // We write into p1 (the incoming result Object) rather than a v-register
        // to avoid any dependency on the method's local register count.
        // ─────────────────────────────────────────────────────────────────────
        HelioAdScheduleCoroutineFingerprint.method.addInstructions(
            0,
            """
                sget-object p1, Lkotlin/coroutines/intrinsics/CoroutineSingletons;->c:Lkotlin/coroutines/intrinsics/CoroutineSingletons;
                return-object p1
            """
        )

        // ─────────────────────────────────────────────────────────────────────
        // Hook 2 — MultiPeriodAdsMediaSource$ComponentListener.a(AdPlaybackState)
        //
        // Belt-and-suspenders backstop for any AdPlaybackState that reaches
        // the EventListener interface despite Hook 1. This covers:
        //   - HelioAdsLoader$1$1$1$1.invoke() — the per-ad-event update lambda
        //     that constructs a new AdPlaybackState directly (bypasses the
        //     coroutine and therefore Hook 1) and dispatches via h.a()
        //   - Any future Helio update that adds a new dispatch path
        //
        // Returning void here prevents Handler.post(Runnable_a) from firing,
        // so MultiPeriodAdsMediaSource.w is never updated and ExoPlayer's
        // timeline refresh methods j0()/k0() are never called.
        //
        // definingClass and parameter type are verified against v7.5.102 smali:
        //   Lcom/comcast/helio/hacks/multiperiodads/MultiPeriodAdsMediaSource$ComponentListener;
        //   Landroidx/media3/common/AdPlaybackState;
        //
        // Note: prior builds used wrong paths (cvs/android/helio/ads +
        // exoplayer2 parameter). Those are corrected here.
        //
        // Note: returnEarly() from app.morphe.util is not available in Morphe
        // 1.3.0. We insert return-void directly via addInstructions at index 0.
        // ─────────────────────────────────────────────────────────────────────
        HelioAdPlaybackStateFingerprint.method.addInstructions(
            0,
            """
                return-void
            """
        )
    }
}
