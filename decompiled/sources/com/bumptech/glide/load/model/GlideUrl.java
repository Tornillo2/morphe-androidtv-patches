package com.bumptech.glide.load.model;

import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.util.Preconditions;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class GlideUrl implements Key {
    public static final String ALLOWED_URI_CHARS = "@#&=*+-_.,:!?()/~'%;$";

    @Nullable
    public volatile byte[] cacheKeyBytes;
    public int hashCode;
    public final Headers headers;

    @Nullable
    public String safeStringUrl;

    @Nullable
    public URL safeUrl;

    @Nullable
    public final String stringUrl;

    @Nullable
    public final URL url;

    public GlideUrl(URL url) {
        this(url, Headers.DEFAULT);
    }

    @Override // com.bumptech.glide.load.Key
    public boolean equals(Object obj) {
        if (obj instanceof GlideUrl) {
            GlideUrl glideUrl = (GlideUrl) obj;
            if (getCacheKey().equals(glideUrl.getCacheKey()) && this.headers.equals(glideUrl.headers)) {
                return true;
            }
        }
        return false;
    }

    public String getCacheKey() {
        String str = this.stringUrl;
        if (str != null) {
            return str;
        }
        URL url = this.url;
        Preconditions.checkNotNull(url, "Argument must not be null");
        return url.toString();
    }

    public final byte[] getCacheKeyBytes() {
        if (this.cacheKeyBytes == null) {
            this.cacheKeyBytes = getCacheKey().getBytes(Key.CHARSET);
        }
        return this.cacheKeyBytes;
    }

    public Map<String, String> getHeaders() {
        return this.headers.getHeaders();
    }

    public final String getSafeStringUrl() {
        if (TextUtils.isEmpty(this.safeStringUrl)) {
            String string = this.stringUrl;
            if (TextUtils.isEmpty(string)) {
                URL url = this.url;
                Preconditions.checkNotNull(url, "Argument must not be null");
                string = url.toString();
            }
            this.safeStringUrl = Uri.encode(string, ALLOWED_URI_CHARS);
        }
        return this.safeStringUrl;
    }

    public final URL getSafeUrl() throws MalformedURLException {
        if (this.safeUrl == null) {
            this.safeUrl = new URL(getSafeStringUrl());
        }
        return this.safeUrl;
    }

    @Override // com.bumptech.glide.load.Key
    public int hashCode() {
        if (this.hashCode == 0) {
            int iHashCode = getCacheKey().hashCode();
            this.hashCode = iHashCode;
            this.hashCode = this.headers.hashCode() + (iHashCode * 31);
        }
        return this.hashCode;
    }

    public String toString() {
        return getCacheKey();
    }

    public String toStringUrl() {
        return getSafeStringUrl();
    }

    public URL toURL() throws MalformedURLException {
        return getSafeUrl();
    }

    @Override // com.bumptech.glide.load.Key
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update(getCacheKeyBytes());
    }

    public GlideUrl(String str) {
        this(str, Headers.DEFAULT);
    }

    public GlideUrl(URL url, Headers headers) {
        Preconditions.checkNotNull(url, "Argument must not be null");
        this.url = url;
        this.stringUrl = null;
        Preconditions.checkNotNull(headers, "Argument must not be null");
        this.headers = headers;
    }

    public GlideUrl(String str, Headers headers) {
        this.url = null;
        Preconditions.checkNotEmpty(str);
        this.stringUrl = str;
        Preconditions.checkNotNull(headers, "Argument must not be null");
        this.headers = headers;
    }
}
