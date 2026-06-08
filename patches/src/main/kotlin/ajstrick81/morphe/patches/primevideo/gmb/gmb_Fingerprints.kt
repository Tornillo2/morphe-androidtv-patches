package ajstrick81.morphe.patches.primevideo.gmb

import app.morphe.patcher.Fingerprint
import com.android.tools.smali.dexlib2.AccessFlags

// ─────────────────────────────────────────────────────────────────────────────
// Diagnostic target — GMBMessageProcessor.processMessage()
// smali/com/amazon/ignitionshared/GMBMessageProcessor.smali
//
// processMessage(String eventType, String payload) is called from native code
// (@CalledFromNative annotation) every time the Ignite WASM runtime sends a
// message to the Java layer via the Generic Message Bus.
//
// The method signature:
//   public processMessage(String eventType, String payload) → int
//   p0 = this
//   p1 = eventType  ← the GMB event type string we need to discover
//   p2 = payload    ← JSON payload for that event
//
// Amazon's internal logger (com.amazon.reporting.Log.d) already logs
// "Message received on GMB with event type : " + eventType but uses
// an internal logging pipeline that does not surface in Android logcat.
//
// This diagnostic patch adds a standard android.util.Log.d() call so
// every GMB event type string appears in ADB logcat under the tag
// "GMB_DIAGNOSTIC" — giving us visibility into the exact event type
// strings that flow during ad breaks, cart overlays, and pre-rolls.
//
// Once we have the event type strings we can build a targeted suppression
// patch that filters specific ad/billing events while allowing all other
// GMB messages to pass through normally.
//
// This fingerprint is for DIAGNOSTIC purposes only — the patch does NOT
// suppress any messages. It is intended to be committed temporarily,
// tested, and then replaced with the production suppression patch once
// the event type strings are known.
// ─────────────────────────────────────────────────────────────────────────────
object GMBProcessMessageFingerprint : Fingerprint(
    definingClass = "Lcom/amazon/ignitionshared/GMBMessageProcessor;",
    name = "processMessage",
    parameters = listOf(
        "Ljava/lang/String;",
        "Ljava/lang/String;"
    ),
    returnType = "I",
    accessFlags = listOf(AccessFlags.PUBLIC)
)
