package com.android.volley.toolbox;

import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import java.io.UnsupportedEncodingException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class StringRequest extends Request<String> {

    @Nullable
    @GuardedBy("mLock")
    public Response.Listener<String> mListener;
    public final Object mLock;

    public StringRequest(int i, String str, Response.Listener<String> listener, @Nullable Response.ErrorListener errorListener) {
        super(i, str, errorListener);
        this.mLock = new Object();
        this.mListener = listener;
    }

    @Override // com.android.volley.Request
    public void cancel() {
        super.cancel();
        synchronized (this.mLock) {
            this.mListener = null;
        }
    }

    @Override // com.android.volley.Request
    public Response<String> parseNetworkResponse(NetworkResponse networkResponse) {
        String str;
        try {
            str = new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers, "ISO-8859-1"));
        } catch (UnsupportedEncodingException unused) {
            str = new String(networkResponse.data);
        }
        return new Response<>(str, HttpHeaderParser.parseCacheHeaders(networkResponse));
    }

    @Override // com.android.volley.Request
    public void deliverResponse(String str) {
        Response.Listener<String> listener;
        synchronized (this.mLock) {
            listener = this.mListener;
        }
        if (listener != null) {
            listener.onResponse(str);
        }
    }

    public StringRequest(String str, Response.Listener<String> listener, @Nullable Response.ErrorListener errorListener) {
        this(0, str, listener, errorListener);
    }
}
