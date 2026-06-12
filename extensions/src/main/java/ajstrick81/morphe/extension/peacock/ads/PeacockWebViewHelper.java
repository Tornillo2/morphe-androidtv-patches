package ajstrick81.morphe.extension.peacock.ads;

import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.ByteArrayInputStream;

/**
 * Layer 7 — WebView shouldInterceptRequest injection.
 *
 * wrapClient() takes the existing xtvClient (XTVWebView$xtvClient$1, which extends
 * WebViewClient) and returns a new WebViewClient wrapper that:
 *  - Delegates ALL existing overrides to the original client (onPageStarted,
 *    onPageFinished, onLoadResource, onReceivedError, onReceivedHttpError,
 *    onReceivedSslError, onRenderProcessGone)
 *  - Adds shouldInterceptRequest() to block ad CDN and FreeWheel hostnames,
 *    returning an empty 200 response so Chromium doesn't surface an error
 *    to the JS layer.
 *
 * Called from the smali injection in XTVWebView.<init>(Context) immediately
 * before setWebViewClient() so the wrapper replaces xtvClient transparently.
 */
public class PeacockWebViewHelper {

    private static final String TAG = "MORPHE-PCK-WV";

    // Ad segment CDNs confirmed via PCAP/GREASE fingerprinting (Chromium traffic)
    private static final String[] AD_HOSTS = {
        "prd-fy.cdn.peacocktv.com",
        "prd-ak.cdn.peacocktv.com",
        "prd-cf.cdn.peacocktv.com",
        "fwmrm.net",
        "scorecardresearch.com",
        "imrworldwide.com",
        "omtrdc.net",
        "doubleverify.com",
        "adsafeprotected.com",
        "innovid.com",
        "agkn.com",
        "doubleclick.net",
        "rlcdn.com",
        "nbcuas.com",
    };

    /**
     * Returns an empty 200 WebResourceResponse — Chromium treats this as a
     * successful load of zero bytes. The JS ad player gets an empty response
     * and moves on rather than triggering an error recovery path.
     */
    private static WebResourceResponse emptyResponse() {
        return new WebResourceResponse(
            "text/plain",
            "utf-8",
            new ByteArrayInputStream(new byte[0])
        );
    }

    private static boolean isAdHost(String url) {
        if (url == null) return false;
        for (String host : AD_HOSTS) {
            if (url.contains(host)) return true;
        }
        return false;
    }

    /**
     * Wraps the existing xtvClient WebViewClient with our interceptor.
     * The original client is retained as a delegate for all existing callbacks.
     */
    public static WebViewClient wrapClient(final WebViewClient original) {
        Log.d(TAG, "PeacockWebViewHelper.wrapClient() — Layer 7 active");
        return new WebViewClient() {

            // ── Interception ─────────────────────────────────────────────
            @Override
            public WebResourceResponse shouldInterceptRequest(
                    WebView view, WebResourceRequest request) {
                String url = request.getUrl().toString();
                if (isAdHost(url)) {
                    Log.d(TAG, "BLOCKED: " + url);
                    return emptyResponse();
                }
                return original.shouldInterceptRequest(view, request);
            }

            // ── Delegate all existing xtvClient overrides ─────────────────
            @Override
            public void onPageStarted(WebView view, String url,
                    android.graphics.Bitmap favicon) {
                original.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                original.onPageFinished(view, url);
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                original.onLoadResource(view, url);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode,
                    String description, String failingUrl) {
                original.onReceivedError(view, errorCode, description, failingUrl);
            }

            @Override
            public void onReceivedHttpError(WebView view,
                    WebResourceRequest request,
                    WebResourceResponse errorResponse) {
                original.onReceivedHttpError(view, request, errorResponse);
            }

            @Override
            public void onReceivedSslError(WebView view,
                    android.webkit.SslErrorHandler handler,
                    android.net.http.SslError error) {
                original.onReceivedSslError(view, handler, error);
            }

            @Override
            public boolean onRenderProcessGone(WebView view,
                    android.webkit.RenderProcessGoneDetail detail) {
                return original.onRenderProcessGone(view, detail);
            }
        };
    }
}
