package ajstrick81.morphe.patches.primevideo.gmb

import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.patch.bytecodePatch
import ajstrick81.morphe.patches.primevideo.shared.Constants

// ─────────────────────────────────────────────────────────────────────────────
// DIAGNOSTIC PATCH — temporary, not for production use
//
// This patch intercepts GMBMessageProcessor.processMessage() and logs every
// GMB event type string to Android logcat via android.util.Log.d() under
// the tag "GMB_DIAGNOSTIC".
//
// Purpose: discover the exact event type strings that flow through the GMB
// message bus during ad breaks, pre-rolls, and cart overlay delivery.
// These strings are needed to build the production suppression patch.
//
// Usage:
//   1. Commit this patch temporarily to the repo
//   2. Apply the patched APK to the Onn device
//   3. Run: adb logcat --pid=<PID> -v time | findstr "GMB_DIAGNOSTIC"
//   4. Play content and observe all GMB event types during ad breaks
//   5. Note all ad/billing related event type strings
//   6. Remove this patch and build the production suppression patch
//
// Named gmbDiagnosticPatch to avoid any top-level val naming conflicts.
// ─────────────────────────────────────────────────────────────────────────────

@Suppress("unused")
val gmbDiagnosticPatch = bytecodePatch(
    name = "GMB Diagnostic",
    description = "Logs all GMB message event types to logcat for ad event discovery. Temporary diagnostic patch — not for production use.",
) {
    compatibleWith(Constants.COMPATIBILITY)

    execute {

        // ─────────────────────────────────────────────────────────────────────
        // Hook — GMBMessageProcessor.processMessage(String eventType, String payload)
        //
        // Register layout at entry:
        //   p0 = this (GMBMessageProcessor)
        //   p1 = eventType (String) ← log this
        //   p2 = payload (String)   ← log this too for context
        //
        // We call our extension logGMBMessage(eventType, payload) at index 0
        // before the original method runs. The original method continues
        // normally after our log call — no messages are suppressed.
        //
        // The extension uses android.util.Log.d() which surfaces in ADB
        // logcat unlike Amazon's internal com.amazon.reporting.Log.d().
        //
        // Filter in logcat with:
        //   adb logcat --pid=<PID> -v time | findstr "GMB_DIAGNOSTIC"
        // ─────────────────────────────────────────────────────────────────────
        GMBProcessMessageFingerprint.method.addInstructions(
            0,
            """
                invoke-static { p1, p2 }, Lajstrick81/morphe/extension/primevideo/gmb/GMBDiagnostic;->logGMBMessage(Ljava/lang/String;Ljava/lang/String;)V
            """
        )
    }
}
