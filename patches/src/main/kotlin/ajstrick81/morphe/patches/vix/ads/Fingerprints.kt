package ajstrick81.morphe.patches.vix.ads

import app.morphe.patcher.Fingerprint
import com.android.tools.smali.dexlib2.AccessFlags

// ─────────────────────────────────────────────────────────────────────────────
// Fingerprints for ViX Android TV (com.univision.prendetv) v4.46.0_tv
//
// Ad stack: LuraPlayer SDK (com.akta.luraplayer) + Innovid SSAI overlay.
// Class/method names confirmed via baksmali disassembly of the shipped dex
// set (classes.dex … classes11.dex).
//
// NOTE on the former LuraFreewheelConfiguration constructor hook (removed):
//   classes5.dex analysis showed LuraFreewheelConfiguration has THREE public
//   constructors (the kotlinx.serialization synthetic <init>(IZ…x2), the
//   primary <init>(Z…), and the default-args synthetic), so an unparameterised
//   [PUBLIC, CONSTRUCTOR] fingerprint bound ambiguously. More importantly the
//   hook was a no-op for ad suppression: the `enabled` field (a:Z) is read only
//   inside the class, and getEnabled() is invoked solely by a config merge()
//   helper (LuraFreewheelConfigurationKt) — nothing in the ad scheduler gates
//   delivery on it. The app also constructs its client-side ad config with
//   FreeWheel = null and provider = "generic" (videoplayer/player/g.smali).
//   Injecting return-void at index 0 of the constructor additionally skipped
//   the mandatory super.<init>() call, risking a VerifyError at class load.
//   The hook was therefore removed rather than retargeted.
// ─────────────────────────────────────────────────────────────────────────────

// ─────────────────────────────────────────────────────────────────────────────
// InnovidHelper — ad overlay mount entry point
// classes10.dex / com/univision/descarga/videoplayer/utilities/innovid/
//
// Method `h(boolean, WebView, VideoModel, Flow)` is the call that mounts the
// Innovid SSAI ad WebView overlay. Confirmed via the "innovidAd" parameter
// string in the body and its sole call site in videoplayer/z.smali, which
// dispatches it from the player's ad-event switch. Returning void at index 0
// stops the overlay before any WebView is attached or network request fires.
//
// The method name (`h`) is R8-obfuscated and intentionally NOT matched on —
// InnovidHelper exposes four void methods that share [PUBLIC, FINAL]
// (the synthetic accessors a/b, the teardown c(DisposeReason), and h), so the
// previous name-less, parameter-less fingerprint could bind the wrong one
// (e.g. teardown). The full parameter signature uniquely identifies the mount
// method and survives method renaming across minor builds.
// ─────────────────────────────────────────────────────────────────────────────
object InnovidStartAdFingerprint : Fingerprint(
    definingClass = "Lcom/univision/descarga/videoplayer/utilities/innovid/InnovidHelper;",
    parameters = listOf(
        "Z",
        "Landroid/webkit/WebView;",
        "Lcom/univision/descarga/presentation/models/video/v;",
        "Lkotlinx/coroutines/flow/i;"
    ),
    returnType = "V",
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.FINAL)
)
