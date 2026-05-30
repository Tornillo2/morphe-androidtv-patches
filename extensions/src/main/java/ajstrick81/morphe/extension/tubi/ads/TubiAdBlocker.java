package ajstrick81.morphe.extension.tubi.ads;

import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import java.io.ByteArrayInputStream;

/**
 * TubiAdBlocker — WebViewClient.shouldInterceptRequest() extension
 *
 * Called from the patched xo/C$c.shouldInterceptRequest() before the
 * existing yo/b (LocalAssetsLoader) logic runs. Checks the request URL
 * against a list of known ad domains and returns an empty WebResourceResponse
 * for matches, silently blocking the request from the SPA's perspective.
 *
 * This is the bytecode equivalent of the AGP DNS block for dai.google.com
 * that was confirmed to eliminate the pre-roll in testing. It works at the
 * WebViewClient layer, which means it intercepts requests from the Chrome
 * renderer process before they reach the network — no AGH/AGP required.
 *
 * Architecture note: Tubi Android TV is a hybrid app. The SPA at
 * https://ott-androidtv.tubitv.com runs inside TubiWebView and drives the
 * entire pre-roll ad lifecycle in JavaScript. All IMA/FoxPlayer hooks
 * (Hooks 1–6) target the Java native layer and have no effect on JS-driven
 * ads. This hook is the correct interception point for the WebView layer.
 *
 * The same WebView-SPA architecture is used in Fox Sports and Fox One.
 * A corresponding hook in those apps' WebViewClient subclasses should
 * eliminate their surviving pre-rolls using the same blocked domain list.
 */
public class TubiAdBlocker {

    // ─────────────────────────────────────────────────────────────────────────
    // Ad domain blocklist
    //
    // Confirmed via AGP DNS block test: blocking dai.google.com eliminates
    // the pre-roll entirely by preventing the SPA's DAI stream request.
    // The remaining domains cover the full IMA/DAI delivery stack.
    //
    // imasdk.googleapis.com  — IMA SDK JavaScript loader; the SPA bootstraps
    //                          the entire IMA ad engine by loading this script.
    //                          Blocking it prevents ad requests from being made.
    // dai.google.com         — Google DAI stream request target (confirmed).
    // googletagmanager.com   — Ad tag container; routes targeting parameters
    //                          to ad networks.
    // doubleclick.net        — DoubleClick/DFP ad delivery network.
    // googlesyndication.com  — Google AdSense/ad syndication network.
    // ─────────────────────────────────────────────────────────────────────────
    private static final String[] BLOCKED_HOSTS = {
        "dai.google.com",
        "imasdk.googleapis.com",
        "googletagmanager.com",
        "doubleclick.net",
        "googlesyndication.com"
    };

    /**
     * Called from patched shouldInterceptRequest() at index 0.
     *
     * @param request  the WebResourceRequest Android passes to shouldInterceptRequest
     * @return         an empty WebResourceResponse if the URL matches a blocked domain,
     *                 or null to fall through to the existing LocalAssetsLoader logic
     */
    public static WebResourceResponse shouldBlock(WebResourceRequest request) {
        if (request == null || request.getUrl() == null) {
            return null;
        }

        String host = request.getUrl().getHost();
        if (host == null) {
            return null;
        }

        for (String blocked : BLOCKED_HOSTS) {
            if (host.equals(blocked) || host.endsWith("." + blocked)) {
                // Return empty 200 response — the SPA's fetch/XHR resolves
                // with an empty body rather than erroring out, which keeps
                // the SPA stable while silently dropping the ad request.
                return new WebResourceResponse(
                    "text/plain",
                    "utf-8",
                    new ByteArrayInputStream(new byte[0])
                );
            }
        }

        return null;
    }
}
