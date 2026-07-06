package com.bumptech.glide.load;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import androidx.annotation.Nullable;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class HttpException extends IOException {
    public static final int UNKNOWN = -1;
    public static final long serialVersionUID = 1;
    public final int statusCode;

    public HttpException(int i) {
        this(MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Http request failed with status code: ", i), i, null);
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public HttpException(String str) {
        this(str, -1, null);
    }

    public HttpException(String str, int i) {
        this(str, i, null);
    }

    public HttpException(String str, int i, @Nullable Throwable th) {
        super(str, th);
        this.statusCode = i;
    }
}
