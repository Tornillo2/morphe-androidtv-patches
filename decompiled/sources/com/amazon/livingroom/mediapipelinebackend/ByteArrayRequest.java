package com.amazon.livingroom.mediapipelinebackend;

import androidx.annotation.NonNull;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class ByteArrayRequest extends Request<byte[]> {
    public Response.Listener<byte[]> listener;
    public final Object listenerLock;

    public ByteArrayRequest(@NonNull String str, Response.Listener<byte[]> listener, Response.ErrorListener errorListener) {
        this(0, str, listener, errorListener);
    }

    @Override // com.android.volley.Request
    public void cancel() {
        super.cancel();
        synchronized (this.listenerLock) {
            this.listener = null;
        }
    }

    @Override // com.android.volley.Request
    public Response<byte[]> parseNetworkResponse(NetworkResponse networkResponse) {
        return new Response<>(networkResponse.data, HttpHeaderParser.parseCacheHeaders(networkResponse));
    }

    public ByteArrayRequest(int i, @NonNull String str, Response.Listener<byte[]> listener, Response.ErrorListener errorListener) {
        super(i, str, errorListener);
        this.listenerLock = new Object();
        this.listener = listener;
    }

    @Override // com.android.volley.Request
    public void deliverResponse(byte[] bArr) {
        Response.Listener<byte[]> listener;
        synchronized (this.listenerLock) {
            listener = this.listener;
        }
        if (listener != null) {
            listener.onResponse(bArr);
        }
    }
}
