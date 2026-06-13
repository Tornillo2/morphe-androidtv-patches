package ajstrick81.morphe.extension.peacock.ads;

import android.util.Log;
import android.webkit.ClientCertRequest;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.ByteArrayInputStream;

/**
 * Layer 7 — WebView shouldInterceptRequest injection.
 *
 * wrapClient() wraps the existing xtvClient and adds shouldInterceptRequest()
 * to block confirmed ad/analytics hostnames via PCAP/GREASE fingerprinting.
 *
 * Critical: onReceivedClientCertRequest MUST be delegated. Peacock uses mutual
 * TLS with a client certificate (com.peacock.peacocktv.tamper.Loader) to
 * authenticate the app to its servers. Dropping this callback silently kills
 * the TLS handshake and prevents the page from loading entirely — this was
 * the root cause of the Puss in Boots error screen in all previous builds.
 */
public class PeacockWebViewHelper {

    private static final String TAG = "MORPHE-PCK-WV";

    private static final String[] SAFE_HOSTS = {
        "peacocktv.com",
        "nbcuni.com",
        "nbcuniversal.com",
    };

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
    };

    private static WebResourceResponse emptyResponse() {
        return new WebResourceResponse(
            "text/plain",
            "utf-8",
            new ByteArrayInputStream(new byte[0])
        );
    }

    public static WebViewClient wrapClient(final WebViewClient original) {
        Log.d(TAG, "PeacockWebViewHelper.wrapClient() — Layer 7 active");
        return new WebViewClient() {

            // ── Interception ──────────────────────────────────────────────
            @Override
            public WebResourceResponse shouldInterceptRequest(
                    WebView view, WebResourceRequest request) {
                try {
                    String url = request.getUrl().toString();

                    for (String safe : SAFE_HOSTS) {
                        if (url.contains(safe)) return null;
                    }

                    for (String ad : AD_HOSTS) {
                        if (url.contains(ad)) {
                            Log.d(TAG, "BLOCKED: " + url);
                            return emptyResponse();
                        }
                    }

                    return null;

                } catch (Exception e) {
                    Log.e(TAG, "shouldInterceptRequest error: " + e.getMessage());
                    return null;
                }
            }

            // ── Critical: mutual TLS client certificate ───────────────────
            // Peacock authenticates the app to its servers via a client cert
            // stored in com.peacock.peacocktv.tamper.Loader. This MUST be
            // delegated or the TLS handshake fails and the page never loads.
            @Override
            public void onReceivedClientCertRequest(WebView view,
                    ClientCertRequest certRequest) {
                original.onReceivedClientCertRequest(view, certRequest);
            }

            // ── Delegate all other xtvClient overrides ────────────────────
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
