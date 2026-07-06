package com.android.volley.toolbox;

import android.os.SystemClock;
import com.android.volley.Header;
import com.android.volley.Network;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import j$.util.DesugarCollections;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class BasicNetwork implements Network {
    public static final int DEFAULT_POOL_SIZE = 4096;
    public final BaseHttpStack mBaseHttpStack;

    @Deprecated
    public final HttpStack mHttpStack;
    public final ByteArrayPool mPool;

    @Deprecated
    public BasicNetwork(HttpStack httpStack) {
        this(httpStack, new ByteArrayPool(4096));
    }

    @Deprecated
    public static Map<String, String> convertHeaders(Header[] headerArr) {
        TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        for (Header header : headerArr) {
            treeMap.put(header.mName, header.mValue);
        }
        return treeMap;
    }

    @Override // com.android.volley.Network
    public NetworkResponse performRequest(Request<?> request) throws VolleyError {
        IOException iOException;
        HttpResponse httpResponse;
        byte[] bArr;
        Request<?> request2;
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        while (true) {
            try {
                HttpResponse httpResponseExecuteRequest = this.mBaseHttpStack.executeRequest(request, HttpHeaderParser.getCacheHeaders(request.getCacheEntry()));
                try {
                    int i = httpResponseExecuteRequest.mStatusCode;
                    List listUnmodifiableList = DesugarCollections.unmodifiableList(httpResponseExecuteRequest.mHeaders);
                    if (i == 304) {
                        return NetworkUtility.getNotModifiedNetworkResponse(request, SystemClock.elapsedRealtime() - jElapsedRealtime, listUnmodifiableList);
                    }
                    InputStream content = httpResponseExecuteRequest.getContent();
                    byte[] bArrInputStreamToBytes = content != null ? NetworkUtility.inputStreamToBytes(content, httpResponseExecuteRequest.mContentLength, this.mPool) : new byte[0];
                    try {
                        NetworkUtility.logSlowRequests(SystemClock.elapsedRealtime() - jElapsedRealtime, request, bArrInputStreamToBytes, i);
                        if (i < 200 || i > 299) {
                            throw new IOException();
                        }
                        return new NetworkResponse(i, bArrInputStreamToBytes, false, SystemClock.elapsedRealtime() - jElapsedRealtime, (List<Header>) listUnmodifiableList);
                    } catch (IOException e) {
                        e = e;
                        request2 = request;
                        httpResponse = httpResponseExecuteRequest;
                        bArr = bArrInputStreamToBytes;
                        iOException = e;
                        NetworkUtility.attemptRetryOnException(request2, NetworkUtility.shouldRetryException(request2, iOException, jElapsedRealtime, httpResponse, bArr));
                        request = request2;
                    }
                } catch (IOException e2) {
                    e = e2;
                    bArr = null;
                    httpResponse = httpResponseExecuteRequest;
                    request2 = request;
                }
            } catch (IOException e3) {
                iOException = e3;
                httpResponse = null;
                bArr = null;
                request2 = request;
            }
            NetworkUtility.attemptRetryOnException(request2, NetworkUtility.shouldRetryException(request2, iOException, jElapsedRealtime, httpResponse, bArr));
            request = request2;
        }
    }

    @Deprecated
    public BasicNetwork(HttpStack httpStack, ByteArrayPool byteArrayPool) {
        this.mHttpStack = httpStack;
        this.mBaseHttpStack = new AdaptedHttpStack(httpStack);
        this.mPool = byteArrayPool;
    }

    public BasicNetwork(BaseHttpStack baseHttpStack) {
        this(baseHttpStack, new ByteArrayPool(4096));
    }

    public BasicNetwork(BaseHttpStack baseHttpStack, ByteArrayPool byteArrayPool) {
        this.mBaseHttpStack = baseHttpStack;
        this.mHttpStack = baseHttpStack;
        this.mPool = byteArrayPool;
    }
}
