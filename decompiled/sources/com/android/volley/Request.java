package com.android.volley;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import androidx.annotation.CallSuper;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import androidx.core.app.RemoteInput$$ExternalSyntheticOutline0;
import com.android.volley.Cache;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class Request<T> implements Comparable<Request<T>> {
    public static final String DEFAULT_PARAMS_ENCODING = "UTF-8";

    @Nullable
    public Cache.Entry mCacheEntry;

    @GuardedBy("mLock")
    public boolean mCanceled;
    public final int mDefaultTrafficStatsTag;

    @Nullable
    @GuardedBy("mLock")
    public Response.ErrorListener mErrorListener;
    public final VolleyLog.MarkerLog mEventLog;
    public final Object mLock;
    public final int mMethod;

    @GuardedBy("mLock")
    public NetworkRequestCompleteListener mRequestCompleteListener;
    public RequestQueue mRequestQueue;

    @GuardedBy("mLock")
    public boolean mResponseDelivered;
    public RetryPolicy mRetryPolicy;
    public Integer mSequence;
    public boolean mShouldCache;
    public boolean mShouldRetryConnectionErrors;
    public boolean mShouldRetryServerErrors;
    public Object mTag;
    public final String mUrl;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Method {
        public static final int DELETE = 3;
        public static final int DEPRECATED_GET_OR_POST = -1;
        public static final int GET = 0;
        public static final int HEAD = 4;
        public static final int OPTIONS = 5;
        public static final int PATCH = 7;
        public static final int POST = 1;
        public static final int PUT = 2;
        public static final int TRACE = 6;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface NetworkRequestCompleteListener {
        void onNoUsableResponseReceived(Request<?> request);

        void onResponseReceived(Request<?> request, Response<?> response);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum Priority {
        LOW,
        NORMAL,
        HIGH,
        IMMEDIATE
    }

    @Deprecated
    public Request(String str, Response.ErrorListener errorListener) {
        this(-1, str, errorListener);
    }

    public static int findDefaultTrafficStatsTag(String str) {
        Uri uri;
        String host;
        if (TextUtils.isEmpty(str) || (uri = Uri.parse(str)) == null || (host = uri.getHost()) == null) {
            return 0;
        }
        return host.hashCode();
    }

    public void addMarker(String str) {
        if (VolleyLog.MarkerLog.ENABLED) {
            this.mEventLog.add(str, Thread.currentThread().getId());
        }
    }

    @CallSuper
    public void cancel() {
        synchronized (this.mLock) {
            this.mCanceled = true;
            this.mErrorListener = null;
        }
    }

    public void deliverError(VolleyError volleyError) {
        Response.ErrorListener errorListener;
        synchronized (this.mLock) {
            errorListener = this.mErrorListener;
        }
        if (errorListener != null) {
            errorListener.onErrorResponse(volleyError);
        }
    }

    public abstract void deliverResponse(T t);

    public final byte[] encodeParameters(Map<String, String> map, String str) {
        StringBuilder sb = new StringBuilder();
        try {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (entry.getKey() == null || entry.getValue() == null) {
                    throw new IllegalArgumentException(String.format("Request#getParams() or Request#getPostParams() returned a map containing a null key or value: (%s, %s). All keys and values must be non-null.", entry.getKey(), entry.getValue()));
                }
                sb.append(URLEncoder.encode(entry.getKey(), str));
                sb.append('=');
                sb.append(URLEncoder.encode(entry.getValue(), str));
                sb.append('&');
            }
            return sb.toString().getBytes(str);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(RemoteInput$$ExternalSyntheticOutline0.m("Encoding not supported: ", str), e);
        }
    }

    public void finish(final String str) {
        RequestQueue requestQueue = this.mRequestQueue;
        if (requestQueue != null) {
            requestQueue.finish(this);
        }
        if (VolleyLog.MarkerLog.ENABLED) {
            final long id = Thread.currentThread().getId();
            if (Looper.myLooper() != Looper.getMainLooper()) {
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.android.volley.Request.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Request.this.mEventLog.add(str, id);
                        Request request = Request.this;
                        request.mEventLog.finish(request.toString());
                    }
                });
            } else {
                this.mEventLog.add(str, id);
                this.mEventLog.finish(toString());
            }
        }
    }

    public byte[] getBody() throws AuthFailureError {
        return null;
    }

    public String getBodyContentType() {
        return "application/x-www-form-urlencoded; charset=UTF-8";
    }

    @Nullable
    public Cache.Entry getCacheEntry() {
        return this.mCacheEntry;
    }

    public String getCacheKey() {
        String url = getUrl();
        int method = getMethod();
        if (method == 0 || method == -1) {
            return url;
        }
        return Integer.toString(method) + '-' + url;
    }

    @Nullable
    public Response.ErrorListener getErrorListener() {
        Response.ErrorListener errorListener;
        synchronized (this.mLock) {
            errorListener = this.mErrorListener;
        }
        return errorListener;
    }

    public Map<String, String> getHeaders() throws AuthFailureError {
        return Collections.EMPTY_MAP;
    }

    public int getMethod() {
        return this.mMethod;
    }

    @Nullable
    public Map<String, String> getParams() throws AuthFailureError {
        return null;
    }

    public String getParamsEncoding() {
        return "UTF-8";
    }

    @Deprecated
    public byte[] getPostBody() throws AuthFailureError {
        Map<String, String> postParams = getPostParams();
        if (postParams == null || postParams.size() <= 0) {
            return null;
        }
        return encodeParameters(postParams, getPostParamsEncoding());
    }

    @Deprecated
    public String getPostBodyContentType() {
        return getBodyContentType();
    }

    @Nullable
    @Deprecated
    public Map<String, String> getPostParams() throws AuthFailureError {
        return null;
    }

    @Deprecated
    public String getPostParamsEncoding() {
        return "UTF-8";
    }

    public Priority getPriority() {
        return Priority.NORMAL;
    }

    public RetryPolicy getRetryPolicy() {
        return this.mRetryPolicy;
    }

    public final int getSequence() {
        Integer num = this.mSequence;
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalStateException("getSequence called before setSequence");
    }

    public Object getTag() {
        return this.mTag;
    }

    public final int getTimeoutMs() {
        return getRetryPolicy().getCurrentTimeout();
    }

    public int getTrafficStatsTag() {
        return this.mDefaultTrafficStatsTag;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public boolean hasHadResponseDelivered() {
        boolean z;
        synchronized (this.mLock) {
            z = this.mResponseDelivered;
        }
        return z;
    }

    public boolean isCanceled() {
        boolean z;
        synchronized (this.mLock) {
            z = this.mCanceled;
        }
        return z;
    }

    public void markDelivered() {
        synchronized (this.mLock) {
            this.mResponseDelivered = true;
        }
    }

    public void notifyListenerResponseNotUsable() {
        NetworkRequestCompleteListener networkRequestCompleteListener;
        synchronized (this.mLock) {
            networkRequestCompleteListener = this.mRequestCompleteListener;
        }
        if (networkRequestCompleteListener != null) {
            networkRequestCompleteListener.onNoUsableResponseReceived(this);
        }
    }

    public void notifyListenerResponseReceived(Response<?> response) {
        NetworkRequestCompleteListener networkRequestCompleteListener;
        synchronized (this.mLock) {
            networkRequestCompleteListener = this.mRequestCompleteListener;
        }
        if (networkRequestCompleteListener != null) {
            networkRequestCompleteListener.onResponseReceived(this, response);
        }
    }

    public abstract Response<T> parseNetworkResponse(NetworkResponse networkResponse);

    public void sendEvent(int i) {
        RequestQueue requestQueue = this.mRequestQueue;
        if (requestQueue != null) {
            requestQueue.sendRequestEvent(this, i);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Request<?> setCacheEntry(Cache.Entry entry) {
        this.mCacheEntry = entry;
        return this;
    }

    public void setNetworkRequestCompleteListener(NetworkRequestCompleteListener networkRequestCompleteListener) {
        synchronized (this.mLock) {
            this.mRequestCompleteListener = networkRequestCompleteListener;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Request<?> setRequestQueue(RequestQueue requestQueue) {
        this.mRequestQueue = requestQueue;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Request<?> setRetryPolicy(RetryPolicy retryPolicy) {
        this.mRetryPolicy = retryPolicy;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Request<?> setSequence(int i) {
        this.mSequence = Integer.valueOf(i);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Request<?> setShouldCache(boolean z) {
        this.mShouldCache = z;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Request<?> setShouldRetryConnectionErrors(boolean z) {
        this.mShouldRetryConnectionErrors = z;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Request<?> setShouldRetryServerErrors(boolean z) {
        this.mShouldRetryServerErrors = z;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Request<?> setTag(Object obj) {
        this.mTag = obj;
        return this;
    }

    public final boolean shouldCache() {
        return this.mShouldCache;
    }

    public final boolean shouldRetryConnectionErrors() {
        return this.mShouldRetryConnectionErrors;
    }

    public final boolean shouldRetryServerErrors() {
        return this.mShouldRetryServerErrors;
    }

    public String toString() {
        String str = "0x" + Integer.toHexString(getTrafficStatsTag());
        StringBuilder sb = new StringBuilder();
        sb.append(isCanceled() ? "[X] " : "[ ] ");
        sb.append(getUrl());
        sb.append(StringUtils.SPACE);
        sb.append(str);
        sb.append(StringUtils.SPACE);
        sb.append(getPriority());
        sb.append(StringUtils.SPACE);
        sb.append(this.mSequence);
        return sb.toString();
    }

    public Request(int i, String str, @Nullable Response.ErrorListener errorListener) {
        this.mEventLog = VolleyLog.MarkerLog.ENABLED ? new VolleyLog.MarkerLog() : null;
        this.mLock = new Object();
        this.mShouldCache = true;
        this.mCanceled = false;
        this.mResponseDelivered = false;
        this.mShouldRetryServerErrors = false;
        this.mShouldRetryConnectionErrors = false;
        this.mCacheEntry = null;
        this.mMethod = i;
        this.mUrl = str;
        this.mErrorListener = errorListener;
        setRetryPolicy(new DefaultRetryPolicy());
        this.mDefaultTrafficStatsTag = findDefaultTrafficStatsTag(str);
    }

    @Override // java.lang.Comparable
    public int compareTo(Request<T> request) {
        Priority priority = getPriority();
        Priority priority2 = request.getPriority();
        return priority == priority2 ? this.mSequence.intValue() - request.mSequence.intValue() : priority2.ordinal() - priority.ordinal();
    }

    public VolleyError parseNetworkError(VolleyError volleyError) {
        return volleyError;
    }
}
