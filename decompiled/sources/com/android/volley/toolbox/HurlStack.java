package com.android.volley.toolbox;

import androidx.annotation.VisibleForTesting;
import androidx.core.app.RemoteInput$$ExternalSyntheticOutline0;
import com.amazon.minerva.client.thirdparty.transport.MetricsTransporter;
import com.android.volley.AuthFailureError;
import com.android.volley.Header;
import com.android.volley.Request;
import com.android.volley.toolbox.HttpClientStack;
import java.io.DataOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class HurlStack extends BaseHttpStack {
    public static final int HTTP_CONTINUE = 100;
    public final SSLSocketFactory mSslSocketFactory;
    public final UrlRewriter mUrlRewriter;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class UrlConnectionInputStream extends FilterInputStream {
        public final HttpURLConnection mConnection;

        public UrlConnectionInputStream(HttpURLConnection httpURLConnection) {
            super(HurlStack.inputStreamFromConnection(httpURLConnection));
            this.mConnection = httpURLConnection;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            super.close();
            this.mConnection.disconnect();
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface UrlRewriter extends com.android.volley.toolbox.UrlRewriter {
    }

    public HurlStack(UrlRewriter urlRewriter, SSLSocketFactory sSLSocketFactory) {
        this.mUrlRewriter = urlRewriter;
        this.mSslSocketFactory = sSLSocketFactory;
    }

    @VisibleForTesting
    public static List<Header> convertHeaders(Map<String, List<String>> map) {
        ArrayList arrayList = new ArrayList(map.size());
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            if (entry.getKey() != null) {
                Iterator<String> it = entry.getValue().iterator();
                while (it.hasNext()) {
                    arrayList.add(new Header(entry.getKey(), it.next()));
                }
            }
        }
        return arrayList;
    }

    public static boolean hasResponseBody(int i, int i2) {
        if (i != 4) {
            return ((100 <= i2 && i2 < 200) || i2 == 204 || i2 == 304) ? false : true;
        }
        return false;
    }

    public static InputStream inputStreamFromConnection(HttpURLConnection httpURLConnection) {
        try {
            return httpURLConnection.getInputStream();
        } catch (IOException unused) {
            return httpURLConnection.getErrorStream();
        }
    }

    public final void addBody(HttpURLConnection httpURLConnection, Request<?> request, byte[] bArr) throws IOException {
        httpURLConnection.setDoOutput(true);
        if (!httpURLConnection.getRequestProperties().containsKey("Content-Type")) {
            httpURLConnection.setRequestProperty("Content-Type", request.getBodyContentType());
        }
        DataOutputStream dataOutputStream = new DataOutputStream(createOutputStream(request, httpURLConnection, bArr.length));
        dataOutputStream.write(bArr);
        dataOutputStream.close();
    }

    public final void addBodyIfExists(HttpURLConnection httpURLConnection, Request<?> request) throws IOException, AuthFailureError {
        byte[] body = request.getBody();
        if (body != null) {
            addBody(httpURLConnection, request, body);
        }
    }

    public HttpURLConnection createConnection(URL url) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setInstanceFollowRedirects(HttpURLConnection.getFollowRedirects());
        return httpURLConnection;
    }

    public InputStream createInputStream(Request<?> request, HttpURLConnection httpURLConnection) {
        return new UrlConnectionInputStream(httpURLConnection);
    }

    public OutputStream createOutputStream(Request<?> request, HttpURLConnection httpURLConnection, int i) throws IOException {
        return httpURLConnection.getOutputStream();
    }

    @Override // com.android.volley.toolbox.BaseHttpStack
    public HttpResponse executeRequest(Request<?> request, Map<String, String> map) throws IOException, AuthFailureError {
        String url = request.getUrl();
        HashMap map2 = new HashMap();
        map2.putAll(map);
        map2.putAll(request.getHeaders());
        UrlRewriter urlRewriter = this.mUrlRewriter;
        if (urlRewriter != null) {
            String strRewriteUrl = urlRewriter.rewriteUrl(url);
            if (strRewriteUrl == null) {
                throw new IOException(RemoteInput$$ExternalSyntheticOutline0.m("URL blocked by rewriter: ", url));
            }
            url = strRewriteUrl;
        }
        HttpURLConnection httpURLConnectionOpenConnection = openConnection(new URL(url), request);
        try {
            for (String str : map2.keySet()) {
                httpURLConnectionOpenConnection.setRequestProperty(str, (String) map2.get(str));
            }
            setConnectionParametersForRequest(httpURLConnectionOpenConnection, request);
            int responseCode = httpURLConnectionOpenConnection.getResponseCode();
            if (responseCode == -1) {
                throw new IOException("Could not retrieve response code from HttpUrlConnection.");
            }
            if (hasResponseBody(request.getMethod(), responseCode)) {
                return new HttpResponse(responseCode, convertHeaders(httpURLConnectionOpenConnection.getHeaderFields()), httpURLConnectionOpenConnection.getContentLength(), createInputStream(request, httpURLConnectionOpenConnection));
            }
            HttpResponse httpResponse = new HttpResponse(responseCode, convertHeaders(httpURLConnectionOpenConnection.getHeaderFields()));
            httpURLConnectionOpenConnection.disconnect();
            return httpResponse;
        } catch (Throwable th) {
            if (0 == 0) {
                httpURLConnectionOpenConnection.disconnect();
            }
            throw th;
        }
    }

    public final HttpURLConnection openConnection(URL url, Request<?> request) throws IOException {
        SSLSocketFactory sSLSocketFactory;
        HttpURLConnection httpURLConnectionCreateConnection = createConnection(url);
        int timeoutMs = request.getTimeoutMs();
        httpURLConnectionCreateConnection.setConnectTimeout(timeoutMs);
        httpURLConnectionCreateConnection.setReadTimeout(timeoutMs);
        httpURLConnectionCreateConnection.setUseCaches(false);
        httpURLConnectionCreateConnection.setDoInput(true);
        if ("https".equals(url.getProtocol()) && (sSLSocketFactory = this.mSslSocketFactory) != null) {
            ((HttpsURLConnection) httpURLConnectionCreateConnection).setSSLSocketFactory(sSLSocketFactory);
        }
        return httpURLConnectionCreateConnection;
    }

    public void setConnectionParametersForRequest(HttpURLConnection httpURLConnection, Request<?> request) throws IOException, AuthFailureError {
        switch (request.getMethod()) {
            case -1:
                byte[] postBody = request.getPostBody();
                if (postBody != null) {
                    httpURLConnection.setRequestMethod(MetricsTransporter.REQUEST_METHOD);
                    addBody(httpURLConnection, request, postBody);
                    return;
                }
                return;
            case 0:
                httpURLConnection.setRequestMethod("GET");
                return;
            case 1:
                httpURLConnection.setRequestMethod(MetricsTransporter.REQUEST_METHOD);
                byte[] body = request.getBody();
                if (body != null) {
                    addBody(httpURLConnection, request, body);
                    return;
                }
                return;
            case 2:
                httpURLConnection.setRequestMethod("PUT");
                byte[] body2 = request.getBody();
                if (body2 != null) {
                    addBody(httpURLConnection, request, body2);
                    return;
                }
                return;
            case 3:
                httpURLConnection.setRequestMethod("DELETE");
                return;
            case 4:
                httpURLConnection.setRequestMethod("HEAD");
                return;
            case 5:
                httpURLConnection.setRequestMethod("OPTIONS");
                return;
            case 6:
                httpURLConnection.setRequestMethod("TRACE");
                return;
            case 7:
                httpURLConnection.setRequestMethod(HttpClientStack.HttpPatch.METHOD_NAME);
                byte[] body3 = request.getBody();
                if (body3 != null) {
                    addBody(httpURLConnection, request, body3);
                    return;
                }
                return;
            default:
                throw new IllegalStateException("Unknown method type.");
        }
    }

    public HurlStack() {
        this(null, null);
    }

    public HurlStack(UrlRewriter urlRewriter) {
        this(urlRewriter, null);
    }
}
