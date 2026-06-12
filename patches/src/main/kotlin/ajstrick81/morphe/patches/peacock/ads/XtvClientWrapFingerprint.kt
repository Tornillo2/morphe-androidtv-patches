package ajstrick81.morphe.patches.peacock.ads

import app.revanced.patcher.fingerprint.MethodFingerprint
import com.android.tools.smali.dexlib2.Opcode

/**
 * Fingerprints XTVWebView.<init>(Context) at the setWebViewClient() call site.
 *
 * Target bytecode (offset 252):
 *   const-wide/16 v1, 30000        ← rendererCrashWindowMs — unique anchor
 *   iput-wide     v1, v5, ...rendererCrashWindowMs J
 *   new-instance  v1, XTVWebView$xtvClient$1
 *   invoke-direct v1, v5, XTVWebView$xtvClient$1-><init>(XTVWebView)V
 *   iput-object   v1, v5, ...xtvClient
 *   ...
 *   [8 getSettings() calls]
 *   ...
 *   invoke-virtual v5, v1, WebView->setWebViewClient(WebViewClient)V  ← INJECT HERE
 *
 * We anchor on the const-wide/16 30000 + subsequent setWebViewClient pattern.
 */
object XtvClientWrapFingerprint : MethodFingerprint(
    returnType = "V",
    parameters = listOf("Landroid/content/Context;"),
    strings = listOf("WebViewClient.onLoadResource."),  // unique string in xtvClient$1, class is inner so same dex
    opcodes = listOf(
        Opcode.CONST_WIDE_16,          // const-wide/16 v1, 30000
        Opcode.IPUT_WIDE,              // iput-wide rendererCrashWindowMs
        Opcode.NEW_INSTANCE,           // new-instance xtvClient$1
        Opcode.INVOKE_DIRECT,          // invoke-direct xtvClient$1.<init>
        Opcode.IPUT_OBJECT,            // iput-object xtvClient field
    ),
    customFingerprint = { methodDef, _ ->
        methodDef.definingClass == "Lcom/peacock/peacocktv/web/XTVWebView;"
    }
)
