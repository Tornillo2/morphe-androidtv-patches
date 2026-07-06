package com.android.volley.toolbox;

import androidx.annotation.Nullable;
import com.android.volley.Header;
import j$.util.DesugarCollections;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class HttpResponse {

    @Nullable
    public final InputStream mContent;

    @Nullable
    public final byte[] mContentBytes;
    public final int mContentLength;
    public final List<Header> mHeaders;
    public final int mStatusCode;

    public HttpResponse(int i, List<Header> list) {
        this(i, list, -1, null);
    }

    @Nullable
    public final InputStream getContent() {
        InputStream inputStream = this.mContent;
        if (inputStream != null) {
            return inputStream;
        }
        if (this.mContentBytes != null) {
            return new ByteArrayInputStream(this.mContentBytes);
        }
        return null;
    }

    @Nullable
    public final byte[] getContentBytes() {
        return this.mContentBytes;
    }

    public final int getContentLength() {
        return this.mContentLength;
    }

    public final List<Header> getHeaders() {
        return DesugarCollections.unmodifiableList(this.mHeaders);
    }

    public final int getStatusCode() {
        return this.mStatusCode;
    }

    public HttpResponse(int i, List<Header> list, int i2, InputStream inputStream) {
        this.mStatusCode = i;
        this.mHeaders = list;
        this.mContentLength = i2;
        this.mContent = inputStream;
        this.mContentBytes = null;
    }

    public HttpResponse(int i, List<Header> list, byte[] bArr) {
        this.mStatusCode = i;
        this.mHeaders = list;
        this.mContentLength = bArr.length;
        this.mContentBytes = bArr;
        this.mContent = null;
    }
}
