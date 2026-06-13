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
 * wrapClient() takes the existing xtvClient (XTVWebView$xtvClient$1) and returns
 * a wrapper WebViewClient that delegates all existing callbacks and adds
 * shouldInterceptRequest() to block ad CDN and FreeWheel hostnames confirmed
 * via PCAP/GREASE fingerprinting.
 *
 * Safe-list: peacocktv.com and nbcuni.com subdomains are always allowed through,
 * ensuring the initial page load and all content delivery is never blocked.
 * Only explicit ad/analytics hostnames are intercepted.
 */
public class PeacockWebViewHelper {

    private static final String TAG = "MORPHE-PCK-WV";

    // Peacock-owned domains — never block anything under these
    private static final String[] SAFE_HOSTS = {
        "peacocktv.com",
        "nbcuni.com",
        "nbcuniversal.com",
        "clients.peacocktv.com",
    };

    // Ad/analytics hosts confirmed via PCAP GREASE fingerprinting as Chromium traffic
    private static final String[] AD_HOSTS = {
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
        "prd-fy.cdn.peacocktv.com",
        "prd-ak.cdn.peacocktv.com",
        "prd-cf.cdn.peacocktv.com",
    };

    private static WebResourceResponse emptyResponse() {
        return new WebResourceResponse(
            "text/plain",
            "utf-8",
            new ByteArrayInputStream(new byte[0])
        );
    }

    private static boolean isSafeHost(String url) {
        for (String host : SAFE_HOSTS) {
            if (url.contains(host)) return true;
        }
        return false;
    }

    private static boolean isAdHost(String url) {
        for (String host : AD_HOSTS) {
            if (url.contains(host)) return true;
        }
        return false;
    }

    public static WebViewClient wrapClient(final WebViewClient original) {
        Log.d(TAG, "PeacockWebViewHelper.wrapClient() — Layer 7 active");
        return new WebViewClient() {

            // ── Interception ─────────────────────────────────────────────
            @Override
            public WebResourceResponse shouldInterceptRequest(
                    WebView view, WebResourceRequest request) {
                String url = request.getUrl().toString();

                // Safe-list check first — Peacock's own infrastructure is never blocked
                if (isSafeHost(url)) {
                    return original.shouldInterceptRequest(view, request);
                }

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
